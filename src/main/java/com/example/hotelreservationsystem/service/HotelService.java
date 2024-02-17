package com.example.hotelreservationsystem.service;

import com.example.hotelreservationsystem.api.v1.request.HotelRq;
import com.example.hotelreservationsystem.api.v1.response.HotelRs;
import com.example.hotelreservationsystem.entity.Hotel;
import com.example.hotelreservationsystem.exception.EntityNotFoundException;
import com.example.hotelreservationsystem.mappers.HotelMapper;
import com.example.hotelreservationsystem.repository.HotelRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class HotelService {

    private final HotelRepository hotelRepository;

    public List<HotelRs> getAllHotels(Integer offset, Integer perPage) {
        List<Hotel> contentList = new ArrayList<>();
        if (offset == null) offset = 0;
        if (perPage == null) perPage = 10;

        Pageable pageable = PageRequest.of(offset, perPage);

        Page<Hotel> content = hotelRepository.findAll(pageable);
        if (!content.isEmpty()) contentList = content.getContent();

        log.info("getAllHotels: offset=" + offset + ", perPage=" + perPage);

        return HotelMapper.INSTANCE.toDTO(contentList);
    }

    public HotelRs getIdHotel(Long id) {

        Optional<Hotel> byId = hotelRepository.findById(id);

        if (byId.isPresent()) {
            log.info("getIdHotel: " + id);
            return HotelMapper.INSTANCE.toDTO(byId.get());
        }

        throw new EntityNotFoundException("Hotel not found id=" + id);

    }

    public HotelRs putIdHotel(Long id, HotelRq hotelRq) {

        Optional<Hotel> byId = hotelRepository.findById(id);

        if (byId.isEmpty()) {
            throw new EntityNotFoundException("Hotel is not found id=" + id);
        }

        Hotel hotel = HotelMapper.INSTANCE.toModel(hotelRq);
        hotel.setId(id);
        Hotel save = hotelRepository.save(hotel);
        log.info("putIdHotel: id=" + id + ", hotelRq=" + hotelRq);

        return HotelMapper.INSTANCE.toDTO(save);
    }

    public HotelRs addHotel(HotelRq hotelRq) {

        Hotel hotel = HotelMapper.INSTANCE.toModel(hotelRq);
        hotel.setId(0);
        Hotel save = hotelRepository.save(hotel);
        log.info("addHotel: " + hotelRq);

        return HotelMapper.INSTANCE.toDTO(save);
    }

    public void deleteHotel(Long id) {
        hotelRepository.deleteById(id);
        log.info("deleteHotel: " + id);
    }

    public void addRating(Long id, Integer newMark) {

        Optional<Hotel> byId = hotelRepository.findById(id);
        if (byId.isEmpty()) throw new EntityNotFoundException("Hotel is not found id=" + id);

        Hotel hotel = byId.get();
        double totalRating = hotel.getRating() * hotel.getNumberRatings();

        if (totalRating == 0D) {
            totalRating = newMark;
        } else {
            totalRating = totalRating - hotel.getRating() + newMark;
        }
        double rating;
        if (hotel.getNumberRatings() == 0){
            rating = totalRating;
        } else {
            double round = Math.round((totalRating / hotel.getNumberRatings()) * 10);
            rating = round / 10;
        }

        hotel.setNumberRatings(hotel.getNumberRatings() + 1);
        hotel.setRating(rating);

        hotelRepository.save(hotel);
    }
}
