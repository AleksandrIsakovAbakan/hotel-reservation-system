package com.example.hotelreservationsystem.controllers;

import com.example.hotelreservationsystem.api.v1.request.UserRq;
import com.example.hotelreservationsystem.api.v1.response.UserRs;
import com.example.hotelreservationsystem.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/user")
public class UserController {

    private final UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<UserRs> getUserId(@PathVariable(required = false) Long id) {
        return new ResponseEntity<>(userService.getIdUser(id), HttpStatus.OK);
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<UserRs> getUserUsername(@PathVariable(required = false) String username) {
        return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserRs> editUser(@PathVariable(required = false) Long id,
                                           @RequestBody UserRq userRQ) {
        return new ResponseEntity<>(userService.putIdUser(id, userRQ), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<UserRs> addUser(@RequestBody(required = false) UserRq userRq) {
        return new ResponseEntity<>(userService.addUser(userRq), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<UserRs> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
