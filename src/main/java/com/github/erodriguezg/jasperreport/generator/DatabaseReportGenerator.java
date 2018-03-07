package com.github.erodriguezg.jasperreport.generator;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.InputStream;
import java.sql.Connection;
import java.util.Map;

public class DatabaseReportGenerator implements ReportGenerator {

    private final Connection connection;

    public DatabaseReportGenerator(Connection connection) {
        this.connection = connection;
    }

    @Override
    public JasperPrint generate(InputStream jasperCompilado, Map<String, Object> params) {
        try {
            return JasperFillManager.fillReport(jasperCompilado, params, connection);
        } catch (JRException e) {
            throw new IllegalStateException(e);
        }
    }

}