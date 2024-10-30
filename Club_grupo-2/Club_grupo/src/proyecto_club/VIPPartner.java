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
public class VIPPartner extends Partner {
    public VIPPartner(String cedula, String nameMember) {
        super(cedula, nameMember, "VIP");
    }

    @Override
    protected void initializeFunds() {
        this.fundsAvailable = 100000; // Fondos iniciales específicos para VIP
    }
}
