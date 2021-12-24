package com.blackpoint.covidmonitor.provider.control;

import com.blackpoint.covidmonitor.provider.scrapper.control.ScrapperService;
import org.jboss.logging.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;

@ApplicationScoped
public class CustomPolandDataProviderService implements DataProviderService {

    private static final String DATA_URL = "https://www.gov.pl/web/koronawirus/wykaz-zarazen-koronawirusem-sars-cov-2";

    @Inject
    Logger logger;

    @Inject
    ScrapperService scrapperService;

    @Override
    public void loadData() throws IOException, InterruptedException {
        logger.info("Data Provider initialized");
        logger.info("Starting to collect data...");
        Document document = Jsoup.connect(DATA_URL).get();
        Thread.sleep(10000);
        Element newlyInfected = document.select("span[style*='color:#e60000']").first();
        assert newlyInfected != null;
        logger.info(String.format("Found newly infected number: %s", newlyInfected.text()));
    }

    @Override
    public Long getNewlyInfected() {
        return null;
    }

    @Override
    public Long getAllInfected() {
        return null;
    }

    @Override
    public Long getNewlyDeceased() {
        return null;
    }

    @Override
    public Long getAllDeceased() {
        return null;
    }
}
