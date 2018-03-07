package com.github.erodriguezg.jasperreport.export;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.*;

import java.io.File;


public final class PdfReportExporter extends ReportExporter {

    private PdfExporterConfiguration pdfExporterConfiguration;
    private PdfReportConfiguration pdfReportConfiguration;

    public PdfReportExporter() {
        this.pdfExporterConfiguration = new SimplePdfExporterConfiguration();
        this.pdfReportConfiguration = new SimplePdfReportConfiguration();
    }

    public PdfReportExporter(PdfExporterConfiguration pdfExporterConfiguration, PdfReportConfiguration pdfReportConfiguration) {
        this.pdfExporterConfiguration = pdfExporterConfiguration;
        this.pdfReportConfiguration = pdfReportConfiguration;
    }

    public PdfExporterConfiguration getPdfExporterConfiguration() {
        return pdfExporterConfiguration;
    }

    public PdfReportConfiguration getPdfReportConfiguration() {
        return pdfReportConfiguration;
    }

    @Override
    protected String getExtension() {
        return "pdf";
    }

    @Override
    protected void export(File outputFile, JasperPrint jasperPrint) throws JRException {
        JRPdfExporter exporter = new JRPdfExporter();
        exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
        exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputFile));
        exporter.setConfiguration(this.pdfExporterConfiguration);
        exporter.setConfiguration(this.pdfReportConfiguration);
        exporter.exportReport();
    }
}
