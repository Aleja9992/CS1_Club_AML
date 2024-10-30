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
public class Affiliate {
    protected String cedula;  // Cédula del afiliado
    protected String nameMember;  // Nombre del afiliado
    protected String typeSubscription;  // Tipo de suscripción
    protected Partner partner;  // Socio asociado

    // Constructor
    public Affiliate(String cedula, String nameMember, String typeSubscription, Partner partner) {
        this.cedula = cedula;
        this.nameMember = nameMember;
        this.typeSubscription = typeSubscription;
        this.partner = partner;
    }

    // Método para obtener la cédula del afiliado
    public String getCedula() {
        return cedula;
    }

    // Método para obtener el nombre del afiliado
    public String getNameMember() {
        return nameMember;
    }

   
    Object getcedula() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
