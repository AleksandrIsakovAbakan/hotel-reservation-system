package com.example.hotelreservationsystem.api.v1.response;

import com.example.hotelreservationsystem.entity.UnavailableDates;
import lombok.Data;

import java.util.List;

@Data
public class RoomRs {

    private long id;

    private String name;

    private String description;

    private int roomNumber;

    private int price;

    private int maximumNumberPeople;

    private long hotelId;

    private List<UnavailableDates> unavailableDatesList;
}
