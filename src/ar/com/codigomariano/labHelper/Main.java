package ar.com.codigomariano.labHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ar.com.codigomariano.domain.Cliente;
import ar.com.codigomariano.domain.Ensayo;
import ar.com.codigomariano.domain.Muestra;
import ar.com.codigomariano.domain.Resultado;
import ar.com.codigomariano.domain.Usuario;
import ar.com.codigomariano.enums.EstadoMuestra;
import ar.com.codigomariano.enums.TipoCliente;


public class Main {
	
	private static Scanner scanner = new Scanner(System.in);
    private static List<Muestra> muestras = new ArrayList<>();
    private static List<Ensayo> ensayos = new ArrayList<>();
    private static int contadorMuestras = 1;

    public static void main(String[] args) {

        cargarEnsayosIniciales();
        menuPrincipal();
    }

    private static void menuPrincipal() {

        boolean salir = false;

        while (!salir) {

            System.out.println("\n===== LABHELPER =====");
            System.out.println("1. Registrar muestra");
            System.out.println("2. Listar muestras");
            System.out.println("3. Cambiar estado");
            System.out.println("4. Cargar resultado");
            System.out.println("5. Ver resultados");
            System.out.println("0. Salir");

            int opcion = Integer.parseInt(scanner.nextLine());

            switch (opcion) {

                case 1 -> registrarMuestra();

                case 2 -> listarMuestras();

                case 3 -> cambiarEstado();

                case 4 -> cargarResultado();

                case 5 -> verResultados();

                case 0 -> salir = true;

                default -> System.out.println("Opción inválida");
            }
        }
    }

    private static void registrarMuestra() {

        String codigo = "AQ-" + contadorMuestras++;

        System.out.print("Tipo de muestra: ");
        String tipo = scanner.nextLine();

        System.out.print("Cliente: ");
        String nombreCliente = scanner.nextLine();

        System.out.print("Observaciones: ");
        String observaciones = scanner.nextLine();

        Cliente cliente = new Cliente(
                1L,
                nombreCliente,
                "",
                TipoCliente.EMPRESA
        );

        Muestra muestra = new Muestra(
                codigo,
                tipo,
                cliente,
                observaciones
        );

        muestras.add(muestra);

        System.out.println("Muestra registrada.");
    }

    private static void listarMuestras() {

        for (Muestra muestra : muestras) {
            System.out.println(muestra);
        }
    }

    private static void cambiarEstado() {

        listarMuestras();

        System.out.print("Código de muestra: ");

        String codigo = scanner.nextLine();

        Muestra muestra = buscarMuestra(codigo);

        if (muestra == null) {
            return;
        }

        EstadoMuestra[] estados = EstadoMuestra.values();

        for (int i = 0; i < estados.length; i++) {
            System.out.println((i + 1) + ". " + estados[i]);
        }

        int opcion = Integer.parseInt(scanner.nextLine());

        muestra.setEstado(estados[opcion - 1]);
    }

    private static void cargarResultado() {

        listarMuestras();

        System.out.print("Código de muestra: ");

        String codigo = scanner.nextLine();

        Muestra muestra = buscarMuestra(codigo);

        if (muestra == null) {
            return;
        }

        for (int i = 0; i < ensayos.size(); i++) {

            System.out.println(
                    (i + 1)
                    + ". "
                    + ensayos.get(i).getNombre()
            );
        }

        int opcion = Integer.parseInt(scanner.nextLine());

        Ensayo ensayo = ensayos.get(opcion - 1);

        System.out.print("Valor obtenido: ");

        String valor = scanner.nextLine();

        System.out.print("Observaciones: ");

        String observacion = scanner.nextLine();

        Resultado resultado = new Resultado(
                muestra,
                ensayo,
                valor,
                observacion
        );

        muestra.getResultados().add(resultado);

        System.out.println("Resultado agregado.");
    }

    private static void verResultados() {

        System.out.print("Código de muestra: ");

        String codigo = scanner.nextLine();

        Muestra muestra = buscarMuestra(codigo);

        if (muestra == null) {
            return;
        }

        for (Resultado resultado : muestra.getResultados()) {
            System.out.println(resultado);
        }
    }

    private static Muestra buscarMuestra(String codigo) {

        for (Muestra muestra : muestras) {

            if (muestra.getCodigo().equalsIgnoreCase(codigo)) {
                return muestra;
            }
        }

        System.out.println("Muestra no encontrada.");

        return null;
    }

    private static void cargarEnsayosIniciales() {

        ensayos.add(
                new Ensayo(
                        "pH",
                        "pH",
                        "Determinación de pH"
                )
        );

        ensayos.add(
                new Ensayo(
                        "Conductividad",
                        "uS/cm",
                        "Conductividad eléctrica"
                )
        );

        ensayos.add(
                new Ensayo(
                        "Viscosidad",
                        "cP",
                        "Viscosidad"
                )
        );
    }

}
