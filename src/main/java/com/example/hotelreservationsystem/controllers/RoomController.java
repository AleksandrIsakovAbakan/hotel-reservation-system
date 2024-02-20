package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.api.v1.request.RoomRq;
import com.example.hotelreservationsystem.api.v1.response.HotelRs;
import com.example.hotelreservationsystem.api.v1.response.RoomRs;
import com.example.hotelreservationsystem.model.HotelFilter;
import com.example.hotelreservationsystem.model.RoomFilter;
import com.example.hotelreservationsystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public RoomRs getRoomId(@PathVariable Long id)
    {
        return roomService.getIdRoom(id);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public RoomRs editRoomId(@PathVariable Long id,
                             @Validated @RequestBody(required = false) RoomRq roomRq)
    {
        return roomService.putIdRoom(id, roomRq);
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public RoomRs addRoom(@Validated @RequestBody RoomRq roomRq)
    {
        return roomService.addRoom(roomRq);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseEntity deleteRoom(@PathVariable Long id)
    {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/search")
    @PreAuthorize("hasAnyAuthority('ROLE_USER', 'ROLE_ADMIN')")
    public ResponseEntity<List<RoomRs>> getPageRooms(@RequestParam(required = false) Long roomId,
                                                     @RequestParam(required = false) String name,
                                                     @RequestParam(required = false) Integer priceMin,
                                                     @RequestParam(required = false) Integer priceMax,
                                                     @RequestParam(required = false) Integer numberQuestsInRoom,
                                                     @RequestParam(required = false) LocalDate arrivalDate,
                                                     @RequestParam(required = false) LocalDate dateOfDeparture,
                                                     @RequestParam(required = false) Integer offset,
                                                     @RequestParam(required = false) Integer perPage) {
        return new ResponseEntity<>(roomService.filterByRooms(new RoomFilter(roomId, name, priceMin,
                priceMax, numberQuestsInRoom, arrivalDate, dateOfDeparture, offset, perPage)), HttpStatus.OK);
    }

}
