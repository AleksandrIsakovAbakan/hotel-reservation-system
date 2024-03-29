package com.example.hotelreservationsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "unavailable_dates")
public class UnavailableDates {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name = "room_id")
    @JsonIgnore
    private long roomId;

    @Column(name = "room_unavailable_data", columnDefinition = "DATE")
    private LocalDate roomUnavailableData;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="room_id", referencedColumnName = "id", insertable = false, updatable = false)
    @ToString.Exclude
    @JsonIgnore
    private Room room;
}
