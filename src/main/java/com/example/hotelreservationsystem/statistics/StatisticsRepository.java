package com.example.hotelreservationsystem.statistics;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsRepository extends MongoRepository<StatisticsEntity, String> {
}
