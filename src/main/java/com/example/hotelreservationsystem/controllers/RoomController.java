package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.api.v1.request.RoomRq;
import com.example.hotelreservationsystem.api.v1.response.RoomRs;
import com.example.hotelreservationsystem.service.RoomService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/room")
public class RoomController {

    private final RoomService roomService;

    @GetMapping("/{id}")
    public RoomRs getRoomId(@PathVariable Long id)
    {
        return roomService.getIdRoom(id);
    }

    @PutMapping("/{id}")
    public RoomRs editRoomId(@PathVariable Long id,
                             @Validated @RequestBody(required = false) RoomRq roomRq)
    {
        return roomService.putIdRoom(id, roomRq);
    }

    @PostMapping
    public RoomRs addRoom(@Validated @RequestBody RoomRq roomRq)
    {
        return roomService.addRoom(roomRq);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteRoom(@PathVariable Long id)
    {
        roomService.deleteRoom(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
