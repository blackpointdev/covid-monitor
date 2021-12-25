package com.blackpoint.covidmonitor.domain.control;

import com.blackpoint.covidmonitor.domain.entity.CovidDataEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CovidDataService {
    @Inject
    CovidDataRepository covidDataRepository;

    @Transactional
    public List<CovidDataEntity> getNewestData(Integer limit) {
        return covidDataRepository.getNewestData(limit);
    }

    @Transactional
    public CovidDataEntity getDataById(Long id) {
        return covidDataRepository.getDataById(id);
    }

    @Transactional
    public List<CovidDataEntity> getDataByDate(String date) throws ParseException {
        Date parsedDate = new SimpleDateFormat("dd-MM-yyy").parse(date);
        return covidDataRepository.getDataByDate(parsedDate);
    }

    @Transactional
    public CovidDataEntity createEntry(CovidDataEntity covidDataEntity) {
        return covidDataRepository.createEntry(covidDataEntity);
    }
}
