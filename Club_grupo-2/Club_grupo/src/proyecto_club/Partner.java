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
public abstract class Partner implements MemberClub {
    // Atributos de la clase Partner
    protected String cedula; // Cédula del socio
    protected String nameMember; // Nombre del socio
    protected double fundsAvailable; // Fondos disponibles del socio
    protected String typeSubscription; // Tipo de suscripción (Regular o VIP)
    protected List<Invoice> invoices; // Lista de facturas pendientes del socio
    protected List<Affiliate> affiliates; // Lista de afiliados del socio
    protected List<Invoice> paidInvoices; // Lista de facturas pagadas
    protected int idInvoice; // Identificador de factura

    // Constructor de la clase Partner
    public Partner(String cedula, String nameMember, String typeSubscription) {
        // Validaciones para asegurarse de que los valores no sean nulos o vacíos
        if (cedula == null || cedula.isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía.");
        }
        if (nameMember == null || nameMember.isEmpty()) {
            throw new IllegalArgumentException("El nombre del socio no puede estar vacío.");
        }
        if (!typeSubscription.equals("Regular") && !typeSubscription.equals("VIP")) {
            throw new IllegalArgumentException("Tipo de suscripción inválido. " +
                 "Debe ser 'Regular' o 'VIP'.");
        }

        // Asignación de valores a los atributos
        this.cedula = cedula;
        this.nameMember = nameMember;
        this.typeSubscription = typeSubscription;
        this.invoices = new ArrayList<>(); // Inicializa la lista de facturas
        this.affiliates = new ArrayList<>(); // Inicializa la lista de afiliados
        this.paidInvoices = new ArrayList<>(); // Inicializa la lista de facturas pagadas
        this.idInvoice = idInvoice; // Inicializa el ID de factura

        // Asigna los fondos iniciales según el tipo de suscripción
        if (typeSubscription.equals("Regular")) {
            this.fundsAvailable = 50000; // Fondos iniciales para suscriptores regulares
        } else {
            this.fundsAvailable = 100000; // Fondos iniciales para suscriptores VIP
        }
    }

    // Método abstracto para inicializar los fondos, a implementar en subclases
    protected abstract void initializeFunds();

    // Métodos de acceso y modificación (getters y setters) para los atributos

    @Override
    public String getCedula() {
        return cedula; // Devuelve la cédula del socio
    }

    public void setCedula(String cedula) {
        // Validación para asegurarse de que la cédula no sea nula o vacía
        if (cedula == null || cedula.isEmpty()) {
            throw new IllegalArgumentException("La cédula no puede estar vacía.");
        }
        this.cedula = cedula; // Asigna la nueva cédula
    }

    @Override
    public String getNameMember() {
        return nameMember; // Devuelve el nombre del socio
    }

    public void setNameMember(String nameMember) {
        // Validación para asegurarse de que el nombre del socio no sea nulo o vacío
        if (nameMember == null || nameMember.isEmpty()) {
            throw new IllegalArgumentException("El nombre del socio no puede estar vacío.");
        }
        this.nameMember = nameMember; // Asigna el nuevo nombre
    }

    @Override
    public double getFundsAvailable() {
        return fundsAvailable; // Devuelve los fondos disponibles del socio
    }

    public void setFundsAvailable(double fundsAvailable) {
        // Validación para asegurarse de que los fondos no sean negativos
        if (fundsAvailable < 0) {
            throw new IllegalArgumentException("Los fondos disponibles no pueden ser negativos.");
        }
        // Muestra un mensaje de cambio de fondos
        JOptionPane.showMessageDialog(null, "Se está cambiando fundsAvailable a: " + 
            fundsAvailable, "Cambio de Fondos", 
        JOptionPane.INFORMATION_MESSAGE);
        this.fundsAvailable = fundsAvailable; // Asigna los nuevos fondos disponibles
    }

    @Override
    public String getTypeSubscription() {
        return typeSubscription; // Devuelve el tipo de suscripción del socio
    }

    public void setTypeSubscription(String typeSubscription) {
        // Validación para asegurarse de que el tipo de suscripción sea válido
        if (!typeSubscription.equals("Regular") && !typeSubscription.equals("VIP")) {
            throw new IllegalArgumentException("Tipo de suscripción inválido. " +
                "Debe ser 'Regular' o 'VIP'.");
        }
        this.typeSubscription = typeSubscription; // Asigna el nuevo tipo de suscripción
    }

    public List<Invoice> getInvoices() {
        return invoices; // Devuelve la lista de facturas pendientes
    }

    public void addInvoice(Invoice invoice) {
        // Validación para asegurarse de que la factura no sea nula
        if (invoice == null) {
            throw new IllegalArgumentException("La factura no puede ser nula.");
        }
        invoices.add(invoice); // Añade la nueva factura a la lista
    }

    public List<Invoice> getPaidInvoices() {
        return paidInvoices; // Devuelve la lista de facturas pagadas
    }

    public void setInvoices(List<Invoice> invoices) {
        // Validación para asegurarse de que la lista de facturas no sea nula
        if (invoices == null) {
            throw new IllegalArgumentException("La lista de facturas no puede ser nula.");
        }
        this.invoices = invoices; // Asigna la nueva lista de facturas
    }

    public List<Affiliate> getAffiliates() {
        return affiliates; // Devuelve la lista de afiliados del socio
    }

    public void setAffiliates(List<Affiliate> affiliates) {
        // Validación para asegurarse de que la lista de afiliados no sea nula
        if (affiliates == null) {
            throw new IllegalArgumentException("La lista de afiliados no puede ser nula.");
        }
        this.affiliates = affiliates; // Asigna la nueva lista de afiliados
    }

    // Método que verifica si el socio es VIP
    public boolean isVIP() {
        return typeSubscription.equals("VIP"); // Devuelve verdadero si el tipo de suscripción es VIP
    }

    // Método para añadir un afiliado a la lista
    public void addAffiliate(Affiliate affiliate) {
        // Validación para asegurarse de que el afiliado no sea nulo
        if (affiliate == null) {
            throw new IllegalArgumentException("El afiliado no puede ser nulo.");
        }
        // Verifica que no se haya alcanzado el límite de afiliados o que haya fondos disponibles
        if (affiliates.size() >= 10 || fundsAvailable <= 0) {
            JOptionPane.showMessageDialog(null, 
                "Has alcanzado el máximo de 10 " +
                "personas autorizadas o ya no tienes " +
                "fondos disponibles", "Error", 
            JOptionPane.ERROR_MESSAGE);
        } else {
            affiliates.add(affiliate); // Añade el afiliado a la lista
            JOptionPane.showMessageDialog(null, 
                "Se ha añadido un afiliado exitosamente.", 
                "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        }
    }

    @Override
    public void addFunds(double funds) {
        // Validación para asegurarse de que la cantidad de fondos a agregar sea positiva
        if (funds <= 0) {
            throw new IllegalArgumentException("La cantidad de fondos a agregar " +
             "debe ser mayor que cero.");
        }

        // Define el valor máximo que se puede tener según el tipo de suscripción
        double maxvalue = typeSubscription.equals("Regular") ? 1000000 : 5000000;

        // Verifica si los fondos disponibles más los nuevos fondos exceden el límite
        if (fundsAvailable + funds > maxvalue) {
            JOptionPane.showMessageDialog(null, 
              "No se pueden aumentar los fondos " +
              "debido a que ya alcanzaste el tope " +
              "máximo.", "Error", 
            JOptionPane.ERROR_MESSAGE);
        } else {
            fundsAvailable += funds; // Aumenta los fondos disponibles
            JOptionPane.showMessageDialog(null, 
                "Se han aumentado los fondos " +
                "exitosamente. Fondos actuales: " + 
                fundsAvailable, "Éxito", 
            JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para eliminar un afiliado
    public void removeAffiliate(String affiliateId) {
        Affiliate affiliateToRemove = null; // Inicializa el afiliado a eliminar
        boolean hasPendingInvoices = false; // Indica si el afiliado tiene facturas pendientes

        // Busca el afiliado por su cédula
        for (Affiliate affiliate : affiliates) {
            if (affiliate.getCedula().equals(affiliateId)) {
                affiliateToRemove = affiliate; // Asigna el afiliado a eliminar
                break; // Sale del bucle si lo encuentra
            }
        }

        // Verifica si el afiliado fue encontrado
        if (affiliateToRemove == null) {
            JOptionPane.showMessageDialog(null, "Afiliado no encontrado.", 
            "Error", JOptionPane.ERROR_MESSAGE);
            return; // Sale del método si no se encuentra
        }

        // Verifica si el afiliado tiene facturas pendientes
        for (Invoice invoice : invoices) {
            if (invoice.getNamePartner().equals(affiliateToRemove.getNameMember()) && 
                !invoice.isPaid()) {
                hasPendingInvoices = true; // Indica que hay facturas pendientes
                break; // Sale del bucle si encuentra alguna
            }
        }

        // Verifica si el afiliado tiene facturas pendientes antes de eliminarlo
        if (hasPendingInvoices) {
            JOptionPane.showMessageDialog(null, 
                "El afiliado tiene facturas pendientes " +
                "y no puede ser eliminado.", "Error", 
                JOptionPane.ERROR_MESSAGE);
        } else {
            affiliates.remove(affiliateToRemove); // Elimina el afiliado de la lista
            JOptionPane.showMessageDialog(null, 
            "Afiliado eliminado exitosamente.", 
            "Éxito", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    // Método para registrar un consumo (factura)
    public boolean registerConsumption(Invoice invoice) {
        // Verifica si se ha alcanzado el límite de facturas pendientes
        if (invoices.size() >= 20) {
            JOptionPane.showMessageDialog(null, 
               "Has alcanzado el máximo de 20 " +
               "facturas sin pagar, para poder " +
               "registrar un consumo, paga tus " +
               "facturas pendientes.", "Error", 
               JOptionPane.ERROR_MESSAGE);
            return false; // Retorna falso si no se puede registrar
        }
        // Validación para asegurarse de que la factura no sea nula
        if (invoice == null) {
            throw new IllegalArgumentException("La factura no puede ser nula.");
        }
        invoices.add(invoice); // Añade la nueva factura a la lista
        return true; // Retorna verdadero si se registra exitosamente
    }

    // Método para pagar una factura
    public boolean payInvoice(Invoice invoice) {
        // Validación para asegurarse de que la factura no sea nula
        if (invoice == null) {
            throw new IllegalArgumentException("La factura no puede ser nula.");
        }
        // Verifica si hay suficientes fondos y si la factura no ha sido pagada
        if (fundsAvailable >= invoice.getValueInvoice() && !invoice.isPaid()) {
            fundsAvailable -= invoice.getValueInvoice(); // Descuenta el monto de la factura de los fondos disponibles
            invoice.setPaid(true); // Marca la factura como pagada
            paidInvoices.add(invoice); // Añade la factura a la lista de facturas pagadas
            invoices.remove(invoice); // Elimina la factura de la lista de facturas pendientes
            JOptionPane.showMessageDialog(null, 
                "Factura pagada exitosamente.\nMonto: " + 
                invoice.getValueInvoice(), 
                "Pago exitoso", 
                JOptionPane.INFORMATION_MESSAGE);
            return true; // Retorna verdadero si el pago fue exitoso
        } else {
            // Mensaje de error si no hay suficientes fondos o la factura ya está pagada
            JOptionPane.showMessageDialog(null, 
                "Fondos insuficientes para pagar la " +
                "factura o ya está pagada.", 
                "Error de pago", 
                JOptionPane.ERROR_MESSAGE);
            return false; // Retorna falso si el pago falla
        }
    }

    // Método para mostrar todas las facturas (pendientes y pagadas)
    public void showAllInvoices() {
        StringBuilder message = new StringBuilder(); // Inicializa un StringBuilder para construir el mensaje

        message.append("================ Facturas Pendientes ================\n");
        // Verifica si hay facturas pendientes
        if (invoices.isEmpty()) {
            message.append("No hay facturas pendientes.\n\n");
        } else {
            // Recorre y muestra las facturas pendientes
            for (Invoice invoice : invoices) {
                message.append("ID de Factura: ").append(invoice.getIdInvoice())
                       .append("\nConcepto: ").append(invoice.getItemInvoice())
                       .append("\nID Generador: ").append(invoice.getNamePartner())
                       .append("\nValor: ").append(invoice.getValueInvoice()).append("\n\n");
            }
        }

        message.append("================ Facturas Pagadas ================\n");
        // Verifica si hay facturas pagadas
        if (paidInvoices.isEmpty()) {
            message.append("No hay facturas pagadas.\n\n");
        } else {
            // Recorre y muestra las facturas pagadas
            for (Invoice invoice : paidInvoices) {
                message.append("ID de Factura: ").append(invoice.getIdInvoice())
                       .append("\nConcepto: ").append(invoice.getItemInvoice())
                       .append("\nID Generador: ").append(invoice.getNamePartner())
                       .append("\nValor: ").append(invoice.getValueInvoice()).append("\n\n");
            }
        }

        // Muestra un cuadro de diálogo con todas las facturas
        JOptionPane.showMessageDialog(null, message.toString(), 
            "Facturas", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    // Método para buscar una factura por su ID
    public Invoice findInvoiceById(int invoiceId) {
        for (Invoice invoice : invoices) {
            if (invoice.getIdInvoice() == invoiceId) {
                return invoice; // Retorna la factura si se encuentra
            }
        }
        return null; // Retorna nulo si no se encuentra la factura
    }
    
    // Método para mostrar los afiliados
    public void showAffiliates(){
        StringBuilder message = new StringBuilder();

        try {
            // Verifica si hay afiliados en la lista
            if (affiliates.isEmpty()) {
                throw new Exception("No hay afiliados registrados para este socio.");
            }

            // Agrega un encabezado al mensaje
            message.append("========== Lista de Afiliados ==========\n");
            for (Affiliate affiliate : affiliates) {
                message.append("Cédula: ").append(affiliate.getCedula())
                       .append("\nNombre: ").append(affiliate.getNameMember())
                       .append("\n-----------------------------\n");
            }

            // Muestra el mensaje con la lista de afiliados en un cuadro de diálogo
            JOptionPane.showMessageDialog(null, message.toString(), 
                "Afiliados del Socio", JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            // Muestra un mensaje de error si no hay afiliados registrados
            JOptionPane.showMessageDialog(null, e.getMessage(), 
                "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
}
