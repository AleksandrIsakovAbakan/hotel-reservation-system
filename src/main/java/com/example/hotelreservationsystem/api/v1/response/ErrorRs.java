package com.example.hotelreservationsystem.api.v1.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorRs {

    private String error;

    @JsonProperty("error_description")
    private String errorDescription;

    private Long timestamp;

    public ErrorRs(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
        this.timestamp = System.currentTimeMillis();
    }

}
