package com.example.hotelreservationsystem.statistics;

import com.example.hotelreservationsystem.exception.EntityNotFoundException;
import com.example.hotelreservationsystem.model.StatisticsEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticsService {

    private final StatisticsRepository statisticsRepository;

    public void addEvent(StatisticsEvent statisticsEvent){
        statisticsRepository.save(StatisticsMapper.INSTANCE.toModel(statisticsEvent));
    }

    public String getStatistics(){

        StringBuilder statisticsStr = new StringBuilder();
        List<StatisticsEntity> userEventRepositoryAll = statisticsRepository.findAll();
        if (!userEventRepositoryAll.isEmpty()) {
            statisticsStr.append("typeEvent");
            statisticsStr.append(",");
            statisticsStr.append("userId");
            statisticsStr.append(",");
            statisticsStr.append("arrivalDate");
            statisticsStr.append(",");
            statisticsStr.append("dateOfDeparture");
            statisticsStr.append("\n");
            for (StatisticsEntity statisticsEntity : userEventRepositoryAll){
                statisticsStr.append(statisticsEntity.getTypeEvent());
                statisticsStr.append(",");
                statisticsStr.append(statisticsEntity.getUserId());
                statisticsStr.append(",");
                statisticsStr.append(statisticsEntity.getArrivalDate());
                statisticsStr.append(",");
                statisticsStr.append(statisticsEntity.getDateOfDeparture());
                statisticsStr.append("\n");
            }
        } else {
            throw new EntityNotFoundException("Statistics database is empty.");
        }
        return statisticsStr.toString();
    }

    public void writeFileStatistics() throws IOException {

        String content = getStatistics();
        File file1 = new File("statistics.csv");
        Files.writeString(Paths.get(file1.toURI()), content, StandardOpenOption.CREATE);
    }
}
