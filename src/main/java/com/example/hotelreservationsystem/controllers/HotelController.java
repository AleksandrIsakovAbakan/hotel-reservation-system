package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.api.v1.request.HotelRq;
import com.example.hotelreservationsystem.api.v1.response.HotelRs;
import com.example.hotelreservationsystem.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    public List<HotelRs> getPageHotel(@RequestParam(required = false) Integer offset,
                                     @RequestParam(required = false) Integer perPage)
    {
        return hotelService.getAllHotels(offset, perPage);
    }

    @GetMapping("/{id}")
    public HotelRs getHotelId(@PathVariable Long id)
    {
        return hotelService.getIdHotel(id);
    }

    @PutMapping("/{id}")
    public HotelRs editHotel(@PathVariable Long id,
                             @Validated @RequestBody(required = false) HotelRq hotelRq)
    {
        return hotelService.putIdHotel(id, hotelRq);
    }

    @PostMapping
    public HotelRs addHotel(@Validated @RequestBody HotelRq hotelRq)
    {
        return hotelService.addHotel(hotelRq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteHotel(@PathVariable Long id)
    {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
