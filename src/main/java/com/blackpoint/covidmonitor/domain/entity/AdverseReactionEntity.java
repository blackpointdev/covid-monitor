package com.blackpoint.covidmonitor.domain.entity;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;


enum Sex {
    Female, Male
}

@Getter
@Builder
public class AdverseReactionEntity {
    private final Long id;
    private final Date date;
    private final String description;
    private Sex sex;
    private final Integer age;
}
