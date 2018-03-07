package com.github.erodriguezg.jasperreport.export;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.HtmlExporter;
import net.sf.jasperreports.export.*;

import java.io.File;

/**
 * Created by eduardo on 30-09-16.
 */
public class HtmlReportExporter extends ReportExporter {

    private HtmlReportConfiguration htmlReportConfiguration;
    private HtmlExporterConfiguration htmlExporterConfiguration;

    public HtmlReportExporter() {
        this.htmlReportConfiguration = new SimpleHtmlReportConfiguration();
        this.htmlExporterConfiguration = new SimpleHtmlExporterConfiguration();
    }

    public HtmlReportExporter(HtmlReportConfiguration htmlReportConfiguration, HtmlExporterConfiguration htmlExporterConfiguration) {
        this.htmlReportConfiguration = htmlReportConfiguration;
        this.htmlExporterConfiguration = htmlExporterConfiguration;
    }

    public HtmlReportConfiguration getHtmlReportConfiguration() {
        return htmlReportConfiguration;
    }

    public HtmlExporterConfiguration getHtmlExporterConfiguration() {
        return htmlExporterConfiguration;
    }

    @Override
    protected String getExtension() {
        return "html";
    }

    @Override
    protected void export(File outputFile, JasperPrint jasperPrint) throws JRException {
        HtmlExporter exporter = new HtmlExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleHtmlExporterOutput(outputFile));
        exporter.setConfiguration(this.htmlReportConfiguration);
        exporter.setConfiguration(this.htmlExporterConfiguration);
        exporter.exportReport();
    }
}
