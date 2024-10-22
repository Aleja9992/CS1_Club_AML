/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_club;

/**
 *
 * @author Usuario
 */
public class ViPPartner extends Partner {
    public ViPPartner(String cedula, String nameMember) {
        super(cedula, nameMember, "VIP");
        this.fundsAvailable = 100000; // Fondos iniciales para VIP
    }
}
