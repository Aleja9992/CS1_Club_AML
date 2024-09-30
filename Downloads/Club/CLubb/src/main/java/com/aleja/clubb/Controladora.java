package com.aleja.clubb;

import java.util.List;

public class Controladora {

    controladoraPersistencia controlPersis = new controladoraPersistencia();

    // Método para validar los miembros (usuarios)
    String valueLogin(String user, String password) {
        List<Login> listMembers = controlPersis.ShowLogin(); // Método para obtener la lista de miembros (Login)

        String message = "";
        for (Login login : listMembers) {
            if (login.getUser().equals(user)) {
                if (login.getPassaword().equals(password)) { // Corregí "passaword" a "password"
                    message = "Usuario y contraseña correctos. ¡Bienvenido/a!";
                    break; // Si encontramos un match, salimos del bucle
                } else {
                    message = "Contraseña incorrecta";
                    break; // Si el usuario es correcto pero la contraseña no, también salimos
                }
            } else {
                message = "Usuario no encontrado";
            }
        }
        return message;
    }
        
}
    

