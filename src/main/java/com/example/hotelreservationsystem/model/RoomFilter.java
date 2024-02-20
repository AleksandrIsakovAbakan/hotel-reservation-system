package com.example.hotelreservationsystem.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class RoomFilter {

    private Long roomId;

    private String name;

    private Integer priceMin;

    private Integer priceMax;

    private Integer numberQuestsInRoom;

    private LocalDate arrivalDate;

    private LocalDate dateOfDeparture;

    private Integer offset;

    private Integer perPage;

    public RoomFilter(Long roomId, String name, Integer priceMin, Integer priceMax, Integer numberQuestsInRoom,
                      LocalDate arrivalDate, LocalDate dateOfDeparture, Integer offset, Integer perPage) {
        this.roomId = roomId;
        this.name = name;
        this.priceMin = priceMin;
        this.priceMax = priceMax;
        this.numberQuestsInRoom = numberQuestsInRoom;
        this.arrivalDate = arrivalDate;
        this.dateOfDeparture = dateOfDeparture;
        this.offset = offset;
        this.perPage = perPage;
    }
}
