package proyecto_club;
import java.io.*;
import java.util.Scanner;
import customException.PartnerNotFoundException;
import javax.swing.JOptionPane;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class Club {
    private List<Partner> partners; // Lista que contiene todos los socios del Club

    // Constructor de la clase Club
    public Club() {
        this.partners = new ArrayList<>();// Inicializa la lista de socios como un ArrayList vacío
        loadPartnersFromFile();
    }

    // Método para agregar un nuevo socio al Club
    public boolean addPartner(Partner partner) {
        try {
            // Verificar si el Club ya tiene el máximo de socios permitido (35)
            if (partners.size() >= 35) {
                throw new Exception("El club ha alcanzado el máximo de 35 socios");
            }

            // Verificar si ya existe un socio con la misma cédula
            for (Partner p : partners) {
                if (p.getCedula().equals(partner.getCedula())) {
                    throw new Exception("Ya existe un socio con ese número de cédula");
                }
            }

            // Verificar si el nuevo socio es VIP y si ya hay 3 VIP en el Club
            if (partner.isVIP() && countVips() >= 3) {
                throw new Exception("El club ha alcanzado el máximo de socios de tipo VIP");
            }

            // Agregar el socio a la lista y retornar verdadero
            savePartnersToFile();
            return partners.add(partner);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false; // No se puede agregar más socios
        }
    }
    
    public List<Partner> getPartner(){
        return partners;
    }

    // Método para contar la cantidad de socios VIP en el Club
    public int countVips() {
        int count = 0; // Contador de socios VIP

        // Iterar sobre la lista de socios para contar los VIP
        for (Partner p : partners) {
            if (p.isVIP()) { // Si el socio es VIP
                count++; // Aumentar el contador
            }
        }
        return count; // Retornar el número total de socios VIP
    }

    // Método para buscar un socio por su cédula
    public Partner searchPartner(String cedula) {
        try {
            // Iterar sobre la lista de socios para encontrar el que coincida con la cédula
            for (Partner p : partners) {
                if (p.getCedula().equals(cedula)) {
                    // Si se encuentra el socio, mostrar su información
                    String message = "================Socio===================\n" +
                        "Cédula: " + p.getCedula() + "\n" +
                        "Nombre socio: " + p.getNameMember() + "\n" +
                        "Tipo de suscripción: " + p.getTypeSubscription() + "\n" +
                        "Fondos disponibles: " + p.getFundsAvailable();
                    JOptionPane.showMessageDialog(null, message, 
                        "Información del Socio", 
                    JOptionPane.INFORMATION_MESSAGE);
                    return p; // Retorna el socio encontrado
                }
            }
            throw new Exception("No existe el socio dentro del club");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", 
                JOptionPane.ERROR_MESSAGE);
            return null; // Retorna null si no se encontró
        }
    }

    // Método para eliminar un socio por su cédula
    public boolean deletePartner(String cedula) {
        try {
            Partner partner = searchPartner(cedula);
            if (partner == null) {
                throw new Exception("El socio no existe dentro del club.");
            }
            if (partner.isVIP()) {
                throw new Exception("No se puede eliminar un socio de tipo VIP.");
            }
            if (!partner.getAffiliates().isEmpty()) {
                throw new Exception("No puede eliminar un socio si tiene personas autorizadas.");
            }
            if (!partner.getInvoices().isEmpty()) {
                throw new Exception("No puedes eliminar un socio si tiene facturas pendientes de pago.");
            }
            partners.remove(partner);
            savePartnersToFile();
            JOptionPane.showMessageDialog(null, "Se ha eliminado el socio exitosamente.", 
                 "Éxito", JOptionPane.INFORMATION_MESSAGE);
            return true;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    // Método para verificar si un socio puede iniciar sesión
    public boolean login(String cedula) {
        // Iterar sobre la lista de socios para encontrar uno con la cédula dada
        for (Partner p : partners) {
            if (p.getCedula().equals(cedula)) {
                return true; // Si se encuentra, retornar verdadero
            }
        }
        return false; // Si no se encuentra, retornar falso
    }
    
    // Método para verificar que el socio dentro del club
    // Si existe lo retornamos
    public Partner returnPartner(String cedula) throws PartnerNotFoundException {
        for (Partner partner : partners) {
            if (partner.getCedula().equals(cedula)) {
                return partner; // Retorna el socio si se encuentra
            }
        }
        // Lanza una excepción si no se encuentra el socio
        throw new PartnerNotFoundException("Socio con cédula " + cedula + " no encontrado.");
    }

    // Método para mostrar información de todos los socios
    public String showInfoPartners() {
        StringBuilder info = new StringBuilder();
        for (Partner p : partners) {
            info.append("================Socio===================\n")
                .append("Cédula: ").append(p.getCedula()).append("\n")
                .append("Nombre socio: ").append(p.getNameMember()).append("\n")
                .append("Tipo de suscripción: ").append(p.getTypeSubscription()).append("\n")
                .append("Fondos disponibles: ").append(p.getFundsAvailable()).append("\n\n");
        }
        // Muestra la información de los socios o un mensaje si no hay
        if (info.length() == 0) {
            JOptionPane.showMessageDialog(null, "No hay socios", "Información", 
                JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, info.toString(), "Información de Socios", 
                JOptionPane.INFORMATION_MESSAGE);
        }
        return info.toString();
    }
    
    
    
    private void loadPartnersFromFile() {
        try (Scanner scanner = new Scanner(new File("socios.txt"))) {
            Partner partner = null;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] data = line.split(",");

                if (data.length >= 4 && !line.startsWith("afiliado,") && !line.startsWith("factura,")) {
                    // Nueva entrada de socio
                    String cedula = data[0];
                    String nameMember = data[1];
                    String typeSubscription = data[2];
                    double fundsAvailable = Double.parseDouble(data[3]);

                    partner = new Partner(cedula, nameMember, typeSubscription) {
                        @Override
                        protected void initializeFunds() {
                            // Implementación específica de la inicialización de fondos
                        }
                    };

                    partner.setFundsAvailable(fundsAvailable);
                    partners.add(partner);
                } else if (partner != null && line.startsWith("afiliado,")) {
                    // Cargar afiliado
                    String affiliateCedula = data[1];
                    String affiliateName = data[2];
                    Affiliate affiliate = new Affiliate(affiliateCedula, affiliateName, partner.getTypeSubscription(), partner);
                    partner.loadAffiliate(affiliate, false);
                } else if (partner != null && line.startsWith("factura,")) {
                    // Cargar factura
                    int invoiceId = Integer.parseInt(data[1]);
                    String concept = data[2];
                    float value = Float.parseFloat(data[3]);
                    Invoice invoice = new Invoice(invoiceId, concept, value, partner.getNameMember());
                    partner.registerConsumption(invoice);
                }
            }
        } catch (FileNotFoundException e) {
            JOptionPane.showMessageDialog(null, "No se encontró el archivo de socios. Se creará uno nuevo.", "Advertencia", JOptionPane.WARNING_MESSAGE);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al cargar los socios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    
    // Método para guardar la lista de socios en el archivo
    void savePartnersToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("socios.txt"))) {
            for (Partner partner : partners) {
                writer.write(partner.getCedula() + "," + partner.getNameMember() + "," + partner.getTypeSubscription() + "," + partner.getFundsAvailable());
                writer.newLine();
                // Guardar información de afiliados
                for (Affiliate affiliate : partner.getAffiliates()) {
                    writer.write("afiliado," + affiliate.getCedula() + "," + affiliate.getNameMember());
                    writer.newLine();
                }

                // Guardar información de facturas
                for (Invoice invoice : partner.getInvoices()) {
                    writer.write("factura," + invoice.getIdInvoice() + "," + invoice.getItemInvoice() + "," + invoice.getValueInvoice() + "," + invoice.getNamePartner());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error al guardar los socios: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    
    
    
    
    
}
