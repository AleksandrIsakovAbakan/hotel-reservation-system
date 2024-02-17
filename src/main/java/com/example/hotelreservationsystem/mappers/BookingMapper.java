package com.example.hotelreservationsystem.mappers;

import com.example.hotelreservationsystem.api.v1.request.BookingRq;
import com.example.hotelreservationsystem.api.v1.response.BookingRs;
import com.example.hotelreservationsystem.entity.Booking;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper()
public interface BookingMapper {

    BookingMapper INSTANCE = Mappers.getMapper(BookingMapper.class);

    List<BookingRs> toDTO(List<Booking> list);

    BookingRs toDTO(Booking booking);

    Booking toModel(BookingRq bookingRq);
}
