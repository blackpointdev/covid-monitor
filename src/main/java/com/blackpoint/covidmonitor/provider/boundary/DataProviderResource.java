package com.blackpoint.covidmonitor.provider.boundary;

import com.blackpoint.covidmonitor.provider.control.DataProviderService;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.jboss.logging.Logger;

import javax.inject.Inject;

@GraphQLApi
public class DataProviderResource {
    @Inject
    Logger logger;

    @Inject
    DataProviderService dataProviderService;

    @Query
    @Description("Start Data Provider")
    public boolean startDataProvider() {
        try {
            dataProviderService.loadData();
            return true;
        } catch (Exception exception) {
            logger.error("Cannot load given URL: " + exception.getMessage());
            return false;
        }
    }
}
