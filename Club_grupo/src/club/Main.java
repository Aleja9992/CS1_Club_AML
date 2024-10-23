/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto_club;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Main {
    public static void main(String[] args) {
        club c = new club();
        Partner partner = null; // Inicializamos partner como null
        boolean switche = true;

        while (switche) {
            String option = JOptionPane.showInputDialog(null, principalMenu(), "Club Social", JOptionPane.QUESTION_MESSAGE);

            if (option != null) {
                switch (option) {
                    case "a":
                        if (partner != null) {
                            ingresarSocio(c, partner);
                        } else {
                            JOptionPane.showMessageDialog(null, "Debes inscribirte primero como socio.");
                        }
                        break;

                    case "b":
                        partner = registrarNuevoSocio(c);
                        break;

                    case "c":
                        String cedulaBuscar = JOptionPane.showInputDialog(null, "Ingresa la cédula del socio:");
                        c.searchPartner(cedulaBuscar);
                        break;

                    case "d":
                        c.showInfoPartners();
                        break;

                    case "e":
                        String cedulaEliminar = JOptionPane.showInputDialog(null, "Ingresa la cédula del socio:");
                        c.deletePartner(cedulaEliminar);
                        break;

                    case "f":
                        JOptionPane.showMessageDialog(null, "¡Gracias por visitar el club! Hasta luego.");
                        switche = false;
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "¡Gracias por visitar el club! Hasta luego.");
                switche = false;
            }
        }
    }

    public static void ingresarSocio(club c, Partner partner) {
    String cedulaIngresar = JOptionPane.showInputDialog(null, "Ingresa tu cédula de socio:");

    if (c.login(cedulaIngresar)) {
        boolean stayInPartnerMenu = true;  // Variable para controlar el bucle del menú de socios

        while (stayInPartnerMenu) {
            String option = JOptionPane.showInputDialog(null, partnerMenu(), "Opciones de socio", JOptionPane.QUESTION_MESSAGE);

            switch (option) {
                case "a":
                    String cedulaAfiliado = JOptionPane.showInputDialog(null, "Ingresa la cédula del afiliado:");
                    String nameAfiliado = JOptionPane.showInputDialog(null, "Ingresa el nombre del afiliado:");
                    

                    Affiliate newAffiliate = new Affiliate(cedulaAfiliado, nameAfiliado, partner.getTypeSubscription(), partner);
                    partner.addAffiliate(newAffiliate);
                    break;

                case "b":
                    String itemInvoice = JOptionPane.showInputDialog(null, "Ingresa el concepto de la factura:");
                    float valueInvoice = Float.parseFloat(JOptionPane.showInputDialog(null, "Ingresa el valor de la factura:"));
                    Invoice newInvoice = new Invoice(partner.getInvoices().size() + 1, itemInvoice, valueInvoice, partner.getNameMember());
                    partner.registerConsumption(newInvoice);
                    break;

                case "c":
                    double fundsToAdd = Double.parseDouble(JOptionPane.showInputDialog(null, "Ingresa la cantidad de fondos a añadir:"));
                    partner.addFunds(fundsToAdd);
                    break;

                case "d":
                    partner.showAllInvoices();  // Mostrar facturas pendientes y pagadas
                    break;

                case "e":
                    String invoiceIdToPay = JOptionPane.showInputDialog(null, "Ingresa el ID de la factura que deseas pagar:");
                    int invoiceId = Integer.parseInt(invoiceIdToPay);

                    // Buscar la factura por ID
                    Invoice invoiceToPay = partner.findInvoiceById(invoiceId);

                    if (invoiceToPay != null) {
                        partner.payInvoice(invoiceToPay);  // Pagar la factura encontrada
                    } else {
                        JOptionPane.showMessageDialog(null, "Factura no encontrada.", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    break;

                case "f":
                    String affiliateId = JOptionPane.showInputDialog(null, "Ingresa la cédula del afiliado a eliminar:");
                    partner.removeAffiliate(affiliateId);  // Eliminar afiliado (verifica facturas pendientes)
                    break;

                case "g":
                    JOptionPane.showMessageDialog(null, "Saliendo del menú de socio.");
                    stayInPartnerMenu = false;  // Rompe el bucle para salir del menú de socios
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        }
    } else {
        JOptionPane.showMessageDialog(null, "Cédula incorrecta. Inténtalo de nuevo.");
    }
}


    public static Partner registrarNuevoSocio(club c) {
        String cedula = JOptionPane.showInputDialog(null, "Ingresa la cédula del nuevo socio:");
        String nameMember = JOptionPane.showInputDialog(null, "Ingresa el nombre del nuevo socio:");
        String typeSubscription = JOptionPane.showInputDialog(null, "Ingresa el tipo de suscripción (VIP/Regular):");

        Partner newPartner;
        if (typeSubscription.equalsIgnoreCase("VIP")) {
            newPartner = new ViPPartner(cedula, nameMember);
        } else {
            newPartner = new RegularPartner(cedula, nameMember);
        }

        c.addPartner(newPartner);
        return newPartner; // Retorna el socio recién creado
    }

    public static String principalMenu() {
        return "Menú Principal:\n" +
                "a. Ingresar como socio\n" +
                "b. Registrar nuevo socio\n" +
                "c. Buscar socio\n" +
                "d. Mostrar información de socios\n" +
                "e. Eliminar socio\n" +
                "f. Salir";
    }

    public static String partnerMenu() {
        return "Menú de Socio:\n" +
                "a. Añadir afiliado\n" +
                "b. Registrar consumo\n" +
                "c. Añadir fondos\n" +
                "d. Mostrar facturas\n" +
                "e. Pagar factura\n" +
                "f. Eliminar afiliado\n" +
                "g. Salir";
    }
}


