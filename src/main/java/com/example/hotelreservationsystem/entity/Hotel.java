package com.example.hotelreservationsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "hotel")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String name;

    @Column(name = "head_line", columnDefinition = "VARCHAR(255)")
    private String headLine;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String city;

    @Column(columnDefinition = "VARCHAR(255) NOT NULL")
    private String address;

    @Column(name = "distance_from_city_center")
    private int distanceFromCityCenter;

    private int rating;

    @Column(name = "number_ratings")
    private int numberRatings;
}
