package com.github.erodriguezg.jasperreport.export;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ooxml.JRXlsxExporter;
import net.sf.jasperreports.export.*;

import java.io.File;

/**
 * Created by eduardo on 30-09-16.
 */
public final class XlsxReportExporter extends ReportExporter {

    private XlsxExporterConfiguration xlsxExporterConfiguration;
    private XlsxReportConfiguration xlsxReportConfiguration;


    public XlsxReportExporter() {
        this.xlsxExporterConfiguration = new SimpleXlsxExporterConfiguration();
        this.xlsxReportConfiguration = new SimpleXlsxReportConfiguration();
    }

    public XlsxReportExporter(XlsxExporterConfiguration xlsxExporterConfiguration, XlsxReportConfiguration xlsxReportConfiguration) {
        this.xlsxExporterConfiguration = xlsxExporterConfiguration;
        this.xlsxReportConfiguration = xlsxReportConfiguration;
    }

    public XlsxExporterConfiguration getXlsxExporterConfiguration() {
        return xlsxExporterConfiguration;
    }

    public XlsxReportConfiguration getXlsxReportConfiguration() {
        return xlsxReportConfiguration;
    }

    @Override
    protected String getExtension() {
        return "xlsx";
    }

    @Override
    protected void export(File outputFile, JasperPrint jasperPrint) throws JRException {
        JRXlsxExporter exporter = new JRXlsxExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        exporter.setConfiguration(this.xlsxExporterConfiguration);
        exporter.setConfiguration(this.xlsxReportConfiguration);
        exporter.exportReport();
    }
}
