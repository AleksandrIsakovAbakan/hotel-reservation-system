package com.example.hotelreservationsystem.repository;

import com.example.hotelreservationsystem.entity.UnavailableDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailableDatesRepository extends JpaRepository<UnavailableDates, Long> {
}
