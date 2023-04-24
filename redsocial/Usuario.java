package org.example.redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class Usuario {
    private static final Scanner teclado = new Scanner(System.in);
    private static final Logger logger = LoggerFactory.getLogger(Usuario.class);

    public String nombre;

    public List<Usuario> seguidos;

    public List<Posts> posts;

    public Usuario() {
    }

    public Usuario(String nombre, List<Usuario> seguidos, List<Posts> posts) {
        this.nombre = nombre;
        this.seguidos = seguidos;
        this.posts = posts;
    }

    // Función que muestra a todos los seguidores del usuario actual
    protected static void verAmigos(Usuario usu) {
        try {
            if (usu.getSeguidos().size() == 0) {
                logger.info("No sigues a nadie");
            } else {
                logger.info("Tu lista de amigos consiste en: ");
                for (Usuario u : usu.getSeguidos()) {
                    logger.info(u.getNombre());
                }
            }

        } catch (Exception e) {
            logger.error("Error a la hora de mostrar amigos", e);
        }

    }

    // Funcion que sugiere usuarios para seguir
    protected static void sugerirAmigos(Usuario usu, List<Usuario> usuarios) {
        // Busca en los usuarios que sigue el usuario que se encuentra en la sesión
        for (Usuario u : usu.getSeguidos()) {
            // Miramos en todos los usuarios que existen en el momento de la ejecución del programa
            for (Usuario test : usuarios) {
                // Hacemos una comprobacion para la sugerencia de seguidores
                if (u.getSeguidos().contains(test) && !test.getSeguidos().contains(usu)) {
                    logger.info("Puedes seguir a " + test.getNombre());
                }
            }
        }
    }

    // Funcion que muestra todos los comentarios del usuario a seleccionar
    protected static void verComentariosUsuario(List<Usuario> usuarios, List<Posts> posts) {
        try {
            boolean noHayComentario = false;
            logger.info("Introduce el nombre del usuario para buscar sus comentarios");
            String nombre = teclado.nextLine();
            for (Usuario u : usuarios) {
                if (u.getNombre().equals(nombre)) {
                    for (Posts p : posts) {
                        if (p.getComentaris().size() == 0) {
                            noHayComentario = true;
                        } else {
                            for (Comentari c : p.getComentaris()) {
                                if (c.getPropietario().equals(u.getNombre())) {
                                    logger.info(c.verComentario());
                                }
                            }
                        }

                    }
                }
            }
            if (noHayComentario) {
                logger.info("El usuario introducido no ha escrito ningun comentario");
            }

        } catch (Exception e) {
            logger.error("No se ha podido mostrar los comentarios", e);
        }
    }

    // Funcion que muestra el mural del usuario que inicie sesion
    protected static void verMural(Usuario usu, List<Posts> posts, Integer cont) {
        try {
            logger.info("------------- Tu mural ------------");
            if (usu.getSeguidos().size() == 0) {
                logger.info("No sigues a nadie, por lo que tu mural esta vacío");
            } else {
                for (Usuario u : usu.getSeguidos()) {
                    if (u.getPosts().size() == 0) {
                        logger.info("Tu amigo " + u.getPosts() + " no ha hecho ningun post");
                    } else {
                        if (posts.size() < 10) {
                            for (Posts p : u.getPosts()) {
                                logger.info(p.getRecurso().getDetails());
                            }
                        } else {
                            while (cont < 10) {
                                for (Posts p : u.getPosts()) {
                                    logger.info(p.getRecurso().getDetails());
                                    cont++;
                                }
                            }
                        }

                    }
                }

            }

        } catch (Exception e) {
            logger.error("No se ha podido mostrar el mural", e);
        }
    }

    // Funcion que muestra todos los posts del usuario a seleccionar
    protected static void verPostUsuario(List<Usuario> usuarios) {
        try {
            logger.info("Introduce el nombre del usuario para buscar sus post");
            String nombre = teclado.nextLine();
            for (Usuario u : usuarios) {
                if (u.getNombre().equals(nombre)) {
                    if (u.getPosts().size() == 0) {
                        logger.info("El usuario no tiene posts");
                    } else {
                        for (Posts p : u.posts) {
                            logger.info(p.getRecurso().getDetails());
                            for (Comentari c : p.getComentaris()) {
                                logger.info(c.verComentario());
                            }
                        }
                    }

                }
            }

        } catch (Exception e) {
            logger.error("No se ha podido mostrar los posts", e);
        }
    }

    protected static void dejarSeguirUsu(Usuario usu) {
        try {
            boolean noExiste = false;
            logger.info("Introduce el nombre del usuario que quieres dejar de seguir");
            String nomUsu = teclado.nextLine();
            if (usu.getSeguidos().size() == 0) {
                logger.error("No puedes dejar de seguir al usuario introducido porque no sigues a nadie");
            } else {
                for (Usuario us : usu.getSeguidos()) {
                    if (us.getNombre().equals(nomUsu)) {
                        usu.getSeguidos().remove(us);
                        logger.info("Has dejado de seguir a " + nomUsu);
                        break;
                    } else {
                        noExiste = true;
                    }
                }
                if (noExiste) {
                    logger.info("El usuario que has introducido no existe en tu lista de amigos");
                }
            }

        } catch (Exception e) {
            logger.error("Ha habido un error a la hora de dejar de seguir al usuario introducido", e);
        }
    }

    protected static void seguirUsuario(List<Usuario> usuarios, Usuario usu) {
        try {
            logger.info("Introduce el nombre del usuario a seguir");
            String nomUsu = teclado.nextLine();
            // Comprobar si el usuario a seguir es el mismo que el actual
            if (usuarios.size() > 1) {
                if (nomUsu.equals(usu.getNombre())) {
                    logger.error("No te puedes seguir a ti mismo");
                } else {
                    // Comprobamos que en la lista de usuarios donde se guardan todos, exista el usuario y, al comprobar su existencia,
                    // comprobamos que no se encuentra en la lista de amigos
                    for (Usuario u : usuarios) {
                        if (u.getNombre().equals(nomUsu) && !usu.getSeguidos().contains(u)) {
                            usu.getSeguidos().add(u);
                            logger.info("Ahora sigues a " + u.getNombre());
                        }
                    }
                }
            } else {
                logger.error("No hay usuarios que seguir o el usuario que has introducido no existe en la base de datos");
            }


        } catch (Exception e) {
            logger.error("Ha habido un error a la hora de seguir a un usuario", e);
        }
    }

    public String getNombre() {
        return nombre;
    }

    public List<Usuario> getSeguidos() {
        return seguidos;
    }

    public List<Posts> getPosts() {
        return posts;
    }


}
