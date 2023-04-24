package org.example.redsocial;

import org.example.redsocial.clases.Recurso;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class Posts {

    private static final Logger logger = LoggerFactory.getLogger(Posts.class);
    private static final Scanner teclado = new Scanner(System.in);

    public Date fecha;

    public List<Comentari> comentaris;

    public Recurso recurso;


    public Posts(Date fecha, List<Comentari> comentaris, Recurso recurso) {
        this.fecha = fecha;
        this.comentaris = comentaris;
        this.recurso = recurso;
    }

    protected static void contarComentariosPost(List<Posts> posts) {
        try {
            if (posts.size() == 0) {
                logger.info("No hay posts");
            } else {
                logger.info("En que post quieres comentar?");
                for (Posts p : posts) {
                    logger.info(p.recurso.titulo);
                }
                String titulo = teclado.nextLine();
                for (Posts test : posts) {
                    if (titulo.equals(test.recurso.titulo)) {
                        logger.info("En este post de titulo " + titulo + " hay " + test.comentaris.size() + " comentarios");
                    }
                }
            }

        } catch (Exception e) {
            logger.error("Ha habido un error al contar los posts", e);
        }

    }

    public List<Comentari> getComentaris() {
        return comentaris;
    }

    public Recurso getRecurso() {
        return recurso;
    }

}
