package com.blackpoint.covidmonitor.domain.entity.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CovidDataCreationDTO {
    private String date;
    private String country;
    private Long newlyInfected;
    private Long allInfected;
    private Long newlyDeceased;
    private Long allDeceased;
}
