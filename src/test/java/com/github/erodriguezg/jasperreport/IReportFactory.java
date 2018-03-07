package com.github.erodriguezg.jasperreport;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by eduardo on 30-09-16.
 */
public final class IReportFactory {

    private IReportFactory() {
        //cerrado
    }

    public static List<UsuarioDto> getUsuarioDtoList() {
        List<UsuarioDto> usuarios = new ArrayList<>();
        for(int i = 1; i < 100; i++) {
            UsuarioDto u = new UsuarioDto();
            u.setFecha("01/04/2015");
            u.setNombre("Rocío Rodriguez");
            u.setRut("24.123.123-5");
            u.setRol("Administrador");
            usuarios.add(u);
        }
        return usuarios;
    }


}
