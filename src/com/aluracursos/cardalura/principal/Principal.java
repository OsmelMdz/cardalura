package com.aluracursos.cardalura.principal;

import com.aluracursos.cardalura.compra.Compra;
import com.aluracursos.cardalura.tarjeta.TarjetaDeCredito;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("===================================================");
        System.out.println("                  BIENVENIDO A CARD ALURA          ");
        System.out.println("===================================================");
        System.out.println("Este sistema le permitirá registrar sus compras utilizando una tarjeta de crédito virtual.");
        System.out.println("Por favor, siga las instrucciones paso a paso.\n");

        System.out.print("Ingrese el monto máximo (límite) disponible en su tarjeta: $");
        double limite = scanner.nextDouble();
        scanner.nextLine();
        TarjetaDeCredito tarjeta = new TarjetaDeCredito(limite);
        System.out.printf("El límite inicial de su tarjeta es: $%.2f%n", tarjeta.getLimite());

        while (true) {
            System.out.println("\n---------------------------------------------------");
            System.out.println("               REGISTRO DE UNA NUEVA COMPRA        ");
            System.out.println("---------------------------------------------------");
            System.out.println("Ingrese los datos de la compra a continuación:");

            System.out.print("Descripción de la compra (ej. 'Zapatos', 'Farmacia'): ");
            String descripcion = scanner.nextLine();

            System.out.print("Monto de la compra (en pesos mexicanos): $");
            double valor = scanner.nextDouble();
            scanner.nextLine();

            System.out.printf("Procesando su compra: \"%s\" por un total de $%.2f... ", descripcion, valor);
            Compra compra = new Compra(valor, descripcion);
            boolean exito = tarjeta.lanzarCompra(compra);

            if (exito) {
                System.out.println("Compra aprobada exitosamente.");
                System.out.printf("Saldo disponible después de esta compra: $%.2f%n", tarjeta.getSaldo());

                if (tarjeta.getSaldo() == 0) {
                    System.out.println("\n*** Ha alcanzado el límite de su tarjeta. Ya no dispone de saldo para nuevas compras. ***");
                    break;
                }

                System.out.print("¿Desea registrar otra compra? Ingrese 0 para NO o 1 para SÍ: ");
                int opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcion == 0) {
                    System.out.println("Usted ha finalizado el registro de compras.");
                    break;
                }

            } else {
                System.out.println("Compra rechazada. No cuenta con suficiente saldo para realizar esta operación.");
                break;
            }
        }

        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        System.out.println("\n===================================================");
        System.out.println("                 RESUMEN DE COMPRAS                ");
        System.out.println("===================================================");
        System.out.println("Fecha y hora de operación: " + fechaActual.format(formatoFecha));
        System.out.println("---------------------------------------------------");

        double total = 0;
        int contador = 1;
        for (Compra compra : tarjeta.getComprasOrdenadasPorValor()) {
            System.out.printf("%d. %s%n", contador++, compra);
            total += compra.getValor();
        }

        System.out.println("---------------------------------------------------");
        System.out.printf("TOTAL DE COMPRAS REALIZADAS: $%.2f%n", total);
        System.out.printf("SALDO FINAL DISPONIBLE EN LA TARJETA: $%.2f%n", tarjeta.getSaldo());
        System.out.println("---------------------------------------------------");
        System.out.println("     Gracias por utilizar el sistema CARD ALURA    ");
        System.out.println("===================================================");

        scanner.close();
    }
}
