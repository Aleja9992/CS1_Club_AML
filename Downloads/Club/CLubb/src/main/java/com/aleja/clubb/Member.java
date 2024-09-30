/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aleja.clubb;


/**
 *
 * @author Usuario
 */

public class Member {
    
    private int id;
    private String suscriptionType;
    private double fondosDisponibles;
    private String facturasPendientes;
    private String personasAutorizadas;
    
    public Member(){
        
    }

    public Member(int id, String suscriptionType, double fondosDisponibles, String facturasPendientes, String personasAutorizadas) {
        this.id = id;
        this.suscriptionType = suscriptionType;
        this.fondosDisponibles = fondosDisponibles;
        this.facturasPendientes = facturasPendientes;
        this.personasAutorizadas = personasAutorizadas;
    }   

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * @return the suscriptionType
     */
    public String getSuscriptionType() {
        return suscriptionType;
    }

    /**
     * @param suscriptionType the suscriptionType to set
     */
    public void setSuscriptionType(String suscriptionType) {
        this.suscriptionType = suscriptionType;
    }

    /**
     * @return the fondosDisponibles
     */
    public double getFondosDisponibles() {
        return fondosDisponibles;
    }

    /**
     * @param fondosDisponibles the fondosDisponibles to set
     */
    public void setFondosDisponibles(double fondosDisponibles) {
        this.fondosDisponibles = fondosDisponibles;
    }

    /**
     * @return the facturasPendientes
     */
    public String getFacturasPendientes() {
        return facturasPendientes;
    }

    /**
     * @param facturasPendientes the facturasPendientes to set
     */
    public void setFacturasPendientes(String facturasPendientes) {
        this.facturasPendientes = facturasPendientes;
    }

    /**
     * @return the personasAutorizadas
     */
    public String getPersonasAutorizadas() {
        return personasAutorizadas;
    }

    /**
     * @param personasAutorizadas the personasAutorizadas to set
     */
    public void setPersonasAutorizadas(String personasAutorizadas) {
        this.personasAutorizadas = personasAutorizadas;
    }

 public void registrarPersonaAutorizada(String nombre) {
        this.personasAutorizadas = nombre;  // Solo se maneja una persona autorizada
        System.out.println("Persona autorizada añadida: " + nombre);
    }

    public void pagarFactura() {
        if (!facturasPendientes.equals("0")) {
            facturasPendientes = "0";
            System.out.println("Factura pagada.");
        } else {
            System.out.println("No hay facturas pendientes.");
        }
    }

    public void registrarConsumo(double monto) {
        if (fondosDisponibles >= monto) {
            fondosDisponibles -= monto;
            System.out.println("Consumo registrado: " + monto);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    public void aumentarFondos(double monto) {
        fondosDisponibles += monto;
        System.out.println("Fondos aumentados en: " + monto);
    }

    public boolean eliminarSocio() {
        // Validación: El socio es VIP
        if (suscriptionType.equals("VIP")) {
            System.out.println("No se puede eliminar a un socio VIP.");
            return false;
        }
        // Validación: El socio tiene facturas pendientes
        if (!facturasPendientes.equals("0")) {
            System.out.println("No se puede eliminar a un socio con facturas pendientes.");
            return false;
        }
        // Validación: Tiene más de un autorizado (solo una persona autorizada es permitida)
        if (!personasAutorizadas.isEmpty()) {
            System.out.println("No se puede eliminar a un socio con una persona autorizada.");
            return false;
        }

        // Si pasa todas las validaciones, el socio puede ser eliminado
        System.out.println("Socio eliminado: " + id);
        return true;
    }
}
