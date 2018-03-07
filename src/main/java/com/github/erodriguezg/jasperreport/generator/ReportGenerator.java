package com.github.erodriguezg.jasperreport.generator;

import net.sf.jasperreports.engine.JasperPrint;

import java.io.InputStream;
import java.util.Map;

public interface ReportGenerator {

    JasperPrint generate(InputStream jasperCompilado, Map<String, Object> params);

}
