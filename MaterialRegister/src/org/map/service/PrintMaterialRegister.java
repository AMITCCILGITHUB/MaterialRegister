package org.map.service;

import java.io.IOException;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JRException;
import org.map.hibernate.dao.Reporter;
import org.map.logger.LoggerUtil;
import org.map.view.ViewReport;

public class PrintMaterialRegister extends Service<String> {

    @Override
    protected Task<String> createTask() {
        return new Task<String>() {

            @Override
            protected String call() {
                String reportPath = null;
                try {
                    reportPath = Reporter.printMaterialRegisterReport(ViewReport.getViewReport().getData());
                } catch (JRException | IOException ex) {
                    LoggerUtil.getLogger().debug(ex);
                }
                return reportPath;
            }
        };
    }
}