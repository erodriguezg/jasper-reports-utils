package com.github.erodriguezg.jasperreport.compilation;

import java.io.File;
import java.io.InputStream;

public interface Compilator {

    File compile(InputStream inputStreamReport);

}
