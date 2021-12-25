package com.blackpoint.covidmonitor.provider.control;

import com.blackpoint.covidmonitor.provider.entity.dto.DataProviderResponseDTO;

import java.io.IOException;

public interface DataProviderService {
    void loadData(String country) throws IOException, IllegalStateException;
}
