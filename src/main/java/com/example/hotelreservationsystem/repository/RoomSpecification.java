package com.example.hotelreservationsystem.repository;

import com.example.hotelreservationsystem.entity.Room;
import com.example.hotelreservationsystem.entity.UnavailableDates;
import com.example.hotelreservationsystem.model.RoomFilter;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDate;

public interface RoomSpecification {

    static Specification<Room> withFilter(RoomFilter roomFilter){

        return Specification.where(byIdRoom(roomFilter.getRoomId()))
                .and(byNameRoom(roomFilter.getName()))
                .and(byPriceMin(roomFilter.getPriceMin()))
                .and(byPriceMax(roomFilter.getPriceMax()))
                .and(byNumberQuestsInRoom(roomFilter.getNumberQuestsInRoom()))
                .and(byBetweenDate(roomFilter.getArrivalDate(), roomFilter.getDateOfDeparture()));

    }

    static Specification<Room> byIdRoom(Long idRoom) {

        return (root, query, cb) -> {
            if (idRoom == null) return null;
            return cb.equal(root.get("id"), idRoom);
        };
    }

    static Specification<Room> byNameRoom(String nameRoom) {

        return (root, query, cb) -> {
            if (nameRoom == null) return null;
            return cb.equal(root.get("name"), nameRoom);
        };
    }

    static Specification<Room> byPriceMin(Integer priceMin) {

        return (root, query, cb) -> {
            if (priceMin == null) return null;
            return cb.greaterThanOrEqualTo(root.get("price"), priceMin);
        };
    }

    static Specification<Room> byPriceMax(Integer priceMax) {

        return (root, query, cb) -> {
            if (priceMax == null) return null;
            return cb.lessThanOrEqualTo(root.get("price"), priceMax);
        };
    }

    static Specification<Room> byNumberQuestsInRoom(Integer numberQuestsInRoom) {

        return (root, query, cb) -> {
            if (numberQuestsInRoom == null) return null;
            return cb.greaterThanOrEqualTo(root.get("maximumNumberPeople"), numberQuestsInRoom);
        };
    }

    static Specification<Room> byBetweenDate(LocalDate arrivalDate, LocalDate dateOfDeparture) {

        return (root, query, cb) -> {
            if (arrivalDate == null) return null;
            if (dateOfDeparture == null) return null;

            CriteriaQuery<Room> roomCriteriaQuery = cb.createQuery(Room.class);
            Subquery<UnavailableDates> subQuery = roomCriteriaQuery.subquery(UnavailableDates.class);
            Root<Room> subRoomRoot = subQuery.correlate(root);
            Join<Room, UnavailableDates> unavailableDatesJoin = subRoomRoot.join("unavailableDatesList");
            subQuery.select(unavailableDatesJoin)
                    .where(cb.between(subRoomRoot.get("unavailableDatesList")
                            .get("roomUnavailableData"), arrivalDate, dateOfDeparture));

            return cb.not(cb.exists(subQuery));
        };
    }
}
