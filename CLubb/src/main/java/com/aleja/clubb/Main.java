/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.aleja.clubb;

import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {

    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Partner partner = new Partner();
        CLubb c = new CLubb();
        Club_interfaz start = new Club_interfaz ();
        start.setVisible(true);
        start.setLocationRelativeto(null);
        
        
        
        
        Scanner sc = new Scanner(System.in);
        
        String option;
        String cedula;
        String nameMember;
        String typeSubscription;
        double fundsAvailable;
        boolean switche = true;
        
        
        do{
            
            principalMenu();
            option = sc.nextLine();
            switch (option){
               case "a":
                    System.out.println("Ingresa tu cedula: ");
                    cedula = sc.nextLine();
                    c.searchPartner(cedula);
                    
                    if(c.login(cedula)==true){
                        partnerMenu();
                        option = sc.nextLine();
                        switch (option){
                            case "a":
                                System.out.println("Ingresa tu cedula: ");
                                cedula = sc.nextLine();
                                System.out.println("Ingresa tu nombre: ");
                                nameMember = sc.nextLine();
                                
                               
                                Affiliate affiliate = new Affiliate(cedula, nameMember, partner.getTypeSubscription(), partner);
                                
                                partner.addAffiliate(affiliate);
                                System.out.println(partner.getAffiliates());
                                break;
                                
                            case "b":
                                System.out.println("Ingresa los fondos a aumentar: ");
                                fundsAvailable = sc.nextDouble();
                                partner.addFuns(fundsAvailable); 
                                break;
                            case "d":
                                break;     
                        }   
                    }else{
                        System.out.println("La cedula ingresada no existe dentro del club");
                        break;
                    }   
               case "b":
                    System.out.println("Ingresa tu cedula: ");
                    cedula = sc.nextLine();
                    System.out.println("Ingresa tu nombre: ");
                    nameMember = sc.nextLine();
                    System.out.println("Ingresa el tipo de suscripción (Regular/ VIP): ");
                    typeSubscription = sc.nextLine();
                    
                    
                    partner = new Partner(cedula, nameMember, typeSubscription);
                    
                    c.addPartner(partner);
                    
                    
                    
                    
                    
                         
            }
            
            
        }while(switche);
    }
    public static void principalMenu(){
        System.out.println("=============Bienvenido al club===============");
        System.out.println("a. Ingresar");
        System.out.println("b. Inscribir socio");
        System.out.println("c. Salir");
        System.out.println("Elige una opción: ");
        
        
    }
    
    public static void partnerMenu(){
        System.out.println("=============Bienvenido================");
        System.out.println("a. Afiliar socio");
        System.out.println("b. Aumentar fondos");
        System.out.println("c. Pagar factura");
        System.out.println("d. Regresar");
        System.out.println("Elige una opción: ");
        
    }
    
    
}
    

