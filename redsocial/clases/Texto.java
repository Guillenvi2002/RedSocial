package org.example.redsocial.clases;


public class Texto  extends Recurso {


    public String contenido;

    public Texto(String titulo, String contenido) {
        super(titulo);
        this.contenido = contenido;
    }

    @Override
    public String getDetails(){
        return "Titulo: " + titulo + " contenido del post: " +contenido;
    }
}
