package com.github.erodriguezg.jasperreport.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class HashMapTmpJasperCache implements JasperCache {

    private static final Logger log = LoggerFactory.getLogger(HashMapTmpJasperCache.class);

    private final HashMap<String, File> map;

    public HashMapTmpJasperCache() {
        map = new HashMap<>();
    }

    @Override
    public void putJasper(String key, File jasperFile) {
        this.map.put(key, jasperFile);
    }

    @Override
    public File getJasper(String key) {
        return this.map.get(key);
    }

    @Override
    public boolean remove(String key) {
        File file = this.map.remove(key);
        if(file == null) {
            return false;
        }
        deleteFile(file);
        return true;
    }

    @Override
    public void cleanAll() {
        for(Map.Entry<String,File> entry : this.map.entrySet()) {
            deleteFile(entry.getValue());
        }
        this.map.clear();
    }

    private void deleteFile(File file) {
        if(file != null && file.exists()) {
            try {
                Files.delete(file.toPath());
            } catch (IOException e) {
                log.warn("no se pudo eliminar file: ", e);
            }
        }
    }

}
