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
public class Affiliate extends Partner{
    
    protected Partner partner;
    
    public Affiliate(String cedula, String nameMember, String typeSubscription, Partner partner) {
        super(cedula, nameMember, typeSubscription);
        this.partner = partner;
    }
    
}
