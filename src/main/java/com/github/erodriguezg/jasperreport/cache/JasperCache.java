package com.github.erodriguezg.jasperreport.cache;

import java.io.File;

public interface JasperCache {

    void putJasper(String key, File jasperFile);

    File getJasper(String key);

    boolean remove(String key);

    void cleanAll();

}
