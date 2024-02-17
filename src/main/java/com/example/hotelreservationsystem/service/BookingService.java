package com.example.hotelreservationsystem.service;

import com.example.hotelreservationsystem.api.v1.request.BookingRq;
import com.example.hotelreservationsystem.api.v1.response.BookingRs;
import com.example.hotelreservationsystem.entity.Booking;
import com.example.hotelreservationsystem.mappers.BookingMapper;
import com.example.hotelreservationsystem.repository.BookingRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class BookingService {

    private final BookingRepository bookingRepository;

    private final RoomService roomService;

    private final UserService userService;

    public List<BookingRs> getAllBookings(Integer offset, Integer perPage) {

        List<Booking> contentList = new ArrayList<>();
        if (offset == null) offset = 0;
        if (perPage == null) perPage = 10;

        Pageable pageable = PageRequest.of(offset, perPage);

        Page<Booking> content = bookingRepository.findAll(pageable);
        if (!content.isEmpty()) contentList = content.getContent();

        log.info("getAllBooking: offset=" + offset + ", perPage=" + perPage);

        return BookingMapper.INSTANCE.toDTO(contentList);
    }
    public BookingRs addBooking(BookingRq bookingRq) {

        roomService.checkingIdRoom(bookingRq.getRoomId());
        userService.checkingIdUser(bookingRq.getUserId());
        roomService.checkingRoomForVacancy(bookingRq);

        Booking booking = BookingMapper.INSTANCE.toModel(bookingRq);
        booking.setId(0);
        Booking save = bookingRepository.save(booking);
        log.info("addBooking: " + bookingRq);

        roomService.saveDates(bookingRq);

        return BookingMapper.INSTANCE.toDTO(save);
    }
}
