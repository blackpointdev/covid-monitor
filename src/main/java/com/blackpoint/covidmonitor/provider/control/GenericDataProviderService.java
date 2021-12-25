package com.blackpoint.covidmonitor.provider.control;

import com.blackpoint.covidmonitor.domain.control.CovidDataService;
import com.blackpoint.covidmonitor.domain.entity.CovidDataEntity;
import com.blackpoint.covidmonitor.provider.entity.dto.DataProviderResponseDTO;
import graphql.com.google.common.collect.ImmutableMap;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.jboss.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.core.UriBuilder;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.time.LocalDate;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@ApplicationScoped
public class GenericDataProviderService implements DataProviderService {

    private static final String COUNTRY_DATA_URL = "https://www.worldometers.info/coronavirus/country/{country}/";
    private static final String ALL_DATA_URL = "https://www.worldometers.info/coronavirus/country/{country}/";
    private static final String FAILED_TO_LOAD_MESSAGE = "Couldn't load data for country '%s'";
    private static final String COUNTRY_PARAMETER_NAME = "country";

    private static final Pattern numericDataPattern = Pattern.compile("\\d+");

    @Inject
    Logger logger;

    @Inject
    CovidDataService covidDataService;

    @Override
    public void loadData(String country) throws IOException, IllegalStateException {
        URI dataUri = buildUri(country);
        Document document = Jsoup.connect(dataUri.toString()).get();
        Element newlyInfected = document.select("div.news_post").first();
        if (Objects.isNull(newlyInfected)) {
            throw new IllegalStateException(String.format(FAILED_TO_LOAD_MESSAGE, country));
        }
        String textData = newlyInfected.text().replace(",", "");
        Matcher matcher = numericDataPattern.matcher(textData);
        CovidDataEntity covidDataEntity = buildCovidDataEntity(matcher, country);
        logger.info(String.format("Infected: %d, deceased: %d", covidDataEntity.getNewlyDeceased(),
                covidDataEntity.getNewlyDeceased()));
        logger.info("Persisting fetched data...");
        Long createdId = covidDataService.createEntry(covidDataEntity).getId();
        logger.info(String.format("Persisted with ID: %d", createdId));
    }

    private URI buildUri(String country) {
        Map<String, String> countryByParameterName = ImmutableMap.of(COUNTRY_PARAMETER_NAME, country);
        UriBuilder builder = UriBuilder.fromPath(COUNTRY_DATA_URL);
        return builder.buildFromMap(countryByParameterName);
    }

    private CovidDataEntity buildCovidDataEntity(Matcher matcher, String country) {
        List<Long> numericData = new ArrayList<>();
        while(matcher.find()) {
            numericData.add(Long.parseLong(matcher.group()));
        }

        return CovidDataEntity.builder()
                .date(LocalDate.now())
                .country(country)
                .newlyInfected(numericData.get(0))
                .newlyDeceased(numericData.get(1))
                .build();
    }
}
