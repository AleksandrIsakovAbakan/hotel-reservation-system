package com.example.hotelreservationsystem.model;

import lombok.Data;

@Data
public class HotelFilter {

    private Long hotelId;

    private String nameHotel;

    private String headLine;

    private String city;

    private String address;

    private Integer distanceFromCityCenter;

    private Integer offset;

    private Integer perPage;

    public HotelFilter(Long hotelId, String nameHotel, String headLine, String city, String address,
                       Integer distanceFromCityCenter, Integer offset, Integer perPage) {
        this.hotelId = hotelId;
        this.nameHotel = nameHotel;
        this.headLine = headLine;
        this.city = city;
        this.address = address;
        this.distanceFromCityCenter = distanceFromCityCenter;
        this.offset = offset;
        this.perPage = perPage;
    }
}
