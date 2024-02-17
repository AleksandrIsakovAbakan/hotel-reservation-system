package com.example.hotelreservationsystem.mappers;

import com.example.hotelreservationsystem.api.v1.request.RoomRq;
import com.example.hotelreservationsystem.api.v1.response.RoomRs;
import com.example.hotelreservationsystem.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface RoomMapper {

    RoomMapper INSTANCE = Mappers.getMapper(RoomMapper.class);

    List<RoomRs> toDTO(List<Room> list);

    RoomRs toDTO(Room room);

    @Mapping(target = "hotel", ignore = true)
    Room toModel(RoomRq roomRq);
}
