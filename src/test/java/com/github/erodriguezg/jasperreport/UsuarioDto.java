package com.github.erodriguezg.jasperreport;

import java.io.Serializable;

/**
 * Created by eduardo on 30-09-16.
 */
public class UsuarioDto implements Serializable {

    private String rut;

    private String nombre;

    private String fecha;

    private String rol;

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
