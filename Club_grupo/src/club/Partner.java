/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club;

import java.util.ArrayList;
import java.util.List;



/**
 *
 * @author Usuario
 */
public class Partner {
    protected String cedula; // Cédula del socio
    protected String nameMember; // Nombre del socio
    protected double fundsAvailable; // Fondos disponibles del socio
    protected String typeSubscription; // Tipo de suscripción (Regular o VIP)
    protected List<Invoice> invoices; // Lista de facturas del socio
    protected List<Affiliate> affiliates; // Lista de afiliados del socio

    // Constructor de la clase Partner
    public Partner(String cedula, String nameMember, String typeSubscription) {
        this.cedula = cedula; // Asigna la cédula proporcionada
        this.nameMember = nameMember; // Asigna el nombre proporcionado
        this.typeSubscription = typeSubscription; // Asigna el tipo de suscripción
        this.invoices = new ArrayList<>(); // Inicializa la lista de facturas como un ArrayList vacío
        this.affiliates = new ArrayList<>(); // Inicializa la lista de afiliados como un ArrayList vacío

        // Asigna los fondos iniciales según el tipo de suscripción
        if (typeSubscription.equals("Regular")) {
            this.fundsAvailable = 50000; // Fondos iniciales para socios Regulares
        } else if (typeSubscription.equals("VIP")) {
            this.fundsAvailable = 100000; // Fondos iniciales para socios VIP
        }
    }

    // Método para obtener la cédula del socio
    public String getCedula() {
        return cedula;
    }

    // Método para establecer la cédula del socio
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    // Método para obtener el nombre del socio
    public String getNameMember() {
        return nameMember;
    }

    // Método para establecer el nombre del socio
    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    // Método para obtener los fondos disponibles del socio
    public double getFundsAvailable() {
        return fundsAvailable;
    }

    // Método para establecer los fondos disponibles del socio
    public void setFundsAvailable(double fundsAvailable) {
        System.out.println("Se está cambiando fundsAvailable a: " + fundsAvailable); // Mensaje de cambio
        this.fundsAvailable = fundsAvailable; // Actualiza los fondos disponibles
    }

    // Método para obtener el tipo de suscripción del socio
    public String getTypeSubscription() {
        return typeSubscription;
    }

    // Método para establecer el tipo de suscripción del socio
    public void setTypeSubscription(String typeSubscription) {
        this.typeSubscription = typeSubscription;
    }

    // Método para obtener la lista de facturas del socio
    public List<Invoice> getInvoices() {
        return invoices;
    }

    // Método para establecer la lista de facturas del socio
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices; // Actualiza la lista de facturas
    }

    // Método para obtener la lista de afiliados del socio
    public List<Affiliate> getAffiliates() {
        return affiliates;
    }

    // Método para establecer la lista de afiliados del socio
    public void setAffiliates(List<Affiliate> affiliates) {
        this.affiliates = affiliates; // Actualiza la lista de afiliados
    }

    // Método para verificar si el socio tiene tipo de suscripción VIP
    public boolean isVIP() {
        return typeSubscription.equals("VIP"); // Retorna verdadero si es VIP, falso en caso contrario
    }
    
    // Método para agregar un afiliado a la lista de afiliados del socio
    public void addAffiliate(Affiliate affiliate) {
        // Verifica si el socio ha alcanzado el máximo de 10 afiliados o si no tiene fondos disponibles
        if (affiliates.size() >= 10 || fundsAvailable <= 0) {
            System.out.println("Has alcanzado el máximo de 10 personas autorizadas o ya no tienes fondos disponibles");
        } else {
            affiliates.add(affiliate); // Agrega el afiliado a la lista
            System.out.println("Se ha añadido un afiliado exitosamente."); // Mensaje de éxito
        }
    }

    // Método para aumentar los fondos disponibles del socio
    public void addFuns(double funds) {
        double maxvalue; // Variable para almacenar el valor máximo de fondos

        // Determinamos el valor máximo según el tipo de suscripción
        if (typeSubscription.equals("Regular")) {
            maxvalue = 1000000; // Límite máximo para socios Regulares
        } else {
            maxvalue = 5000000; // Límite máximo para socios VIP
        }

        // Verificamos si la suma de los fondos excede el valor máximo permitido
        if (fundsAvailable + funds > maxvalue) {
            System.out.println("No se pueden aumentar los fondos debido a que ya alcanzaste el tope máximo."); // Mensaje de error
        } else {
            // Si no se excede, se agregan los fondos a los fondos disponibles
            fundsAvailable += funds; // Aumenta los fondos disponibles
            System.out.println("Se han aumentado los fondos exitosamente. Fondos actuales: " + fundsAvailable); // Mensaje de éxito
        }
    }
}