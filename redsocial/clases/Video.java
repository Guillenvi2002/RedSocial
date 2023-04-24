package org.example.redsocial.clases;



public class Video extends Recurso {


    public String calidad;

    public Integer tiempoSeg;

    public Video(String titulo, String calidad, Integer tiempoSeg) {
        super(titulo);
        this.calidad = calidad;
        this.tiempoSeg = tiempoSeg;
    }

    @Override
    public String getDetails(){

        return "Titulo: " + titulo + " Calidad del post: " + calidad
                + " Duración del post: " +tiempoSeg + " segundos";
    }
}
