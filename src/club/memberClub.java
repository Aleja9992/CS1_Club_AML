package club;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class memberClub {
    
    private int id; 
    private String name;
    private String suscriptionType;
    private double fondosDisponibles;
    private List<String> facturasPendientes = new ArrayList<>();
    private List<String> personasAutorizadas = new ArrayList<>();

    // Constructor corregido para recibir listas
    public memberClub(int id, String name, String suscriptionType, double fondosDisponibles, List<String> facturasPendientes, List<String> personasAutorizadas) {
        this.id = id;
        this.name = name;
        this.suscriptionType = suscriptionType;
        this.fondosDisponibles = fondosDisponibles;
        this.facturasPendientes = facturasPendientes;
        this.personasAutorizadas = personasAutorizadas;
    }   

    // Métodos getter y setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuscriptionType() {
        return suscriptionType;
    }

    public void setSuscriptionType(String suscriptionType) {
        this.suscriptionType = suscriptionType;
    }

    public double getFondosDisponibles() {
        return fondosDisponibles;
    }

    public void setFondosDisponibles(double fondosDisponibles) {
        this.fondosDisponibles = fondosDisponibles;
    }

    public List<String> getFacturasPendientes() {
        return facturasPendientes;
    }

    public void setFacturasPendientes(List<String> facturasPendientes) {
        this.facturasPendientes = facturasPendientes;
    }

    public List<String> getPersonasAutorizadas() {
        return personasAutorizadas;
    }

    public void setPersonasAutorizadas(List<String> personasAutorizadas) {
        this.personasAutorizadas = personasAutorizadas;
    }

    // Método para registrar una persona autorizada
    public void registrarPersonaAutorizada(String nombre) {
        if (!personasAutorizadas.contains(nombre)) {
            personasAutorizadas.add(nombre);
            System.out.println("Persona autorizada añadida: " + nombre);
        } else {
            System.out.println("Esta persona ya está autorizada.");
        }
    }

    // Método para agregar una factura pendiente
    public void agregarFacturaPendiente(String factura) {
        facturasPendientes.add(factura);
        System.out.println("Factura pendiente añadida: " + factura);
    }

    // Método corregido para pagar facturas
    public void pagarFactura() {
        if (!facturasPendientes.isEmpty()) {
            facturasPendientes.clear(); // Se vacía la lista de facturas
            System.out.println("Todas las facturas han sido pagadas.");
        } else {
            System.out.println("No hay facturas pendientes.");
        }
    }

    // Método para registrar el consumo
    public void registrarConsumo(double monto) {
        if (fondosDisponibles >= monto) {
            fondosDisponibles -= monto;
            System.out.println("Consumo registrado: " + monto);
        } else {
            System.out.println("Fondos insuficientes.");
        }
    }

    // Método para aumentar los fondos
    public void aumentarFondos(double monto) {
        fondosDisponibles += monto;
        System.out.println("Fondos aumentados en: " + monto);
    }

    // Método para eliminar socio
    public boolean eliminarSocio() {
        if (suscriptionType.equals("VIP")) {
            System.out.println("No se puede eliminar a un socio VIP.");
            return false;
        }
        if (!facturasPendientes.isEmpty()) {
            System.out.println("No se puede eliminar a un socio con facturas pendientes.");
            return false;
        }
        if (!personasAutorizadas.isEmpty()) {
            System.out.println("No se puede eliminar a un socio con una persona autorizada.");
            return false;
        }
        System.out.println("Socio eliminado: " + name);
        return true;
    }

    // Método para mostrar detalles del socio
    public void mostrarDetalles() {
        System.out.println("ID: " + id);
        System.out.println("Nombre: " + name);
        System.out.println("Tipo de suscripción: " + suscriptionType);
        System.out.println("Fondos disponibles: " + fondosDisponibles);
        System.out.println("Facturas pendientes: " + facturasPendientes);
        System.out.println("Personas autorizadas: " + personasAutorizadas);
    }
}
