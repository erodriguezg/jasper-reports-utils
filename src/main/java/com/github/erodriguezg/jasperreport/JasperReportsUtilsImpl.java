package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.compilation.Compilator;
import com.github.erodriguezg.jasperreport.export.ReportExporter;
import com.github.erodriguezg.jasperreport.generator.ReportGenerator;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardo on 30-09-16.
 */
public class JasperReportsUtilsImpl implements JasperReportsUtils {

    @Override
    public File compilar(InputStream jasperFuente, Compilator compilator) {
        this.validarNulosCompilar(jasperFuente, compilator);
        return compilator.compile(jasperFuente);
    }

    @Override
    public File crearReporte(File jasperCompilado, ReportGenerator generator, ReportExporter exporter) {
        try (InputStream inputStream = new FileInputStream(jasperCompilado)) {
            return this.crearReporte(inputStream, generator, exporter);
        }catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public File crearReporte(InputStream jasperCompilado, ReportGenerator generator, ReportExporter exporter) {
        return this.crearReporte(jasperCompilado, new HashMap<>(), generator, exporter);
    }

    @Override
    public File crearReporte(File jasperCompilado, Map<String, Object> params, ReportGenerator generator, ReportExporter exporter) {
        try (InputStream inputStream = new FileInputStream(jasperCompilado)) {
            return this.crearReporte(inputStream, params, generator, exporter);
        }catch (IOException ex) {
            throw new IllegalStateException(ex);
        }
    }

    @Override
    public File crearReporte(InputStream jasperCompilado, Map<String, Object> params, ReportGenerator generator, ReportExporter exporter) {
        this.validarNulosCrearReporte(jasperCompilado, generator, exporter);
        JasperPrint jasperPrint = generator.generate(jasperCompilado, params);
        return exporter.export(jasperPrint);
    }

    private void validarNulosCompilar(InputStream jasperFuente, Compilator compilator) {
        if(jasperFuente == null) {
            throw new IllegalArgumentException("Jasper Fuente es nulo!");
        }
        if(compilator == null) {
            throw new IllegalArgumentException("Compilator es nulo!");
        }
    }

    private void validarNulosCrearReporte(InputStream jasperCompilado, ReportGenerator generator, ReportExporter exporter) {
        if (jasperCompilado == null) {
            throw new IllegalArgumentException("Jasper compilado es nulo!");
        }
        if (generator == null) {
            throw new IllegalArgumentException("ReportGenerator es nulo!");
        }
        if (exporter == null) {
            throw new IllegalArgumentException("ReportExporter es nulo!");
        }
    }

}
