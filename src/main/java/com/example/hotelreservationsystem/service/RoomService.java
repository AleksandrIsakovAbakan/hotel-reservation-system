package com.example.hotelreservationsystem.service;

import com.example.hotelreservationsystem.api.v1.request.RoomRq;
import com.example.hotelreservationsystem.api.v1.response.RoomRs;
import com.example.hotelreservationsystem.entity.Room;
import com.example.hotelreservationsystem.exception.EntityNotFoundException;
import com.example.hotelreservationsystem.mappers.RoomMapper;
import com.example.hotelreservationsystem.repository.RoomRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RoomService {

    private final RoomRepository roomRepository;

    private final HotelService hotelService;

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

        roomRepository.deleteById(id);
        log.info("deleteRoom: " + id);
    }
}
