package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.export.PdfReportExporter;
import net.sf.jasperreports.engine.JRException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by eduardo on 29-09-16.
 */
@SuppressWarnings("squid:S106")
public class ReportJdbcIT {


    @Test
    @Ignore
    public void generarReporteJsfSprinJpa() throws IOException, JRException, SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");

        try (
                InputStream jasperCompiladoStream = ReportJdbcIT.class.getResourceAsStream("/jasper/usuarios_jdbc.jasper");
                Connection connection = getConnection();
        ) {
            JasperReportsUtils utils = new JasperReportsUtilsImpl();
            //File file = utils.generarReporteConQuery(jasperCompiladoStream, null, connection, new PdfReportExporter());
            //System.out.println("file: " + file.getAbsolutePath());
            //Assert.assertTrue(file.length() > 100);
        }

    }

    @SuppressWarnings("squid:S2068")
    public static Connection getConnection() throws SQLException {

        Connection conn = null;
        Properties connectionProps = new Properties();
        connectionProps.put("user", "eduardo");
        connectionProps.put("password", "cambiame");

        conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/jsfspringjpa", connectionProps);

        System.out.println("Connected to database");
        return conn;
    }


}
