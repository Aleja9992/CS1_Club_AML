/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package club;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<memberClub> members = new ArrayList<>();  // Lista para almacenar los socios
        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {
            principalMenu();
            String option = sc.nextLine();

            switch (option) {
                case "a":
                    ingresarSocio(sc, members);
                    break;
                case "b":
                    registrarNuevoSocio(sc, members);
                    break;
                case "c":
                    System.out.println("¡Gracias por visitar el club! Hasta luego.");
                    running = false;
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
            }
        }
    }

    // Método para ingresar un socio
    public static void ingresarSocio(Scanner sc, ArrayList<memberClub> members) {
        System.out.println("Ingresa tu ID de socio: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer

        memberClub socio = buscarSocio(id, members);
        if (socio != null) {
            System.out.println("Bienvenido, " + socio.getName() + "!");
            partnerMenu();
            String option = sc.nextLine();
            switch (option) {
                case "a":
                    System.out.println("Ingresa el nombre de la persona autorizada:");
                    String nombreAutorizado = sc.nextLine();
                    socio.registrarPersonaAutorizada(nombreAutorizado);
                    break;
                case "b":
                    System.out.println("Ingresa el monto a aumentar en fondos:");
                    double monto = sc.nextDouble();
                    sc.nextLine(); // Limpiar buffer
                    socio.aumentarFondos(monto);
                    break;
                case "c":
                    socio.pagarFactura();
                    break;
                case "d":
                    socio.mostrarDetalles();
                    break;
                default:
                    System.out.println("Opción no válida.");
                    break;
            }
        } else {
            System.out.println("No se encontró un socio con ese ID.");
        }
    }

    // Método para registrar un nuevo socio
    public static void registrarNuevoSocio(Scanner sc, ArrayList<memberClub> members) {
        System.out.println("Ingresa tu ID: ");
        int id = sc.nextInt();
        sc.nextLine(); // Limpiar buffer
        System.out.println("Ingresa tu nombre: ");
        String name = sc.nextLine();
        System.out.println("Ingresa el tipo de suscripción (Regular/VIP): ");
        String suscriptionType = sc.nextLine();
        System.out.println("Ingresa los fondos disponibles: ");
        double fondos = sc.nextDouble();
        sc.nextLine(); // Limpiar buffer

        // Crear el nuevo socio
        memberClub newMember = new memberClub(id, name, suscriptionType, fondos, new ArrayList<>(), new ArrayList<>());
        members.add(newMember);
        System.out.println("¡Socio registrado exitosamente!");
    }

    // Método para buscar un socio por ID
    public static memberClub buscarSocio(int id, ArrayList<memberClub> members) {
        for (memberClub socio : members) {
            if (socio.getId() == id) {
                return socio;
            }
        }
        return null;
    }

    // Menú principal
    public static void principalMenu() {
        System.out.println("\n============= Bienvenido al Club ==============");
        System.out.println("a. Ingresar socio");
        System.out.println("b. Registrar nuevo socio");
        System.out.println("c. Salir");
        System.out.println("Elige una opción: ");
    }

    // Menú de opciones del socio
    public static void partnerMenu() {
        System.out.println("\n============= Opciones de Socio ==============");
        System.out.println("a. Registrar persona autorizada");
        System.out.println("b. Aumentar fondos");
        System.out.println("c. Pagar facturas pendientes");
        System.out.println("d. Mostrar detalles del socio");
        System.out.println("Elige una opción: ");
    }
}
