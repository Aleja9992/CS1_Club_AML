/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Club c = new Club();
        Partner partner = null; // Inicializamos partner como null

        Scanner sc = new Scanner(System.in);
        String option;
        boolean switche = true;

        while (switche) {
            principalMenu();
            option = sc.nextLine();
            switch (option) {
                case "a":
                    // Solo llamamos a ingresarSocio si partner no es null
                    if (partner != null) {
                        ingresarSocio(sc, c, partner);
                    } else {
                        System.out.println("Debes inscribirte primero como socio.");
                    }
                    break;

                case "b":
                    partner = registrarNuevoSocio(sc, c); // Asignamos el nuevo socio a partner
                    break;

                case "c":
                    System.out.println("Ingresa la cedula del socio: ");
                    String cedula = sc.nextLine();
                    c.searchPartner(cedula);
                    break;

                case "d":
                    System.out.println("Opcion aún no configurada");
                    break;

                case "e":
                    System.out.println("¡Gracias por visitar el club! Hasta luego.");
                    switche = false;
                    break;

                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    public static void ingresarSocio(Scanner sc, Club c, Partner partner) {
        System.out.println("Ingresa tu cedula de socio: ");
        String cedula = sc.nextLine();

        if (c.login(cedula)) {
            partnerMenu();
            String option = sc.nextLine();
            switch (option) {
                case "a":
                    System.out.println("Ingresa la cedula del afiliado: ");
                    cedula = sc.nextLine();
                    System.out.println("Ingresa tu nombre: ");
                    String nameMember = sc.nextLine();
                    Affiliate affiliate = new Affiliate(cedula, nameMember, partner.getTypeSubscription(), partner);
                    partner.addAffiliate(affiliate);
                    break;
                case "b":
                    System.out.println("Ingresa el monto a aumentar en fondos: ");
                    double funds = sc.nextDouble();
                    sc.nextLine(); // Limpiar el buffer
                    partner.addFuns(funds);
                    break;
                case "c":
                    break;
            }
        } else {
            System.out.println("La cedula ingresada no existe dentro del club.");
        }
    }

    public static Partner registrarNuevoSocio(Scanner sc, Club c) {
        System.out.println("Ingresa tu cedula: ");
        String cedula = sc.nextLine();
        System.out.println("Ingresa tu nombre: ");
        String nameMember = sc.nextLine();
        System.out.println("Ingresa el tipo de suscripción (Regular/VIP): ");
        String typeSubscription = sc.nextLine();

        // Crear el nuevo socio
        Partner partner = new Partner(cedula, nameMember, typeSubscription);
        c.addPartner(partner);
        return partner; // Retornamos el nuevo socio
    }

    public static void principalMenu() {
        System.out.println("=============Bienvenido al club===============");
        System.out.println("a. Ingresar");
        System.out.println("b. Inscribir socio");
        System.out.println("c. Buscar socio");
        System.out.println("d. Ver socios");
        System.out.println("e. Salir");
        System.out.println("Elige una opción: ");
    }

    public static void partnerMenu() {
        System.out.println("=============Bienvenido================");
        System.out.println("a. Afiliar socio");
        System.out.println("b. Aumentar fondos");
        System.out.println("c. Pagar factura");
        System.out.println("d. Regresar");
        System.out.println("Elige una opción: ");
    }
}
