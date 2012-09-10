package org.map.service;

import java.io.IOException;
import java.net.URISyntaxException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JRException;
import org.map.hibernate.dao.Reporter;
import org.map.logger.LoggerUtil;
import org.map.view.ViewHeatChart;

public class PrintHeatChart extends Service<String> {

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {

            @Override
            protected String call() {
                String reportPath = null;
                try {
                    reportPath = Reporter.printHeatChartReport(ViewHeatChart.getViewHeatChart().getHeatChart());
                } catch (JRException | IOException | URISyntaxException ex) {
                    LoggerUtil.getLogger().debug(ex);
                }
                return reportPath;
            }
        };
    }
}