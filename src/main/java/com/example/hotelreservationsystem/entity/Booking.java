package com.example.hotelreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "user_id")
    private long userId;

    @Column(name = "room_id")
    private long roomId;

    @Column(name = "arrival_date", columnDefinition = "DATE")
    private LocalDate arrivalDate;

    @Column(name = "date_of_departure", columnDefinition = "DATE")
    private LocalDate dateOfDeparture;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    @JsonIgnore
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="room_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    @JsonIgnore
    private Room room;
}
