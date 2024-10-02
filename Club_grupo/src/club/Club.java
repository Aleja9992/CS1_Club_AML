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
public class Club {
    protected List<Partner> partners; // Lista que contiene todos los socios del club
    
    // Constructor de la clase Club
    public Club(){
        this.partners = new ArrayList<>(); // Inicializa la lista de socios como un ArrayList vacío
    }
    
    // Método para agregar un nuevo socio al club
    public boolean addPartner(Partner partner){
        // Verificar si el club ya tiene el máximo de socios permitido (35)
        if(partners.size() >= 35){
            System.out.println("El club ha alcanzado el máximo de 35 socios");
            return false; // No se puede agregar más socios
        }
        
        // Verificar si ya existe un socio con la misma cédula
        for(Partner p: partners){
            if(p.getCedula().equals(partner.getCedula())){
                System.out.println("Ya existe un socio con ese número de cédula");
                return false; // No se puede agregar el socio duplicado
            }
        }

        // Verificar si el nuevo socio es VIP y si ya hay 3 VIP en el club
        if (partner.isVIP() && countVips() >= 3){
            System.out.println("El club ha alcanzado el máximo de socios de tipo VIP");  
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
                count = count + 1; // Aumentar el contador
            }
        }
        return count; // Retornar el número total de socios VIP
    }
    
    // Método para buscar un socio por su cédula
    public String searchPartner(String cedula) {
        // Iterar sobre la lista de socios para encontrar el que coincida con la cédula
        for (Partner p : partners) {
            if (p.getCedula().equals(cedula)) {
                // Si se encuentra el socio, mostrar su información
                System.out.println("================Socio===================");
                System.out.println("Cedula: " + p.getCedula());
                System.out.println("Nombre socio: " + p.getNameMember());
                System.out.println("Tipo de suscripcion: " + p.getTypeSubscription());
                System.out.println("Fondos disponibles: " + p.getFundsAvailable());
                return "Socio encontrado"; // Retornar un mensaje de éxito
            }
        }
        // Si no se encontró ningún socio con la cédula proporcionada
        System.out.println("No existe el socio dentro del club");
        return "Socio no encontrado"; // Retornar un mensaje de error
    }
  
    // Método para verificar si un socio puede iniciar sesión
    public boolean login(String cedula){
        // Iterar sobre la lista de socios para encontrar uno con la cédula dada
        for (Partner p: partners){
            if(p.getCedula().equals(cedula)){
                return true; // Si se encuentra, retornar verdadero
            }
        }
        return false; // Si no se encuentra, retornar falso
    }
}