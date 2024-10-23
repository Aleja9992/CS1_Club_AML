package club;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Usuario
 */
public class Affiliate extends Partner {
    protected Partner partner; // Referencia al socio (Partner) al que pertenece este afiliado

    // Constructor de la clase Affiliate
    public Affiliate(String cedula, String nameMember, String typeSubscription, Partner partner) {
        super(cedula, nameMember, typeSubscription); // Llama al constructor de la clase base Partner para inicializar cédula, nombre y tipo de suscripción
        this.partner = partner; // Asigna el socio correspondiente a este afiliado
    }
}

