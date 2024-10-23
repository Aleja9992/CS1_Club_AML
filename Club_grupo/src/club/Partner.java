/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_club;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane; // Importar 


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
    protected List<Invoice> paidInvoices; 
    protected int idInvoice;
    
    // Constructor de la clase Partner
    public Partner(String cedula, String nameMember, String typeSubscription) {
        this.cedula = cedula; // Asigna la cédula proporcionada
        this.nameMember = nameMember; // Asigna el nombre proporcionado
        this.typeSubscription = typeSubscription; // Asigna el tipo de suscripción
        this.invoices = new ArrayList<>(); // Inicializa la lista de facturas como un ArrayList vacío
        this.affiliates = new ArrayList<>(); // Inicializa la lista de afiliados como un ArrayList vacío
        this.paidInvoices = new ArrayList<>(); 
        this.idInvoice  = idInvoice;
        
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
        JOptionPane.showMessageDialog(null, "Se está cambiando fundsAvailable a: " + fundsAvailable, "Cambio de Fondos", JOptionPane.INFORMATION_MESSAGE);
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
    
    
    // Método para agregar una nueva factura
    public void addInvoice(Invoice invoice) {
        invoices.add(invoice); // Agregar la factura a la lista de pendientes
    }

    // Método para obtener la lista de facturas pagadas
    public List<Invoice> getPaidInvoices() {
        return paidInvoices;
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
            JOptionPane.showMessageDialog(null, "Has alcanzado el máximo de 10 personas autorizadas o ya no tienes fondos disponibles", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            affiliates.add(affiliate); // Agrega el afiliado a la lista
            JOptionPane.showMessageDialog(null, "Se ha añadido un afiliado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE); // Mensaje de éxito
        }
    }

    // Método para aumentar los fondos disponibles del socio
    public void addFunds(double funds) {
        double maxvalue; // Variable para almacenar el valor máximo de fondos

        // Determinamos el valor máximo según el tipo de suscripción
        if (typeSubscription.equals("Regular")) {
            maxvalue = 1000000; // Límite máximo para socios Regulares
        } else {
            maxvalue = 5000000; // Límite máximo para socios VIP
        }

        // Verificamos si la suma de los fondos excede el valor máximo permitido
        if (fundsAvailable + funds > maxvalue) {
            JOptionPane.showMessageDialog(null, "No se pueden aumentar los fondos debido a que ya alcanzaste el tope máximo.", "Error", JOptionPane.ERROR_MESSAGE); // Mensaje de error
        } else {
            // Si no se excede, se agregan los fondos a los fondos disponibles
            fundsAvailable += funds; // Aumenta los fondos disponibles
            JOptionPane.showMessageDialog(null, "Se han aumentado los fondos exitosamente. Fondos actuales: " + fundsAvailable, "Éxito", JOptionPane.INFORMATION_MESSAGE); // Mensaje de éxito
        }
    }

    // Otros métodos y constructores de la clase Partner...

    public void removeAffiliate(String affiliateId) {
        Affiliate affiliateToRemove = null;
        boolean hasPendingInvoices = false;

        // Buscar el afiliado en la lista de afiliados
        for (Affiliate affiliate : affiliates) {
            if (affiliate.getCedula().equals(affiliateId)) { // Se compara la cédula del afiliado
                affiliateToRemove = affiliate;
                break;
            }
        }

        // Si no se encuentra el afiliado, mostrar mensaje de error
        if (affiliateToRemove == null) {
            JOptionPane.showMessageDialog(null, "Afiliado no encontrado.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Verificar si el afiliado tiene facturas pendientes
        for (Invoice invoice : invoices) {
            if (invoice.getNamePartner().equals(affiliateToRemove.getNameMember()) && !invoice.isPaid()) {
                hasPendingInvoices = true;
                break;
            }
        }

        // Si el afiliado tiene facturas pendientes, no se puede eliminar
        if (hasPendingInvoices) {
            JOptionPane.showMessageDialog(null, "El afiliado tiene facturas pendientes y no puede ser eliminado.", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Si no tiene facturas pendientes, eliminar el afiliado
            affiliates.remove(affiliateToRemove);
            JOptionPane.showMessageDialog(null, "Afiliado eliminado exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    // Método para registrar un consumo
    public boolean registerConsumption(Invoice invoice) {
        // Se verifica la cantidad de facturas que tiene el socio, ya que no
        // puede tener más de 20 facturas sin pagar
        if (invoices.size() >= 20) {
            JOptionPane.showMessageDialog(null, "Has alcanzado el máximo de 20 facturas sin pagar, para poder registrar un consumo, paga tus facturas pendientes.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        return invoices.add(invoice);
    }
    // Método para pagar una factura
    public boolean payInvoice(Invoice invoice) {
        if (fundsAvailable >= invoice.getValueInvoice() && !invoice.isPaid()) {
            fundsAvailable -= invoice.getValueInvoice();  // Descontar el valor de la factura
            invoice.setPaid(true);  // Marcar la factura como pagada
            paidInvoices.add(invoice);  // Agregar la factura a la lista de pagadas
            invoices.remove(invoice);  // Eliminar de las facturas pendientes
            JOptionPane.showMessageDialog(null, "Factura pagada exitosamente.\nMonto: " + invoice.getValueInvoice(), 
                                          "Pago exitoso", JOptionPane.INFORMATION_MESSAGE);
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Fondos insuficientes para pagar la factura o ya está pagada.", 
                                          "Error de pago", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Método para mostrar facturas pendientes y pagadas
    public void showAllInvoices() {
        StringBuilder message = new StringBuilder();

        // Mostrar facturas pendientes
        message.append("================ Facturas Pendientes ================\n");
        if (invoices.isEmpty()) {
            message.append("No hay facturas pendientes.\n\n");
        } else {
            for (Invoice invoice : invoices) {
            message.append("ID de Factura: ").append(invoice.getIdInvoice()) // Mostrar el ID de la factura
                   .append("\nConcepto: ").append(invoice.getItemInvoice())
                   .append("\nID Generador: ").append(invoice.getNamePartner())
                   .append("\nValor: ").append(invoice.getValueInvoice()).append("\n\n");
        }
        }

        // Mostrar facturas pagadas
        message.append("================ Facturas Pagadas ================\n");
        if (paidInvoices.isEmpty()) {
            message.append("No hay facturas pagadas.\n\n");
        } else {
            for (Invoice invoice : paidInvoices) {
            message.append("ID de Factura: ").append(invoice.getIdInvoice()) // Mostrar el ID de la factura
                   .append("\nConcepto: ").append(invoice.getItemInvoice())
                   .append("\nID Generador: ").append(invoice.getNamePartner())
                   .append("\nValor: ").append(invoice.getValueInvoice()).append("\n\n");
        }
        }

        // Mostrar el resumen en una ventana de diálogo
        JOptionPane.showMessageDialog(null, message.toString(), 
                                      "Facturas Pendientes y Pagadas", JOptionPane.INFORMATION_MESSAGE);
    }

  public Invoice findInvoiceById(int invoiceId) {
    for (Invoice invoice : invoices) {  // Recorre la lista de facturas pendientes
        if (invoice.getIdInvoice() == invoiceId) {  // Si encuentra el ID coincidente
            return invoice;  // Retorna la factura
        }
    }
    return null;  // Si no encuentra ninguna factura con el ID, retorna null
}
    
}
