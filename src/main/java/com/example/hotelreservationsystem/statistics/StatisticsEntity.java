package com.example.hotelreservationsystem.statistics;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class StatisticsEntity {

    private String typeEvent;

    private long userId;

    private String arrivalDate;

    private String dateOfDeparture;

}
