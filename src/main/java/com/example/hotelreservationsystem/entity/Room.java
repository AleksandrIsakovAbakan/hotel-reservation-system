package com.example.hotelreservationsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(columnDefinition = "VARCHAR(255)")
    private String description;

    @Column(name = "room_number")
    private int roomNumber;

    private int price;

    @Column(name = "maximum_number_people")
    private int maximumNumberPeople;

    @Column(name = "hotel_id")
    private long hotelId;

    @OneToMany(mappedBy="room", fetch = FetchType.EAGER)
    private List<UnavailableDates> unavailableDatesList = new ArrayList<>();
}
