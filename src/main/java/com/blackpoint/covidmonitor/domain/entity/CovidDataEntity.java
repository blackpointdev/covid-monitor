package com.blackpoint.covidmonitor.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class CovidDataEntity {
    private final Long id;
    private final Date date;
    private final String country;
    private final Long newlyInfected;
    private final Long allInfected;
    private final Long newlyDeceased;
    private final Long allDeceased;
}
