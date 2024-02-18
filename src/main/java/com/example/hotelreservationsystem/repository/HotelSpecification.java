package com.example.hotelreservationsystem.repository;

import com.example.hotelreservationsystem.entity.Hotel;
import com.example.hotelreservationsystem.model.HotelFilter;
import org.springframework.data.jpa.domain.Specification;

public interface HotelSpecification {

    static Specification<Hotel> withFilter(HotelFilter hotelFilter){
        return Specification.where(byIdHotel(hotelFilter.getHotelId()))
                .and(byNameHotel(hotelFilter.getNameHotel()))
                .and(byHeadLine(hotelFilter.getHeadLine()))
                .and(byCity(hotelFilter.getCity()))
                .and(byAddress(hotelFilter.getAddress()))
                .and(byDistanceFromCityCenter(hotelFilter.getDistanceFromCityCenter()));

    }

    static Specification<Hotel> byIdHotel(Long idHotel) {
        return (root, query, cb) -> {
            if (idHotel == null) return null;
            return cb.equal(root.get("id"), idHotel);
        };
    }

    static Specification<Hotel> byNameHotel(String nameHotel) {
        return (root, query, cb) -> {
            if (nameHotel == null) return null;
            return cb.equal(root.get("name"), nameHotel);
        };
    }

    static Specification<Hotel> byHeadLine(String headLine) {
        return (root, query, cb) -> {
            if (headLine == null) return null;
            return cb.equal(root.get("headLine"), headLine);
        };
    }

    static Specification<Hotel> byCity(String city) {
        return (root, query, cb) -> {
            if (city == null) return null;
            return cb.equal(root.get("city"), city);
        };
    }

    static Specification<Hotel> byAddress(String address) {
        return (root, query, cb) -> {
            if (address == null) return null;
            return cb.equal(root.get("address"), address);
        };
    }

    static Specification<Hotel> byDistanceFromCityCenter(Integer distanceFromCityCenter) {
        return (root, query, cb) -> {
            if (distanceFromCityCenter == null) return null;
            return cb.lessThanOrEqualTo(root.get("distanceFromCityCenter"), distanceFromCityCenter);
        };
    }

}
