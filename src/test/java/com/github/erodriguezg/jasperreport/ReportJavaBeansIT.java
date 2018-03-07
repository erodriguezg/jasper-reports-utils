package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.export.HtmlReportExporter;
import com.github.erodriguezg.jasperreport.export.PdfReportExporter;
import com.github.erodriguezg.jasperreport.export.ReportExporter;
import com.github.erodriguezg.jasperreport.export.XlsxReportExporter;
import com.github.erodriguezg.jasperreport.generator.JavaBeanReportGenerator;
import com.github.erodriguezg.jasperreport.generator.ReportGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by eduardo on 29-09-16.
 */
@SuppressWarnings("squid:S106")
public class ReportJavaBeansIT {

    private ReportGenerator reportGenerator;
    private JasperReportsUtils utils;

    @Before
    public void before() {
        reportGenerator = new JavaBeanReportGenerator(IReportFactory.getUsuarioDtoList());
        utils = new JasperReportsUtilsImpl();
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
        try (
                InputStream jasperCompiladoStream = ReportJdbcIT.class.getResourceAsStream("/jasper/usuarios_javabeans.jasper");
        ) {
            File file = utils.crearReporte(jasperCompiladoStream, reportGenerator, exporter);
            System.out.println("file: " + file.getAbsolutePath());
            Assert.assertTrue(file.length() > 100);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

}
