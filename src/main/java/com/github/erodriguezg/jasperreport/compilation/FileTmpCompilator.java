package com.github.erodriguezg.jasperreport.compilation;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;

import java.io.*;
import java.util.UUID;

public class FileTmpCompilator implements Compilator {

    @Override
    public File compile(InputStream inputStreamReport) {
        try {
            File fileTmp = File.createTempFile(UUID.randomUUID().toString(), ".tmp.jasper");
            try (OutputStream outputStream = new FileOutputStream(fileTmp)) {
                JasperCompileManager.compileReportToStream(inputStreamReport, outputStream);
            }
            return fileTmp;
        } catch (IOException | JRException ex) {
            throw new IllegalStateException(ex);
        }
    }

}
