package com.blackpoint.covidmonitor.provider.control;

import java.io.IOException;

public interface DataProviderService {
    void loadData() throws IOException, InterruptedException;
    Long getNewlyInfected();
    Long getAllInfected();
    Long getNewlyDeceased();
    Long getAllDeceased();
}
