package com.blackpoint.covidmonitor.domain.entity.jpa;

import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "covid_data")
@Getter
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
@NamedQueries({
        @NamedQuery(name = "CovidData.FindNewestWithLimit", query = "select d from CovidDataJPA d"),
        @NamedQuery(name = "CovidData.FindByDate", query = "select d from CovidDataJPA d where d.x_date = :input_date")
})
public class CovidDataJPA {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date x_date;
    private String country;

    @Column(name = "newly_infected")
    private Long newlyInfected;

    @Column(name = "all_infected")
    private Long allInfected;

    @Column(name = "newly_deceased")
    private Long newlyDeceased;

    @Column(name = "all_deceased")
    private Long allDeceased;
}
