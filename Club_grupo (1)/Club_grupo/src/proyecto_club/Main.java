package proyecto_club;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;

public class Main { 

    public static void main(String[] args) { 
        Club c = new Club(); // Crea una nueva instancia de la clase Club
        Partner partner = null; // Inicializa la variable partner como null
        boolean switche = true; // Bandera para controlar el bucle del menú principal

        // Bucle que se ejecuta mientras switche sea verdadero
        while (switche) {
            try {
                // Muestra un cuadro de diálogo con el menú principal y captura la opción seleccionada
                String option = JOptionPane.showInputDialog(
                    null, principalMenu(), "Club Social",
                    JOptionPane.QUESTION_MESSAGE
                );

                if (option != null) { // Verifica si se ha seleccionado una opción
                    // Switch para manejar las diferentes opciones del menú
                    switch (option) {
                        case "a": // Opción para ingresar como socio
                            if (partner != null || !c.getPartner().isEmpty()) { // Verifica si ya hay un socio registrado
                                ingresarSocio(c); // Llama al método para ingresar como socio
                            } else {
                                // Muestra un mensaje si no se ha inscrito como socio
                                JOptionPane.showMessageDialog(
                                    null, "Debes inscribirte primero como socio."
                                );
                            }
                            break;

                        case "b": // Opción para registrar un nuevo socio
                            partner = registrarNuevoSocio(c); // Llama al método y asigna el nuevo socio
                            break;

                        case "c": // Opción para buscar un socio
                            String cedulaBuscar = requestCedula("Ingresa la cédula del socio:"); // Solicita la cédula
                            c.searchPartner(cedulaBuscar); // Busca al socio en la lista
                            break;

                        case "d": // Opción para mostrar información de socios
                            c.showInfoPartners(); // Muestra la información de los socios registrados
                            break;

                        case "e": // Opción para eliminar un socio
                            String cedulaEliminar = requestCedula("Ingresa la cédula del socio:"); // Solicita la cédula
                            c.deletePartner(cedulaEliminar); // Elimina al socio correspondiente
                            break;
                            
                        case "f":
                            c.savePartnersToFile();
                            JOptionPane.showMessageDialog(
                                null, "Cambios guardados."
                            );
                            break;

                        case "g": // Opción para salir del programa
                            JOptionPane.showMessageDialog(
                                null, "¡Gracias por visitar el club! Hasta luego."
                            );
                            switche = false; // Cambia la bandera para salir del bucle
                            break;

                        default: // Opción no válida
                            JOptionPane.showMessageDialog(
                                null, "Opción no válida. Inténtalo de nuevo."
                            );
                            break;
                    }
                } else { // Si no se seleccionó ninguna opción (se cerró el diálogo)
                    JOptionPane.showMessageDialog(
                        null, "¡Gracias por visitar el club! Hasta luego."
                    );
                    switche = false; // Cambia la bandera para salir del bucle
                }
            } catch (HeadlessException e) { 
                JOptionPane.showMessageDialog(
                    null, "Ha ocurrido un error: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE
                );
            }
        }
    }

    // Método para ingresar como socio
    public static void ingresarSocio(Club c) {
        try {
            // Solicita la cédula del socio
            String cedulaIngresar = requestCedula("Ingresa tu cédula de socio:");

            // Verifica si la cédula ingresada es correcta
            if (c.login(cedulaIngresar)) {
                Partner partner = c.returnPartner(cedulaIngresar); // Obtiene el objeto Partner
                boolean stayInPartnerMenu = true; // Bandera para controlar el bucle del menú de socio

                // Bucle que se ejecuta mientras stayInPartnerMenu sea verdadero
                while (stayInPartnerMenu) {
                    try {
                        // Muestra un cuadro de diálogo con el menú del socio y captura la opción seleccionada
                        String option = JOptionPane.showInputDialog(
                            null, partnerMenu(), "Opciones de socio",
                            JOptionPane.QUESTION_MESSAGE
                        );

                        // Switch para manejar las diferentes opciones del menú de socio
                        switch (option) {
                            case "a": // Opción para añadir un afiliado
                                String cedulaAfiliado = requestCedula("Ingresa la cédula del afiliado:"); // Solicita la cédula del afiliado
                                String nameAfiliado = JOptionPane.showInputDialog(
                                    null, "Ingresa el nombre del afiliado:" // Solicita el nombre del afiliado
                                );

                                // Crea una nueva instancia de Affiliate y la añade al socio
                                Affiliate newAffiliate = new Affiliate(
                                    cedulaAfiliado, nameAfiliado,
                                    partner.getTypeSubscription(), partner
                                );
                                partner.addAffiliate(newAffiliate); // Añade el afiliado al socio
                                break;

                            case "b": // Opción para registrar consumo
                                String itemInvoice = JOptionPane.showInputDialog(
                                    null, "Ingresa el concepto de la factura:" // Solicita el concepto de la factura
                                );
                                // Solicita el valor de la factura y la convierte a float
                                float valueInvoice = Float.parseFloat(
                                    JOptionPane.showInputDialog(
                                        null, "Ingresa el valor de la factura:"
                                    )
                                );

                                // Crea una nueva factura e la registra en el socio
                                Invoice newInvoice = new Invoice(
                                    partner.getInvoices().size() + 1,
                                    itemInvoice, valueInvoice, partner.getNameMember()
                                );
                                partner.registerConsumption(newInvoice);
                                break;

                            case "c": // Opción para añadir fondos
                                // Solicita la cantidad de fondos a añadir y la convierte a double
                                double fundsToAdd = Double.parseDouble(
                                    JOptionPane.showInputDialog(
                                        null, "Ingresa la cantidad de fondos a añadir:"
                                    )
                                );
                                partner.addFunds(fundsToAdd); // Añade los fondos al socio
                                break;

                            case "d": // Opción para mostrar todas las facturas
                                partner.showAllInvoices(); // Muestra las facturas del socio
                                break;

                            case "e": // Opción para pagar factura
                                String invoiceIdToPay = JOptionPane.showInputDialog(
                                    null, "Ingresa el ID de la factura que deseas pagar:" // Solicita el ID de la factura
                                );
                                int invoiceId = Integer.parseInt(invoiceIdToPay); // Convierte el ID a int

                                // Busca la factura por ID
                                Invoice invoiceToPay = partner.findInvoiceById(invoiceId);
                                if (invoiceToPay != null) { // Verifica si la factura existe
                                    partner.payInvoice(invoiceToPay); // Paga la factura
                                } else {
                                    // Muestra un mensaje si no se encuentra la factura
                                    JOptionPane.showMessageDialog(
                                        null, "Factura no encontrada.", "Error",
                                        JOptionPane.ERROR_MESSAGE
                                    );
                                }
                                break;
                            case "f":
                                partner.showAffiliates();
                                break;

                            case "g": // Opción para eliminar un afiliado
                                String affiliateId = requestCedula("Ingresa la "
                                     + "cédula del afiliado a eliminar:"); // Solicita la cédula del afiliado
                                partner.removeAffiliate(affiliateId); // Elimina el afiliado
                                break;

                            case "h": // Opción para salir del menú de socio
                                JOptionPane.showMessageDialog(
                                    null, "Saliendo del menú de socio."
                                );
                                stayInPartnerMenu = false; // Cambia la bandera para salir del bucle
                                break;

                            default: // Opción no válida
                                JOptionPane.showMessageDialog(
                                    null, "Opción no válida. Inténtalo de nuevo."
                                );
                                break;
                        }
                    } catch (Exception e) { // Maneja cualquier excepción al procesar la opción
                        JOptionPane.showMessageDialog(
                            null, "Error al procesar la opción: " + e.getMessage(),
                            "Error", JOptionPane.ERROR_MESSAGE
                        );
                    }
                }
            } else { // Si la cédula ingresada es incorrecta
                JOptionPane.showMessageDialog(
                    null, "Cédula incorrecta. Inténtalo de nuevo."
                );
            }
        } catch (Exception e) { // Maneja excepciones al intentar ingresar como socio
            JOptionPane.showMessageDialog(
                null, "Error en ingresar socio: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
    }

    // Método para registrar un nuevo socio
    public static Partner registrarNuevoSocio(Club c) {
        try {
            // Solicita la cédula del nuevo socio
            String cedula = requestCedula("Ingresa la cédula del nuevo socio:");
            String nameMember = JOptionPane.showInputDialog(
                null, "Ingresa el nombre del nuevo socio:" // Solicita el nombre del nuevo socio
            );
            String typeSubscription = JOptionPane.showInputDialog(
                null, "Ingresa el tipo de suscripción (VIP/Regular):" // Solicita el tipo de suscripción
            );

            // Crea un nuevo objeto Partner dependiendo del tipo de suscripción
            Partner newPartner;
            if (typeSubscription.equalsIgnoreCase("VIP")) {
                newPartner = new VIPPartner(cedula, nameMember); // Crea un socio VIP
            } else {
                newPartner = new RegularPartner(cedula, nameMember); // Crea un socio Regular
            }

            c.addPartner(newPartner); // Añade el nuevo socio al club
            return newPartner; // Devuelve el nuevo socio
        } catch (HeadlessException e) { // Maneja excepciones al registrar el nuevo socio
            JOptionPane.showMessageDialog(
                null, "Error en registrar nuevo socio: " + e.getMessage(),
                "Error", JOptionPane.ERROR_MESSAGE
            );
            return null; // Devuelve null en caso de error
        }
    }

    // Método para solicitar la cédula
    public static String requestCedula(String mensaje) {
        String cedula;
        while (true) {
            cedula = JOptionPane.showInputDialog(null, mensaje); // Muestra un cuadro de diálogo para ingresar la cédula
            // Verifica que la cédula no sea null y contenga solo números
            if (cedula != null && cedula.matches("\\d+")) {
                break; // Sale del bucle si la cédula es válida
            }
            // Muestra un mensaje de error si la cédula no es válida
            JOptionPane.showMessageDialog(
                null, "La cédula debe contener solo números. Inténtalo de nuevo.",
                "Error", JOptionPane.ERROR_MESSAGE
            );
        }
        return cedula; // Devuelve la cédula válida
    }

    // Método para mostrar el menú principal
    public static String principalMenu() {
        return "Menú Principal:\n" +
                "a. Ingresar como socio\n" +
                "b. Registrar nuevo socio\n" +
                "c. Buscar socio\n" +
                "d. Mostrar información de socios\n" +
                "e. Eliminar socio\n" +
                "f. Guardar cambios\n" + 
                "g. Salir";
    }

    // Método para mostrar el menú del socio
    public static String partnerMenu() {
        return "Menú de Socio:\n" +
                "a. Añadir afiliado\n" +
                "b. Registrar consumo\n" +
                "c. Añadir fondos\n" +
                "d. Mostrar facturas\n" +
                "e. Pagar factura\n" +
                "f. Mostrar mis afiliados\n" +
                "g. Eliminar afiliado\n" +
                "h. Salir";
    }
}