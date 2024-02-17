package com.example.hotelreservationsystem.repository;

import com.example.hotelreservationsystem.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Long> {
    void deleteAllByRoomId(Long id);
}
