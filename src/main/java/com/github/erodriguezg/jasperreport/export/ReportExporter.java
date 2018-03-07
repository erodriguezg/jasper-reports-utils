package com.github.erodriguezg.jasperreport.export;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * Created by eduardo on 30-09-16.
 */
public abstract class ReportExporter {

    public File export(JasperPrint jasperPrint) {
        File outputFile;
        try {
            outputFile = File.createTempFile(UUID.randomUUID().toString(), ".temp." + getExtension());
            export(outputFile, jasperPrint);
            return outputFile;
        } catch (IOException  | JRException ex) {
            throw new IllegalStateException(ex);
        }
    }

    protected abstract String getExtension();

    protected abstract void export(File outputFile, JasperPrint jasperPrint) throws JRException;

}
