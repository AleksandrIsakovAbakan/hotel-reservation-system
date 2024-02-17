package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.api.v1.request.BookingRq;
import com.example.hotelreservationsystem.api.v1.response.BookingRs;
import com.example.hotelreservationsystem.service.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/booking")
public class BookingController {

    private final BookingService bookingService;

    @GetMapping
    public List<BookingRs> getPageBooking(@RequestParam(required = false) Integer offset,
                                        @RequestParam(required = false) Integer perPage)
    {
        return bookingService.getAllBookings(offset, perPage);
    }

    @PostMapping
    public BookingRs addBooking(@Validated @RequestBody BookingRq bookingRq)
    {
        return bookingService.addBooking(bookingRq);
    }
}
