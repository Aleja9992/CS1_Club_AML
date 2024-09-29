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
public class Partner{
   
    protected String cedula;
    protected String nameMember;
    protected double fundsAvailable;
    protected String typeSubscription;
    protected List<Invoice> invoices;
    protected List<Affiliate> affiliates;
    
    

    public Partner(String cedula, String nameMember, String typeSubscription) {
        this.cedula = cedula;
        this.nameMember = nameMember;
        this.typeSubscription = typeSubscription;
        this.invoices = new ArrayList<>();
        this.affiliates = new ArrayList<>();
        
        
        if(typeSubscription.equals("Regular")){
            this.fundsAvailable = 50000;
        }
        else if(typeSubscription.equals("VIP")){
            this.fundsAvailable = 100000;
        }
        
        
    }

    

    /**
     * @return the cedula
     */
    public String getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nameMember
     */
    public String getNameMember() {
        return nameMember;
    }

    /**
     * @param nameMember the nameMember to set
     */
    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    /**
     * @return the fundsAvailable
     */
    public double getFundsAvailable() {
        return fundsAvailable;
    }

    /**
     * @param fundsAvailable the fundsAvailable to set
     */
    public void setFundsAvailable(double fundsAvailable) {
        this.fundsAvailable = fundsAvailable;
    }

    /**
     * @return the typeSubscription
     */
    public String getTypeSubscription() {
        return typeSubscription;
    }

    /**
     * @param typeSubscription the typeSubscription to set
     */
    public void setTypeSubscription(String typeSubscription) {
        this.typeSubscription = typeSubscription;
    }

    /**
     * @return the invoices
     */
    public List<Invoice> getInvoices() {
        return invoices;
    }

    /**
     * @param invoices the invoices to set
     */
    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    /**
     * @return the affiliates
     */
    public List<Affiliate> getAffiliates() {
        return affiliates;
    }

    /**
     * @param affiliates the affiliates to set
     */
    public void setAffiliates(List<Affiliate> affiliates) {
        this.affiliates = affiliates;
    }
    

    public boolean isVIP() {
        return typeSubscription.equals("VIP");
    }
    
    public void addAffiliate( Affiliate affiliate){
        if(this.affiliates.size()>=10 || this.fundsAvailable<=0){
            System.out.println("Has alcanzado el máximo de 10 personas autorizadas o ya no tienes fondos disponibles");
        }else{
            
            affiliates.add(affiliate);
        }
    }
    public void addFuns(double funds){
        
        double maxvalue;
        
        if (typeSubscription.equals("Regular")) {
            maxvalue = 1000000;
        } else {
            maxvalue = 5000000;
        }

        
        if(fundsAvailable + funds >maxvalue ){
            System.out.println("No se pueden aumentar los fondos debido a que ya alcanzaste el tope máximo");
        }else{
            fundsAvailable = fundsAvailable + funds;
            System.out.println("Se han aumentado los fondos exitosamente." + fundsAvailable);
            
        }
        
    }

    

   

    
    
    
    
    
    
    
    
    
    
    
    
}
