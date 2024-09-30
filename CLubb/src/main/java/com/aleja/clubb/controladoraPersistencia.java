
package com.aleja.clubb;

import java.util.List;


public class controladoraPersistencia extends LoginJpaController1 {
    LoginJpaController1 useJpa = new LoginJpaController1();

    List<Login> ShowLogin() {
       return useJpa.findLoginEntities();
       //SELECT * from usuarios
    }
    
}
