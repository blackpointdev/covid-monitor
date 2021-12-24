package com.blackpoint.covidmonitor.domain.boundary;

import com.blackpoint.covidmonitor.domain.boundary.mapper.CovidDataDTOMapper;
import com.blackpoint.covidmonitor.domain.control.CovidDataService;
import com.blackpoint.covidmonitor.domain.entity.CovidDataEntity;
import com.blackpoint.covidmonitor.domain.entity.dto.CovidDataCreationDTO;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@GraphQLApi
public class CovidDataResource {

    @Inject
    CovidDataService covidDataService;

    @Inject
    CovidDataDTOMapper covidDataDTOMapper;

    @Inject
    Logger logger;

    @Query
    @Description("Get all COVID-19 data")
    public List<CovidDataEntity> getAllData(@Name("limit") Integer limit) {
        return covidDataService.getNewestData(limit);
    }

    @Query
    @Description("Get COVID-19 data by ID")
    @Transactional
    public CovidDataEntity getDataById(@Name("entityId") Long id) {
        return covidDataService.getDataById(id);
    }

    @Query
    @Description("Get COVID-19 data by date")
    @Transactional
    public List<CovidDataEntity> getDataByDate(@Name("date") String date) {
        try {
            return covidDataService.getDataByDate(date);
        } catch (ParseException parseException) {
            logger.error("Input date cannot be parsed (incorrect format)");
            return Collections.singletonList(CovidDataEntity.builder().build());
        }
    }

    @Mutation
    @Description("Create Covid Data Entry")
    @Transactional
    public CovidDataEntity createEntry(CovidDataCreationDTO covidDataCreationDTO) {
        try {
            return covidDataService.createEntry(covidDataDTOMapper.mapToEntity(covidDataCreationDTO));
        } catch (ParseException parseException) {
            logger.error("Input date cannot be parsed (incorrect format)");
            return CovidDataEntity.builder().build();
        }
    }
}