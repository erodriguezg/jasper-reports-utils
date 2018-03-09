package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.compilation.Compilator;
import com.github.erodriguezg.jasperreport.export.ReportExporter;
import com.github.erodriguezg.jasperreport.generator.ReportGenerator;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

/**
 * Created by eduardo on 30-09-16.
 */
public interface JasperReportsUtils {

    File compilar(InputStream jasperFuente, Compilator compilator);

    File crearReporte(File jasperCompilado, ReportGenerator generator, ReportExporter exporter);

    File crearReporte(InputStream jasperCompilado, ReportGenerator generator, ReportExporter exporter);

    File crearReporte(File jasperCompilado, Map<String, Object> params, ReportGenerator generator, ReportExporter exporter);

    File crearReporte(InputStream jasperCompilado, Map<String, Object> params, ReportGenerator generator, ReportExporter exporter);

}
