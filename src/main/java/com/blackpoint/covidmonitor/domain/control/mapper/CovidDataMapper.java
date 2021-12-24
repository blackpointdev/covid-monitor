package com.blackpoint.covidmonitor.domain.control.mapper;

import com.blackpoint.covidmonitor.domain.entity.CovidDataEntity;
import com.blackpoint.covidmonitor.domain.entity.jpa.CovidDataJPA;

import javax.enterprise.context.ApplicationScoped;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;

@ApplicationScoped
public class CovidDataMapper {
    public List<CovidDataEntity> mapToEntities(List<CovidDataJPA> covidDataJPAS) {
        return covidDataJPAS.stream()
                .map(this::mapToEntity)
                .collect(Collectors.toList());
    }

    public CovidDataEntity mapToEntity(CovidDataJPA covidDataJPA) {
        return CovidDataEntity.builder()
                .id(covidDataJPA.getId())
                .date(covidDataJPA.getX_date())
                .country(covidDataJPA.getCountry())
                .newlyInfected(covidDataJPA.getNewlyInfected())
                .allInfected(covidDataJPA.getAllInfected())
                .newlyDeceased(covidDataJPA.getNewlyDeceased())
                .allDeceased(covidDataJPA.getAllDeceased())
                .build();
    }

    public CovidDataJPA mapToJPA(CovidDataEntity covidDataEntity) {
        return CovidDataJPA.builder()
                .x_date(new Date(covidDataEntity.getDate().getTime()))
                .country(covidDataEntity.getCountry())
                .newlyInfected(covidDataEntity.getNewlyInfected())
                .allInfected(covidDataEntity.getAllInfected())
                .newlyDeceased(covidDataEntity.getNewlyDeceased())
                .allDeceased(covidDataEntity.getAllDeceased())
                .build();
    }
}
