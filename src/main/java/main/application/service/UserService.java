package main.application.service;

import main.domain.resource.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.List;

public interface UserService  {

    // Devuelve la info del usuario, o null si el usuario no existe.
    UsuarioResource getUserByName(String user);

    // Devuelve una lista con todas las publicaciones (id e imagen) del usuario, o null si el usuario no existe.
    List<PreviewPublicacion> getPosts(String user);



    // Devuelve todoas las valoraciones que ha hecho un usuario (En todas las publicaciones), o null si el usuario no existe.
    List<ValoracionResource> getRatings(String user);

    // Da de alta un usuario en la BD y le envia un mail de confirmacion.
    // Lanza una excepcion si se le introduce un usuario, contraseña o email no validos.
    // Si se ha podido registrar al usuario, lo devuelve.
    UsuarioResource register(String user, String passwd, String email) throws IllegalArgumentException;

    // Sudad de este.
    UsuarioResource verify(Integer token);



}
