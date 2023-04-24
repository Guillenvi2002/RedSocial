package org.example.redsocial;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Scanner;

public class Menu {
    private static final Scanner teclado = new Scanner(System.in);

    private static final Logger logger = LoggerFactory.getLogger(Comentari.class);

    protected static void eliminar() {
        try {
            int eleccion;
            do {
                logger.info("-------------------------------------------");
                logger.info("Que quiere eliminar?:");
                logger.info("1-Usuario");
                logger.info("2-Post");
                logger.info("3-Comentario");
                logger.info("4-Cancelar");
                logger.info("-------------------------------------------");
                eleccion = teclado.nextInt();
                teclado.nextLine();
                switch (eleccion) {
                    case 1:
                        Main.eliminarUsuario();
                        break;
                    case 2:
                        Main.eliminarPost();
                        break;
                    case 3:
                        Main.eliminarComentario();
                        break;
                    default:
                        break;
                }
            } while (eleccion != 4);
        } catch (Exception e) {
            logger.error("Seleccione una opcion de las disponibles");
        }
    }

    protected static void crearPost() {
        try {
            int eleccion;

            do {
                logger.info("-------------------------------------------");
                logger.info("Escoja un tipo de Post:");
                logger.info("1-Texto");
                logger.info("2-Video");
                logger.info("3-Imagen");
                logger.info("4-Cancelar");
                logger.info("-------------------------------------------");
                eleccion = teclado.nextInt();
                teclado.nextLine();
                switch (eleccion) {
                    case 1:
                        Main.crearPost("Texto");
                        break;
                    case 2:
                        Main.crearPost("Video");
                        break;
                    case 3:
                        Main.crearPost("Imagen");
                        break;
                    default:
                        break;
                }
            } while (eleccion != 4);

        } catch (Exception e) {
            logger.error("Seleccione una opcion de las disponibles");
        }
    }

    protected static void menuCrear() {
        try {
            int eleccion;

            do {
                logger.info("-------------------------------------------");
                logger.info("Que quieres crear?:");
                logger.info("1-Usuario");
                logger.info("2-Post");
                logger.info("3-Comentario");
                logger.info("4-Cancelar");
                logger.info("-------------------------------------------");
                eleccion = teclado.nextInt();
                teclado.nextLine();
                switch (eleccion) {
                    case 1:
                        Main.crearUsuario();
                        break;
                    case 2:
                        Menu.crearPost();
                        break;
                    case 3:
                        Main.crearComentario();
                        break;
                    default:
                        break;
                }
            } while (eleccion != 4);

        } catch (Exception e) {
            logger.error("Seleccione una opcion de las disponibles");
        }
    }

    protected static void menuOpcionesUsu() {
        try {


            int eleccion;

            do {
                logger.info("-------------------------------------------");
                logger.info("Selecciona una de las siguientes opciones:");
                logger.info("1-Seguir a un usuario");
                logger.info("2-Dejar de seguir a un usuario");
                logger.info("3-Iniciar sesion como otro usuario");
                logger.info("4-Sugerir posibles amigos");
                logger.info("5-Ver mi lista de amigos");
                logger.info("6-Ver el mural");
                logger.info("7-Cancelar");
                logger.info("-------------------------------------------");
                eleccion = teclado.nextInt();
                teclado.nextLine();
                switch (eleccion) {
                    case 1:
                        Usuario.seguirUsuario(Main.usuarios, Main.usu);
                        break;
                    case 2:
                        Usuario.dejarSeguirUsu(Main.usu);
                        break;
                    case 3:
                        Main.cambioSesion();
                        break;
                    case 4:
                        Usuario.sugerirAmigos(Main.usu, Main.usuarios);
                        break;
                    case 5:
                        Usuario.verAmigos(Main.usu);
                        break;
                    case 6:
                        Usuario.verMural(Main.usu, Main.posts, Main.cont);
                    default:
                        break;
                }
            } while (eleccion != 7);
        } catch (Exception e) {
            logger.error("Seleccione una opcion de las disponibles");
        }

    }
}
