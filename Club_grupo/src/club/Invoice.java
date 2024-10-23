/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_club;

import java.util.Random;

/**
 *
 * @author Usuario
 */
public class Invoice {
    protected int idInvoice;
    protected String itemInvoice;
    protected float valueInvoice;
    protected String namePartner;
    private boolean paid;  

    public Invoice(int idInvoice, String itemInvoice, float valueInvoice, String namePartner) {
        Random random = new Random();
        this.idInvoice = idInvoice;
        this.itemInvoice = itemInvoice;
        this.valueInvoice = valueInvoice;
        this.namePartner = namePartner;
        this.paid = false;
        
    }

    public int getIdInvoice() {
        return idInvoice;
    }

    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    public String getItemInvoice() {
        return itemInvoice;
    }

    public void setItemInvoice(String itemInvoice) {
        this.itemInvoice = itemInvoice;
    }

    public float getValueInvoice() {
        return valueInvoice;
    }

    public void setValueInvoice(float valueInvoice) {
        this.valueInvoice = valueInvoice;
    }

    public String getNamePartner() {
        return namePartner;
    }

    public void setNamePartner(String namePartner) {
        this.namePartner = namePartner;
    }

    double getValue() {
        this.valueInvoice = valueInvoice;
        return 0;
    }

    public boolean isPaid() {
        return paid;
    }

    // Método para marcar la factura como pagada
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
