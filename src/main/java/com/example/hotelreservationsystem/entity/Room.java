package com.example.hotelreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ToString.Exclude
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="hotel_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    private Hotel hotel;
}
