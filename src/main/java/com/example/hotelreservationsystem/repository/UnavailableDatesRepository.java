package com.example.hotelreservationsystem.repository;

import com.example.hotelreservationsystem.entity.UnavailableDates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UnavailableDatesRepository extends JpaRepository<UnavailableDates, Long> {

    Optional<List<UnavailableDates>> findAllByRoomId(long id);

    void deleteAllByRoomId(Long id);
}
