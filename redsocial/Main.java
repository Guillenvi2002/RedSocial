package org.example.redsocial;

import org.example.redsocial.clases.Imagen;
import org.example.redsocial.clases.Recurso;
import org.example.redsocial.clases.Texto;
import org.example.redsocial.clases.Video;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static final Scanner teclado = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Comentari.class);
    static List<Usuario> usuarios = new ArrayList<>();
    static List<Posts> posts = new ArrayList<>();
    static List<Comentari> cList = new ArrayList<>();
    static Usuario usu;
    static Integer cont = 0;

    public static void main(String[] args) {
        int opcion;
        do {
            logger.info("-------------------------------------------");
            logger.info("Seleccione una opción:");
            logger.info("1-Opciones de crear");
            logger.info("2-Opciones del usuario");
            logger.info("3-Ver los posts de un usuario");
            logger.info("4-Ver los comentarios de un usuario");
            logger.info("5-Mostrar el número de comentarios que tiene un post");
            logger.info("6-Opciones de eliminar");
            logger.info("7-Salir");
            logger.info("-------------------------------------------");
            opcion = teclado.nextInt();
            teclado.nextLine();
            switch (opcion) {
                case 1:
                    Menu.menuCrear();
                    break;
                case 2:
                    Menu.menuOpcionesUsu();
                    break;
                case 3:
                    Usuario.verPostUsuario(usuarios);
                    break;
                case 4:
                    Usuario.verComentariosUsuario(usuarios, posts);
                    break;
                case 5:
                    Posts.contarComentariosPost(posts);
                    break;
                case 6:
                    Menu.eliminar();
                    break;
                default:
                    break;
            }
        } while (opcion != 7);
    }

    protected static void cambioSesion() {
        try {
            logger.info("Introduzca el nombre del usuario a iniciar sesión");
            String nomSes = teclado.nextLine();
            if (usuarios.size() > 1) {
                for (Usuario u : usuarios) {
                    if (u.getNombre().equals(nomSes)) {
                        if (u.getNombre().equals(usu.getNombre())) {
                            logger.info("Ya estas en la sesión de " + usu.getNombre());
                        } else {
                            usu = u;
                            logger.info("Cambio de sesión realizada. Bienvenido de nuevo " + usu.getNombre());
                        }
                    }
                }
            } else {
                logger.error("No puede iniciar sesión con otro usuario debido a que no hay ningun otro usuario");
            }


        } catch (Exception e) {
            logger.error("Error durante el cambio de sesión", e);
        }
    }

    protected static void crearUsuario() {
        try {
            // Crear limitaciones a la hora de crear un usuario
            List<Usuario> amigos = new ArrayList<>();
            List<Posts> posts = new ArrayList<>();
            boolean aceptar = true;
            logger.info("Introduce el nombre del usuario");
            String nomb = teclado.nextLine();
            for (Usuario u : usuarios) {
                aceptar = !u.nombre.equals(nomb);
            }
            if (aceptar) {
                usu = new Usuario(nomb, amigos, posts);
                usuarios.add(usu);
                logger.info("Usuario creado correctamente");
                logger.info("Bienvenido a nuestra red social " + usu.getNombre());
            } else {
                logger.error("Hay un usuario existente con el nombre introducido. Por favor, intente de nuevo");

            }


        } catch (Exception e) {
            logger.error("Ha habido un error durante la creación del usuario", e);
        }

    }

    protected static void crearComentario() {

        try {

            if (posts.size() == 0) {
                logger.error("No hay post donde comentar");
            } else {
                logger.info("En que post quieres comentar?");
                for (Posts p : posts) {
                    logger.info(p.getRecurso().getTitulo());
                }
                String titulo = teclado.nextLine();
                for (Posts test : posts) {
                    if (titulo.equals(test.getRecurso().getTitulo())) {
                        logger.info("Escribe el contenido de tu comentario");
                        String conte = teclado.nextLine();
                        Comentari c = new Comentari(new Date(), conte, usu.getNombre());
                        test.getComentaris().add(c);
                    }
                }
                logger.info("Comentario creado correctamente");
            }

        } catch (Exception e) {
            logger.error("Error durante la creación del comentario", e);
        }
    }

    // FUNCIONES DE CREAR UN POST DE CADA TIPO (basado en un criterio)
    protected static void crearPost(String criterio) {

        try {
            cList = new ArrayList<>();
            logger.info("Introduce el titulo de tu post");
            String titulo = teclado.nextLine();
            switch (criterio) {
                case "Texto": {
                    logger.info("Introduce el contenido de tu post");
                    String contenido = teclado.nextLine();
                    Recurso t = new Texto(titulo, contenido);
                    Posts p = new Posts(new Date(), cList, t);
                    usu.posts.add(p);
                    posts.add(p);
                    logger.info("Nuevo post añadido a la lista");
                    break;
                }
                case "Video": {
                    logger.info("Introduce la calidad de tu post");
                    String calidad = teclado.nextLine();
                    logger.info("Cual será la duración del video (en segundos)");
                    Integer tiempo = teclado.nextInt();
                    teclado.nextLine();
                    Recurso r = new Video(titulo, calidad, tiempo);
                    Posts p = new Posts(new Date(), cList, r);
                    usu.posts.add(p);
                    posts.add(p);
                    logger.info("Nuevo post añadido a la lista");
                    break;
                }
                case "Imagen": {
                    logger.info("Introduce el ancho de tu imagen");
                    Integer ancho = teclado.nextInt();
                    teclado.nextLine();
                    logger.info("Introduce el alto de tu imagen");
                    Integer alto = teclado.nextInt();
                    teclado.nextLine();
                    Recurso t = new Imagen(titulo, ancho, alto);
                    Posts p = new Posts(new Date(), cList, t);
                    usu.posts.add(p);
                    posts.add(p);
                    logger.info("Nuevo post añadido a la lista");
                    break;
                }
            }
        } catch (Exception e) {
            logger.error("Ha habido un error durante la creacion del post", e);
        }
    }

    // FUNCIONES DE ELIMINAR
    protected static void eliminarUsuario() {
        try {
            logger.info("Introduce el nombre del usuario a eliminar");
            String usuario = teclado.nextLine();
            for (Usuario u : usuarios) {
                if (u.nombre.equals(usu.getNombre())) {
                    logger.error("No te puedes borrar a ti mismo");
                } else {
                    if (u.getNombre().equals(usuario)) {
                        // Borra todos los comentarios que haya escrito el usuario
                        for (Posts p : posts) {
                            p.getComentaris().removeIf(c -> c.getPropietario().equals(usu.getNombre()));
                        }
                        usuarios.remove(u);
                        logger.info("Usuario eliminado correctamente");
                    }
                }

            }

        } catch (Exception e) {
            logger.error("Error durante la eliminación del usuario", e);
        }

    }

    // Función que borra el posts de un usuario
    protected static void eliminarPost() {
        try {
            if (usu.getPosts().size() == 0) {
                logger.error("No hay posts que eliminar");
            } else {
                logger.info("Introduce el titulo del post que quieres eliminar");
                for (Posts p : usu.getPosts()) {
                    logger.info(p.getRecurso().getTitulo());
                }
                String titu = teclado.nextLine();

                usu.posts.removeIf(p -> p.getRecurso().getTitulo().equals(titu));
                posts.removeIf(p -> p.getRecurso().getTitulo().equals(titu));

            }
        } catch (Exception e) {
            logger.error("Error durante la eliminación del post", e);
        }
    }

    protected static void eliminarComentario() {
        try {

            if (posts.size() == 0 || usu.getPosts().size() == 0) {
                logger.info("No hay posts para borrar comentarios");
            } else {
                logger.info("Introduce el titulo del post");
                for (Posts p : posts) {
                    logger.info(p.getRecurso().getTitulo());
                }
                String titu = teclado.nextLine();

                for (Posts p : posts) {
                    if (p.recurso.titulo.equals(titu)) {
                        logger.info("Que comentario quiere borrar, entre 0 y " + (p.getComentaris().size() - 1));
                        Integer num = teclado.nextInt();
                        teclado.nextLine();
                        // Revisar esto
                        for (int i = 0; i < p.getComentaris().size(); i++) {
                            if (num.equals(i)) {
                                p.getComentaris().remove(i);
                            }
                        }
                    }
                }
                logger.info("Comentario eliminado correctamente");
            }


        } catch (Exception e) {
            logger.error("Error durante el borrado del comentario", e);
        }
    }


}
