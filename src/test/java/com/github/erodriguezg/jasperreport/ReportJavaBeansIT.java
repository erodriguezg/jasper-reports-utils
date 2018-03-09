package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.cache.HashMapTmpJasperCache;
import com.github.erodriguezg.jasperreport.cache.JasperCache;
import com.github.erodriguezg.jasperreport.compilation.FileTmpCompilator;
import com.github.erodriguezg.jasperreport.export.HtmlReportExporter;
import com.github.erodriguezg.jasperreport.export.PdfReportExporter;
import com.github.erodriguezg.jasperreport.export.ReportExporter;
import com.github.erodriguezg.jasperreport.export.XlsxReportExporter;
import com.github.erodriguezg.jasperreport.generator.JavaBeanReportGenerator;
import com.github.erodriguezg.jasperreport.generator.ReportGenerator;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eduardo on 29-09-16.
 */
@SuppressWarnings("squid:S106")
public class ReportJavaBeansIT {

    private static final String REPORTE_JAVABEANS = "reporte_javabeans";

    private static ReportGenerator reportGenerator;
    private static JasperReportsUtils utils;
    private static JasperCache jasperCache;

    @BeforeClass
    public static void beforeClass() throws IOException {
        reportGenerator = new JavaBeanReportGenerator(IReportFactory.getUsuarioDtoList());
        utils = new JasperReportsUtilsImpl();
        jasperCache = new HashMapTmpJasperCache();
        try (
                InputStream fuenteReporte = ReportJavaBeansIT.class.getResourceAsStream("/jrxml/usuarios_javabeans.jrxml");
        ) {
            jasperCache.putJasper(REPORTE_JAVABEANS, utils.compilar(fuenteReporte, new FileTmpCompilator()));
        }
    }

    @AfterClass
    public static void afterClass() {
        jasperCache.cleanAll();
    }

    @Test
    public void testPDF() {
        test(new PdfReportExporter());
    }

    @Test
    public void testEXCEL() {
        test(new XlsxReportExporter());
    }

    @Test
    public void testHTML() {
        test(new HtmlReportExporter());
    }

    private void test(ReportExporter exporter) {
        File file = utils.crearReporte(jasperCache.getJasper(REPORTE_JAVABEANS), reportGenerator, exporter);
        System.out.println("file: " + file.getAbsolutePath());
        Assert.assertTrue(file.length() > 100);
    }

}
