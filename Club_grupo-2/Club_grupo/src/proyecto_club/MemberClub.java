/*
 * Esta es una interfaz que define el contrato para los miembros del club.
 * Cualquier clase que implemente esta interfaz debe proporcionar implementaciones
 * para los métodos definidos aquí.
 */
package proyecto_club; // Paquete donde se encuentra esta interfaz

/**
 *
 * @author Usuario
 */
public interface MemberClub { // Declaración de la interfaz MemberClub

    // Método para obtener la cédula del miembro
    String getCedula();

    // Método para obtener el nombre del miembro
    String getNameMember();

    // Método para obtener los fondos disponibles del miembro
    double getFundsAvailable();

    // Método para añadir fondos al miembro
    void addFunds(double funds);

    // Método para obtener el tipo de suscripción del miembro (VIP o Regular)
    String getTypeSubscription();  
}
