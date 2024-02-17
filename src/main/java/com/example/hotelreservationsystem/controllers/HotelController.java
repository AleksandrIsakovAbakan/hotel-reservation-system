package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.api.v1.request.HotelRq;
import com.example.hotelreservationsystem.api.v1.response.HotelRs;
import com.example.hotelreservationsystem.service.HotelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/hotel")
public class HotelController {

    private final HotelService hotelService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public List<HotelRs> getPageHotel(@RequestParam(required = false) Integer offset,
                                     @RequestParam(required = false) Integer perPage)
    {
        return hotelService.getAllHotels(offset, perPage);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public HotelRs getHotelId(@PathVariable Long id)
    {
        return hotelService.getIdHotel(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public HotelRs editHotel(@PathVariable Long id,
                             @Validated @RequestBody(required = false) HotelRq hotelRq)
    {
        return hotelService.putIdHotel(id, hotelRq);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public HotelRs addHotel(@Validated @RequestBody HotelRq hotelRq)
    {
        return hotelService.addHotel(hotelRq);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteHotel(@PathVariable Long id)
    {
        hotelService.deleteHotel(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/rating/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity getRating(@PathVariable Long id,
                                    @RequestParam Integer newMark){
        hotelService.addRating(id, newMark);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
