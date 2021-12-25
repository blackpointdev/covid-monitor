package com.blackpoint.covidmonitor.provider.boundary;

import com.blackpoint.covidmonitor.provider.control.DataProviderService;
import com.blackpoint.covidmonitor.provider.entity.dto.DataProviderResponseDTO;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import java.io.IOException;

@GraphQLApi
public class DataProviderResource {

    private static final String SUCCESS_MESSAGE = "Data loaded successfully";

    @Inject
    Logger logger;

    @Inject
    DataProviderService dataProviderService;

    @Query
    @Description("Start Data Provider")
    public DataProviderResponseDTO startDataProvider(@Name("country") String country) {
        try {
            dataProviderService.loadData(country);
            return buildResponseDTO(SUCCESS_MESSAGE);
        } catch (Exception exception) {
            logger.error("Data Provider exception: " + exception.getMessage());
            return buildResponseDTO(exception.getMessage());
        }
    }

    private DataProviderResponseDTO buildResponseDTO(String message) {
        return DataProviderResponseDTO.builder()
                .message(message)
                .build();
    }
}
