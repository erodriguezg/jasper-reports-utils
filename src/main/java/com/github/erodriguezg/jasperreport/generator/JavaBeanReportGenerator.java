package com.github.erodriguezg.jasperreport.generator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

public class JavaBeanReportGenerator implements ReportGenerator {

    private final Collection<?> collection;

    public JavaBeanReportGenerator(Collection<?> collection) {
        this.collection = collection;
    }

    @Override
    public JasperPrint generate(InputStream jasperCompilado, Map<String, Object> params) {
        try {
            return JasperFillManager.fillReport(jasperCompilado, params, new JRBeanCollectionDataSource(collection));
        } catch (JRException ex) {
            throw new IllegalStateException(ex);
        }
    }
}
