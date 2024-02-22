package com.example.hotelreservationsystem.statistics;

import com.example.hotelreservationsystem.model.StatisticsEvent;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface StatisticsMapper {

    StatisticsMapper INSTANCE = Mappers.getMapper(StatisticsMapper.class);
    StatisticsEntity toModel(StatisticsEvent statisticsEvent);
}
