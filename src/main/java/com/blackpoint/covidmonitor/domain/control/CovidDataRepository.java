package com.blackpoint.covidmonitor.domain.control;

import com.blackpoint.covidmonitor.domain.control.mapper.CovidDataMapper;
import com.blackpoint.covidmonitor.domain.entity.CovidDataEntity;
import com.blackpoint.covidmonitor.domain.entity.jpa.CovidDataJPA;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.*;
import java.util.Date;
import java.util.List;

@ApplicationScoped
public class CovidDataRepository {

    @Inject
    EntityManager entityManager;

    @Inject
    CovidDataMapper covidDataMapper;

    public List<CovidDataEntity> getNewestData(Integer limit) {
        TypedQuery<CovidDataJPA> query = entityManager.createNamedQuery("CovidData.FindNewestWithLimit", CovidDataJPA.class);
        query.setMaxResults(limit);
        return covidDataMapper.mapToEntities(query.getResultList());
    }

    public CovidDataEntity getDataById(Long id) {
        return covidDataMapper.mapToEntity(entityManager.find(CovidDataJPA.class, id));
    }

    public List<CovidDataEntity> getDataByDate(Date date) {
        TypedQuery<CovidDataJPA> query = entityManager.createNamedQuery("CovidData.FindByDate", CovidDataJPA.class);
        query.setParameter("input_date", date);
        return covidDataMapper.mapToEntities(query.getResultList());
    }

    public CovidDataEntity createEntry(CovidDataEntity covidDataEntity) {
        CovidDataJPA entryToCreate = covidDataMapper.mapToJPA(covidDataEntity);
        entityManager.persist(entryToCreate);
        entityManager.flush();
        return covidDataMapper.mapToEntity(entryToCreate);
    }
}
