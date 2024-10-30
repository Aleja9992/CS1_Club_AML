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
public class RegularPartner extends Partner {
    public RegularPartner(String cedula, String nameMember) {
        super(cedula, nameMember, "Regular");
    }

    @Override
    protected void initializeFunds() {
        this.fundsAvailable = 50000; // Fondos iniciales específicos para Regular
    }
    
}