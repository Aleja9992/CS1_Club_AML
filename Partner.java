package club;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Partner {
   
    protected String cedula;
    protected String nameMember;
    protected double fundsAvailable;
    protected String typeSubscription;
    protected List<Invoice> invoices; // Asegúrate de que esta clase exista
    protected List<Affiliate> affiliates; // Asegúrate de que esta clase exista
    
    // Constructor
    public Partner(String cedula, String nameMember, String typeSubscription) {
        this.cedula = cedula;
        this.nameMember = nameMember;
        this.typeSubscription = typeSubscription;
        this.invoices = new ArrayList<>();
        this.affiliates = new ArrayList<>();
        
        // Asignación de fondos inicial según el tipo de suscripción
        if(typeSubscription.equals("Regular")){
            this.fundsAvailable = 50000;
        } else if(typeSubscription.equals("VIP")){
            this.fundsAvailable = 100000;
        }
    }

    Partner() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    // Método para buscar socio (debería estar en otra clase, probablemente en Club)
    // Lo elimino de aquí ya que debería estar en la clase correspondiente

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

    // Método para verificar si es VIP
    public boolean isVIP() {
        return typeSubscription.equals("VIP");
    }
    
    // Método para agregar un afiliado
    public void addAffiliate(Affiliate affiliate) {
        if (this.affiliates.size() >= 10 || this.fundsAvailable <= 0) {
            System.out.println("Has alcanzado el máximo de 10 personas autorizadas o ya no tienes fondos disponibles");
        } else {
            affiliates.add(affiliate);
            System.out.println("Afiliado añadido exitosamente: " + affiliate.getNameMember());
        }
    }

    // Método para aumentar fondos
    public void addFunds(double funds) {
        double maxValue;

        if (typeSubscription.equals("Regular")) {
            maxValue = 1000000;
        } else {
            maxValue = 5000000;
        }

        if (fundsAvailable + funds > maxValue) {
            System.out.println("No se pueden aumentar los fondos debido a que ya alcanzaste el tope máximo");
        } else {
            fundsAvailable += funds;
            System.out.println("Se han aumentado los fondos exitosamente. Fondos disponibles: " + fundsAvailable);
        }
    }

    void addFuns(double fundsAvailable) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
}
