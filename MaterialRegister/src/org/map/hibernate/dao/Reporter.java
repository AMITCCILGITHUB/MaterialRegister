package org.map.hibernate.dao;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import org.map.hibernate.ddo.HeatChartMaster;
import org.map.hibernate.ddo.MaterialMaster;
import org.map.utils.AppProperties;

public class Reporter {

    public static String printMaterialRegisterReport(List<MaterialMaster> data) throws JRException, IOException {
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(data);
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

        Map parameters = new HashMap();

        File matFile = new File("resources/ireport/MaterialRegister.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(matFile);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        File pdf = new File(AppProperties.getValue("report.pdf.path") + "/MaterialRegister_" + sdf.format(Calendar.getInstance().getTime()) + ".pdf");
        JasperExportManager.exportReportToPdfFile(jasperPrint, pdf.getAbsolutePath());

        return pdf.getAbsolutePath();
    }

    public static String printHeatChartReport(HeatChartMaster data) throws JRException, IOException, URISyntaxException {
        JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(Arrays.asList(data));
        SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyyHHmmss");

        Map parameters = new HashMap();
        File subReportFile = new File("resources/ireport/");
        parameters.put("SUBREPORT_DIR", subReportFile.getAbsolutePath());

        File heatFile = new File("resources/ireport/HeatChart.jrxml");
        JasperDesign jasperDesign = JRXmlLoader.load(heatFile);
        JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
        File pdf = new File(AppProperties.getValue("report.pdf.path") + "/HeatChart_" + sdf.format(Calendar.getInstance().getTime()) + ".pdf");
        JasperExportManager.exportReportToPdfFile(jasperPrint, pdf.getAbsolutePath());

        return pdf.getAbsolutePath();
    }
}
