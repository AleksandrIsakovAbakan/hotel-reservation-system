package com.example.hotelreservationsystem.mappers;

import com.example.hotelreservationsystem.api.v1.request.BookingRq;
import com.example.hotelreservationsystem.api.v1.response.BookingRs;
import com.example.hotelreservationsystem.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    List<BookingRs> toDTO(List<Booking> list);

    BookingRs toDTO(Booking booking);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "room", ignore = true)
    Booking toModel(BookingRq bookingRq);
}
