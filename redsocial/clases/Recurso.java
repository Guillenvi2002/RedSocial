package org.example.redsocial.clases;



public class Recurso {


    public String titulo;

    public Recurso(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
    public String getDetails(){
        return "Titulo del post: " + titulo;
    }
}
