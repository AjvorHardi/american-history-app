package com.example.american_history_app.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.YearMonth;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventDTO {
    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private String linkUrl;

    @JsonFormat(pattern = "yyyy-MM")
    private YearMonth eventDate;
}