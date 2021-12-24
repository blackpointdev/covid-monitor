package com.blackpoint.covidmonitor.domain.boundary.mapper;

import com.blackpoint.covidmonitor.domain.entity.CovidDataEntity;
import com.blackpoint.covidmonitor.domain.entity.dto.CovidDataCreationDTO;

import javax.enterprise.context.ApplicationScoped;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@ApplicationScoped
public class CovidDataDTOMapper {
    public CovidDataEntity mapToEntity(CovidDataCreationDTO covidDataCreationDTO) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyy");
        return CovidDataEntity.builder()
                .date(simpleDateFormat.parse(covidDataCreationDTO.getDate()))
                .country(covidDataCreationDTO.getCountry())
                .newlyInfected(covidDataCreationDTO.getNewlyInfected())
                .allInfected(covidDataCreationDTO.getAllInfected())
                .newlyDeceased(covidDataCreationDTO.getNewlyDeceased())
                .allDeceased(covidDataCreationDTO.getAllDeceased())
                .build();
    }
}
