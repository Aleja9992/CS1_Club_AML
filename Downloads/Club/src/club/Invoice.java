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
public class Invoice extends Partner{
    protected int idInvoice;
    protected String itemInvoice;
    protected float valueInvoice;
    protected boolean State;
    

    public Invoice(String cedula, String nameMember, double fundsAvailable, String typeSubscription, int idInvoice, String itemInvoice, float valueInvoice, boolean State) {
        super(cedula, nameMember, typeSubscription);
        this.idInvoice = idInvoice;
        this.itemInvoice = itemInvoice;
        this.valueInvoice = valueInvoice;
        this.State = false;
        
    }

    /**
     * @return the idInvoice
     */
    public int getIdInvoice() {
        return idInvoice;
    }

    /**
     * @param idInvoice the idInvoice to set
     */
    public void setIdInvoice(int idInvoice) {
        this.idInvoice = idInvoice;
    }

    /**
     * @return the itemInvoice
     */
    public String getItemInvoice() {
        return itemInvoice;
    }

    /**
     * @param itemInvoice the itemInvoice to set
     */
    public void setItemInvoice(String itemInvoice) {
        this.itemInvoice = itemInvoice;
    }

    /**
     * @return the valueInvoice
     */
    public float getValueInvoice() {
        return valueInvoice;
    }

    /**
     * @param valueInvoice the valueInvoice to set
     */
    public void setValueInvoice(float valueInvoice) {
        this.valueInvoice = valueInvoice;
    }

    /**
     * @return the State
     */
    public boolean isState() {
        return State;
    }

    /**
     * @param State the State to set
     */
    public void setState(boolean State) {
        this.State = State;
    }
    
    
}
