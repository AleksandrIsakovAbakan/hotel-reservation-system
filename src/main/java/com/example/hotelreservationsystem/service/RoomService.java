package com.example.hotelreservationsystem.service;

import com.example.hotelreservationsystem.api.v1.request.BookingRq;
import com.example.hotelreservationsystem.api.v1.request.RoomRq;
import com.example.hotelreservationsystem.api.v1.response.RoomRs;
import com.example.hotelreservationsystem.entity.Room;
import com.example.hotelreservationsystem.entity.UnavailableDates;
import com.example.hotelreservationsystem.exception.DuringSpecifiedPeriodRoomOccupiedException;
import com.example.hotelreservationsystem.exception.EntityNotFoundException;
import com.example.hotelreservationsystem.mappers.RoomMapper;
import com.example.hotelreservationsystem.repository.BookingRepository;
import com.example.hotelreservationsystem.repository.RoomRepository;
import com.example.hotelreservationsystem.repository.UnavailableDatesRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    private final UnavailableDatesRepository unavailableDatesRepository;

    private final HotelService hotelService;

    private final BookingRepository bookingRepository;

    public RoomRs getIdRoom(Long id) {

        Optional<Room> byId = roomRepository.findById(id);

        if (byId.isPresent()) {
            log.info("getIdRoom: " + id);
            return RoomMapper.INSTANCE.toDTO(byId.get());
        }

        throw new EntityNotFoundException("Room not found id=" + id);
    }

    public RoomRs putIdRoom(Long id, RoomRq roomRq) {

        Optional<Room> byId = roomRepository.findById(id);

        if (byId.isEmpty()) throw new EntityNotFoundException("Room is not found id=" + id);

        Room room = RoomMapper.INSTANCE.toModel(roomRq);
        room.setId(id);

        if (hotelService.getIdHotel(room.getHotelId()) == null) {
            throw new EntityNotFoundException("Hotel is not found id=" + roomRq.getHotelId());
        }

        Room save = roomRepository.save(room);
        log.info("putIdRoom: id=" + id + ", roomRq=" + roomRq);

        return RoomMapper.INSTANCE.toDTO(save);
    }

    public RoomRs addRoom(RoomRq roomRq) {

        Room room = RoomMapper.INSTANCE.toModel(roomRq);
        room.setId(0);

        if (hotelService.getIdHotel(room.getHotelId()) == null) {
            throw new EntityNotFoundException("Hotel is not found id=" + roomRq.getHotelId());
        }

        Room save = roomRepository.save(room);
        log.info("addRoom: " + roomRq);

        return RoomMapper.INSTANCE.toDTO(save);
    }

    public void deleteRoom(Long id) {

        bookingRepository.deleteAllByRoomId(id);
        unavailableDatesRepository.deleteAllByRoomId(id);
        roomRepository.deleteById(id);
        log.info("deleteRoom: " + id);
    }

    public void checkingRoomForVacancy(BookingRq bookingRq) {

        RoomRs roomRs = getIdRoom(bookingRq.getRoomId());

        List<UnavailableDates> unavailableDatesList = createUnavailableDates(bookingRq);
        Optional<List<UnavailableDates>> unavailableDatesListOld = unavailableDatesRepository
                .findAllByRoomId(roomRs.getId());

        if (unavailableDatesListOld.isPresent()){
            List<UnavailableDates> unavailableDatesListOld1 = unavailableDatesListOld.get();

            for (UnavailableDates unavailableDates : unavailableDatesList){
                if (unavailableDatesListOld1.stream().anyMatch(dates -> dates.getRoomUnavailableData()
                        .equals(unavailableDates.getRoomUnavailableData())))
                    throw new DuringSpecifiedPeriodRoomOccupiedException("During the specified period the room is occupied: room: "
                        + bookingRq.getRoomId() + " " + bookingRq.getArrivalDate() + " " + bookingRq.getDateOfDeparture());
            }
        }
    }

    public void saveDates(BookingRq bookingRq) {

        List<UnavailableDates> unavailableDatesList = createUnavailableDates(bookingRq);
        unavailableDatesRepository.saveAll(unavailableDatesList);
    }

    public List<UnavailableDates> createUnavailableDates(BookingRq bookingRq){
        List<UnavailableDates> unavailableDatesList = new ArrayList<>();
        for (LocalDate d = bookingRq.getArrivalDate(); d.isBefore(bookingRq.getDateOfDeparture().plusDays(1));
             d = d.plusDays(1)){
            UnavailableDates unavailableDates = new UnavailableDates();
            unavailableDates.setRoomId(bookingRq.getRoomId());
            unavailableDates.setRoomUnavailableData(d);
            unavailableDatesList.add(unavailableDates);
        }
        return unavailableDatesList;
    }

    public void checkingIdRoom(long roomId) {

        Optional<Room> byId = roomRepository.findById(roomId);
        if (byId.isEmpty()) throw new EntityNotFoundException("Not found roomId: " + roomId);
    }
}
