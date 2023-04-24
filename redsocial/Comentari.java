package org.example.redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class Comentari {
    private static final Logger logger = LoggerFactory.getLogger(Comentari.class);

    public Date fechaComentario;

    public String textoComen;

    public String propietario;

    public Comentari() {
    }

    public Comentari(Date fechaComentario, String textoComen, String propietario) {
        this.fechaComentario = fechaComentario;
        this.textoComen = textoComen;
        this.propietario = propietario;
    }

    public Date getFechaComentario() {
        return fechaComentario;
    }

    public void setFechaComentario(Date fechaComentario) {
        this.fechaComentario = fechaComentario;
    }

    public String getTextoComen() {
        return textoComen;
    }

    public void setTextoComen(String textoComen) {
        this.textoComen = textoComen;
    }

    public String getPropietario() {
        return propietario;
    }

    public String verComentario() {

        return "El usuario " + propietario + " escribió: " + textoComen + " el " + fechaComentario;
    }
}
