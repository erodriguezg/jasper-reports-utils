package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.export.ReportExporter;
import com.github.erodriguezg.jasperreport.generator.ReportGenerator;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eduardo on 30-09-16.
 */
public class JasperReportsUtilsImpl implements JasperReportsUtils {

    @Override
    public File crearReporte(InputStream jasperCompilado, ReportGenerator generator, ReportExporter exporter) {
        return this.crearReporte(jasperCompilado, new HashMap<>(), generator, exporter);
    }

    @Override
    public File crearReporte(InputStream jasperCompilado, Map<String, Object> params, ReportGenerator generator, ReportExporter exporter) {
        this.validarNulosComunes(jasperCompilado, generator, exporter);
        JasperPrint jasperPrint = generator.generate(jasperCompilado, params);
        return exporter.export(jasperPrint);
    }

    private void validarNulosComunes(InputStream jasperCompilado, ReportGenerator generator, ReportExporter exporter) {
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
