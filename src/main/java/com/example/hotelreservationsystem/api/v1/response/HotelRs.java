package com.example.hotelreservationsystem.api.v1.response;

import lombok.Data;


@Data
public class HotelRs {

    private long id;

    private String name;

    private String headLine;

    private String city;

    private String address;

    private int distanceFromCityCenter;

    private double rating;

    private int numberRatings;
}
