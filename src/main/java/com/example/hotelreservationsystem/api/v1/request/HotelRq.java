package com.example.hotelreservationsystem.api.v1.request;

import lombok.Data;

@Data
public class HotelRq {

    private long id;

    private String name;

    private String headLine;

    private String city;

    private String address;

    private int distanceFromCityCenter;

}
