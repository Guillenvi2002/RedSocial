package org.example.redsocial.clases;


public class Imagen extends Recurso {



    public Integer ancho;

    public Integer alto;

    public Imagen(String titulo, Integer ancho, Integer alto) {
        super(titulo);
        this.ancho = ancho;
        this.alto = alto;
    }

    @Override
    public String getDetails(){
        return "Titulo: " + titulo + " Ancho y alto del post: " + ancho + " " + alto;
    }
}
