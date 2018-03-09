package com.github.erodriguezg.jasperreport;

import com.github.erodriguezg.jasperreport.cache.HashMapTmpJasperCache;
import com.github.erodriguezg.jasperreport.cache.JasperCache;
import com.github.erodriguezg.jasperreport.compilation.FileTmpCompilator;
import com.github.erodriguezg.jasperreport.export.HtmlReportExporter;
import com.github.erodriguezg.jasperreport.export.PdfReportExporter;
import com.github.erodriguezg.jasperreport.export.ReportExporter;
import com.github.erodriguezg.jasperreport.export.XlsxReportExporter;
import com.github.erodriguezg.jasperreport.generator.DatabaseReportGenerator;
import com.github.erodriguezg.jasperreport.generator.ReportGenerator;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;

/**
 * Created by eduardo on 29-09-16.
 */
@SuppressWarnings("squid:S106")
public class ReportJdbcIT {

    private static final String REPORTE_JDBC = "reporte_jdbc";

    private static final String ST_CREATE_TABLE_PERFIL =
            "CREATE TABLE perfil ( " +
                    "        id_perfil int4 NOT NULL, " +
                    "        nombre varchar(250), " +
                    "CONSTRAINT pk_perfil PRIMARY KEY (id_perfil))";

    private static final String ST_CREATE_TABLE_USUARIO =
            "CREATE TABLE usuario ( " +
                    "                rut int4 NOT NULL, " +
                    "                nombres varchar(250), " +
                    "apellidos varchar(250), " +
                    "password varchar(250), " +
                    "email varchar(250), " +
                    "fecha_nacimiento date, " +
                    "perfil_id int4, " +
                    "habilitado boolean, " +
                    "CONSTRAINT pk_usuario PRIMARY KEY (rut), " +
                    "CONSTRAINT fk_usuario FOREIGN KEY (perfil_id) " +
                    "REFERENCES perfil (id_perfil))";

    private static final String ST_INSERT_PERFIL =
            "INSERT INTO perfil (id_perfil, nombre) VALUES (1, 'Super administrador')";

    private static final String ST_INSERT_USUARIO =
            "INSERT INTO usuario (rut, perfil_id, nombres, apellidos, password, fecha_nacimiento, email, habilitado) " +
                    "VALUES (11111111, 1, 'Persona 1', 'Apellido', '70be2932a9786b17a1351b8d3b9fdf22', '1990-01-01', 'usuario1@zeke.cl', 'true')";


    private static final String ST_QUERY_TEST =
            "select * from usuario";

    private static ReportGenerator reportGenerator;
    private static JasperReportsUtils utils;
    private static JasperCache jasperCache;

    @BeforeClass
    public static void crearBaseDatos() throws ClassNotFoundException, IOException, SQLException {

        System.out.println("creando base datos en memoria");

        Class.forName("org.h2.Driver");
        try {
            Connection conn = getConnection();
            Statement st = conn.createStatement();
            st.execute(ST_CREATE_TABLE_PERFIL);
            st.execute(ST_CREATE_TABLE_USUARIO);
            st.execute(ST_INSERT_PERFIL);
            st.execute(ST_INSERT_USUARIO);
            conn.commit();
            ResultSet resultSet = st.executeQuery(ST_QUERY_TEST);
            if (resultSet.next()) {
                System.out.println("se encontro usuario " + resultSet.getString("rut"));
            } else {
                throw new IllegalStateException("no se encontraron usuarios");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("creando componentes");

        reportGenerator = new DatabaseReportGenerator(getConnection());
        utils = new JasperReportsUtilsImpl();
        jasperCache = new HashMapTmpJasperCache();
        try (
                InputStream fuenteReporte = ReportJavaBeansIT.class.getResourceAsStream("/jrxml/usuarios_jdbc.jrxml");
        ) {
            jasperCache.putJasper(REPORTE_JDBC, utils.compilar(fuenteReporte, new FileTmpCompilator()));
        }
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
        File file = utils.crearReporte(jasperCache.getJasper(REPORTE_JDBC), reportGenerator, exporter);
        System.out.println("file: " + file.getAbsolutePath());
        Assert.assertTrue(file.length() > 100);
    }

    @SuppressWarnings("squid:S2068")
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:mem:db1", "sa", "");
    }

}
