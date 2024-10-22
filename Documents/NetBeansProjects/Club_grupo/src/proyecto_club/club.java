/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_club;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class club {
    protected List<Partner> partners; // Lista que contiene todos los socios del club
    
    // Constructor de la clase Club
    public club(){
        this.partners = new ArrayList<>(); // Inicializa la lista de socios como un ArrayList vacío
    }
    
    // Método para agregar un nuevo socio al club
    public boolean addPartner(Partner partner){
        // Verificar si el club ya tiene el máximo de socios permitido (35)
        if(partners.size() >= 35){
            JOptionPane.showMessageDialog(null, "El club ha alcanzado el máximo de 35 socios", "Error", JOptionPane.ERROR_MESSAGE);
            return false; // No se puede agregar más socios
        }
        
        // Verificar si ya existe un socio con la misma cédula
        for(Partner p: partners){
            if(p.getCedula().equals(partner.getCedula())){
                JOptionPane.showMessageDialog(null, "Ya existe un socio con ese número de cédula", "Error", JOptionPane.ERROR_MESSAGE);
                return false; // No se puede agregar el socio duplicado
            }
        }

        // Verificar si el nuevo socio es VIP y si ya hay 3 VIP en el club
        if (partner.isVIP() && countVips() >= 3){
            JOptionPane.showMessageDialog(null, "El club ha alcanzado el máximo de socios de tipo VIP", "Error", JOptionPane.ERROR_MESSAGE);  
            return false; // No se puede agregar más socios VIP
        }

        // Agregar el socio a la lista y retornar verdadero
        return partners.add(partner);
    }
    
    // Método para contar la cantidad de socios VIP en el club
    public int countVips(){
        int count = 0; // Contador de socios VIP
        
        // Iterar sobre la lista de socios para contar los VIP
        for (Partner p: partners){
            if(p.isVIP()){ // Si el socio es VIP
                count++; // Aumentar el contador
            }
        }
        return count; // Retornar el número total de socios VIP
    }
    
    // Método para buscar un socio por su cédula
    public Partner searchPartner(String cedula) {
        // Iterar sobre la lista de socios para encontrar el que coincida con la cédula
        for (Partner p : partners) {
            if (p.getCedula().equals(cedula)) {
                // Si se encuentra el socio, mostrar su información
                String message = "================Socio===================\n" +
                        "Cédula: " + p.getCedula() + "\n" +
                        "Nombre socio: " + p.getNameMember() + "\n" +
                        "Tipo de suscripción: " + p.getTypeSubscription() + "\n" +
                        "Fondos disponibles: " + p.getFundsAvailable();
                JOptionPane.showMessageDialog(null, message, "Información del Socio", JOptionPane.INFORMATION_MESSAGE);
                return p; // Retorna el socio encontrado
            }
        }
        // Si no se encontró ningún socio con la cédula proporcionada
        JOptionPane.showMessageDialog(null, "No existe el socio dentro del club", "Error", JOptionPane.ERROR_MESSAGE);
        return null; // Retorna null si no se encontró
    }
    
    // Método para eliminar un socio por su cédula
    public boolean deletePartner(String cedula) {
        Partner partner = searchPartner(cedula);
        if (partner == null) {
            JOptionPane.showMessageDialog(null, "El socio no existe dentro del club.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (partner.isVIP()) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un socio de tipo VIP.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!partner.getAffiliates().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puede eliminar un socio si tiene personas autorizadas.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!partner.getInvoices().isEmpty()) {
            JOptionPane.showMessageDialog(null, "No puedes eliminar un socio si tiene facturas pendientes de pago.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        partners.remove(partner);
        JOptionPane.showMessageDialog(null, "Se ha eliminado el socio exitosamente.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    // Método para verificar si un socio puede iniciar sesión
    public boolean login(String cedula) {
        // Iterar sobre la lista de socios para encontrar uno con la cédula dada
        for (Partner p: partners) {
            if (p.getCedula().equals(cedula)) {
                return true; // Si se encuentra, retornar verdadero
            }
        }
        return false; // Si no se encuentra, retornar falso
    }

    // Método para mostrar información de todos los socios
    public String showInfoPartners() {
        StringBuilder info = new StringBuilder();
        for (Partner p: partners) {
            info.append("================Socio===================\n")
                .append("Cédula: ").append(p.getCedula()).append("\n")
                .append("Nombre socio: ").append(p.getNameMember()).append("\n")
                .append("Tipo de suscripción: ").append(p.getTypeSubscription()).append("\n")
                .append("Fondos disponibles: ").append(p.getFundsAvailable()).append("\n\n");
        }
        // Muestra la información de los socios o un mensaje si no hay
        if (info.length() == 0) {
            JOptionPane.showMessageDialog(null, "No hay socios", "Información", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, info.toString(), "Información de Socios", JOptionPane.INFORMATION_MESSAGE);
        }
        return info.toString();
    }
}
