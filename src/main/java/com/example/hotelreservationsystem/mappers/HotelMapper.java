package com.example.hotelreservationsystem.mappers;

import com.example.hotelreservationsystem.api.v1.request.HotelRq;
import com.example.hotelreservationsystem.api.v1.response.HotelRs;
import com.example.hotelreservationsystem.entity.Hotel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface HotelMapper {

    HotelMapper INSTANCE = Mappers.getMapper(HotelMapper.class);

    List<HotelRs> toDTO(List<Hotel> list);

    HotelRs toDTO(Hotel hotel);

    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "numberRatings", ignore = true)
    Hotel toModel(HotelRq hotelRq);
}
