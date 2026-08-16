package ar.com.codigomariano.labHelper;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import ar.com.codigomariano.labHelper.domain.*;
import ar.com.codigomariano.labHelper.enums.*;

public class App {
    
    private static List<Usuario> tablaUsuarios = new ArrayList<>();
    private static List<Muestra> tablaMuestras = new ArrayList<>();
    private static List<Cliente> tablaClientes = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        inicializarDatosMaestros();
        
        Usuario usuarioLogueado = portalAcceso(scanner);
        
        if (usuarioLogueado != null) {
            System.out.println("\n✅ ¡Acceso concedido! Bienvenido, " + usuarioLogueado.getNombreCompleto());
            
            Rol rolActivo = usuarioLogueado.getRoles().iterator().next(); 
            
            if (rolActivo.getNombre().equalsIgnoreCase("cliente")) {
                mostrarPortalCliente(scanner, usuarioLogueado);
            } else {
                mostrarDashboard(scanner, usuarioLogueado);
            }
        } else {
            System.out.println("\n❌ Demasiados intentos fallidos. Aplicación cerrada.");
        }
        scanner.close();
    }

    private static void inicializarDatosMaestros() {
    	Password analistaPass=new Password(1L,"123");
        Usuario analista = new Usuario(1L, "juan.p", "Juan Perez", "juan@gmail.com", analistaPass);
        Rol analist =new Rol(1L,"Analista");
        analista.agregarRol(analist);
        Password supervisorPass=new Password(2L, "456");
        Usuario supervisor = new Usuario(2L, "carlos.sup", "Carlos Super", "carlos@gmail.com", supervisorPass);
        Rol superv=new Rol(2L,"Supervisor");
        supervisor.agregarRol(superv);
        Password clientePass=new Password(3L,"567");
        Usuario clienteUser = new Usuario(3L, "mary", "Maria Milagros", "maria@gmail.com", clientePass);
        Rol client=new Rol(3L,"Cliente");
        clienteUser.agregarRol(client);
        
        tablaUsuarios.add(analista);
        tablaUsuarios.add(supervisor);
        tablaUsuarios.add(clienteUser);

        Cliente c1 = new Cliente(101L, "CREAS", TipoCliente.EMPRESA, clienteUser);
        tablaClientes.add(c1);
        
        NotaTexto notaMuestra=new NotaTexto(1L,"Placas de titanio porosas para electrolizador alcalino");
        Muestra m1 = new Muestra(1L, c1,notaMuestra );
        Ensayo e1 = new Ensayo(502L, "Voltametría Cíclica", TipoEnsayo.INSTRUMENTAL, analista);
        e1.agregarResultado(new Resultado(1L, "Potencial Ep", "V", 0.40, 0.55));
        e1.getResultadoXId(1L).setValorObtenido(0.48); 
        e1.agregarResultado(new Resultado(2L, "Corriente Ip", "mA", 1.50, 3.00)); 
        
        m1.asignarEnsayo(e1);
        tablaMuestras.add(m1);
    }
    //PORTAL DE ACCESO
    private static Usuario portalAcceso(Scanner sc) {
    	Password password=new Password(null,null);
        System.out.println("\n+-----------------------------------------+");
        System.out.println("               LABHELPER v1.0            ");
        System.out.println("+-----------------------------------------+");
        System.out.print("Usuario (Email): ");
        String email = sc.next();
        System.out.print("Contraseña: ");
        password.setValor(sc.next());
        
        for (Usuario u : tablaUsuarios) {
            if (u.getEmail().equalsIgnoreCase(email) && u.autenticarUser(password)) {
                return u;
            }
        }
        return null;
    }

    // PANEL DE CONTROL 
    private static void mostrarDashboard(Scanner sc, Usuario usuario) {
        while (true) {
            System.out.println("\n+-------------------------------------------------------------------------+");
            System.out.println(" LABHELPER LIMS    [Dashboard Principal]         👤 " + usuario.getNombreCompleto());
            System.out.println("+-------------------------------------------------------------------------+");
            System.out.printf(" %-12s %-12s %-12s %-14s %-20s\n", "Código", "Cliente", "Ingreso", "Estado Global", "Progreso Analítico");
            System.out.println(" ------------------------------------------------------------------------- ");
            
            for (Muestra m : tablaMuestras) {
                double progreso = m.calcularProgreso();
                int bloques = (int) (progreso / 10);
                String barra = "[" + "█".repeat(bloques) + "░".repeat(10 - bloques) + "]";
                System.out.printf(" %-12s %-12s %-12s %-14s %s %.1f%%\n", 
                    m.getCodigoMuestra(), m.getCliente().getNombreEmpresa(), m.getFechaIngreso(), m.getEstado(), barra, progreso);
            }
            System.out.println("+-------------------------------------------------------------------------+");
            System.out.println("[1] Registrar Muestra | [2] Banco de Trabajo (Analista) | [3] Panel de Validación (Sup) | [4] Salir");
            System.out.print("Seleccione una opción: ");
            int op = sc.nextInt();
            
            if (op == 1) registrarMuestra(sc);
            else if (op == 2) mostrarBancoTrabajoAnalista(sc, usuario);
            else if (op == 3) mostrarPanelValidacionSupervisor(sc, usuario);
            else break;
        }
    }

    // INGRESO Y REGISTRO DE MUESTRAS 
    private static void registrarMuestra(Scanner sc) {
        System.out.println("\n--- REGISTRO DE NUEVA MUESTRA ---");
        sc.nextLine(); 
        System.out.print("Descripción del material: ");
        String desc = sc.nextLine();
        
        
        Cliente cliente = tablaClientes.get(0); 
        Muestra nueva = new Muestra((long)(tablaMuestras.size()+1), cliente, null);
        
      
        Ensayo ensayoInicial = new Ensayo((long)(tablaMuestras.size()+500), "pH de Suspensión", TipoEnsayo.FISICOQUIMICO, tablaUsuarios.get(0));
        ensayoInicial.agregarResultado(new Resultado(10L, "Acidez", "pH", 4.0, 7.0));
        nueva.asignarEnsayo(ensayoInicial);

        tablaMuestras.add(nueva);
        System.out.println("✅ Muestra registrada exitosamente.");
    }

    // PANEL DE TRABAJO DIARIO DEL ANALISTA
    private static void mostrarBancoTrabajoAnalista(Scanner sc, Usuario analista) {
        System.out.println("\n--- MI BANCO DE TRABAJO DIARIO ---");
        List<Ensayo> misEnsayos = new ArrayList<>();
        
        for (Muestra m : tablaMuestras) {
            for (Ensayo e : m.getEnsayosAsignados()) {
                misEnsayos.add(e);
                System.out.printf("[%d] Técnica: %-20s | Muestra: %-10s | Estado: %s\n", 
                    misEnsayos.size(), e.getNombre(), m.getCodigoMuestra(), e.getEstado());
            }
        }
        System.out.print("Seleccione el número de ensayo para trabajar (o 0 para volver): ");
        int sel = sc.nextInt();
        if (sel > 0 && sel <= misEnsayos.size()) {
            fichaEnsayoPestania1(sc, misEnsayos.get(sel - 1));
        }
    }

    // FICHA DEL ENSAYO - PESTAÑA 1
    private static void fichaEnsayoPestania1(Scanner sc, Ensayo ensayo) {
        while (true) {
            System.out.println("\n=========================================================================");
            System.out.println("  FICHA DE ENSAYO: " + ensayo.getNombre() + " | ESTADO: " + ensayo.getEstado());
            System.out.println("=========================================================================");
            System.out.println("  [ PESTAÑA 1: REPORTE DE RESULTADOS NUMÉRICOS ]");
            System.out.println("  ---------------------------------------------------------------------");
            
            List<Resultado> resList = ensayo.getResultados();
            for (int i = 0; i < resList.size(); i++) {
                Resultado r = resList.get(i);
                System.out.printf("  [%d] Parámetro: %-12s | Rango: %.2f - %.2f | Valor: %s | Cumple: %s\n",
                    (i+1), r.getParametro(), r.getValorMinimo(), r.getValorMaximo(), 
                    (r.getValorObtenido() == null ? "PENDIENTE" : r.getValorObtenido()), 
                    (r.getValorObtenido() == null ? "-" : (r.isCumple() ? "✅ SI" : "❌ NO")));
                if (r.getObservaciones() != null) {
                    System.out.println("      └── 💬 Obs: " + r.getObservaciones().getContenido());
                }
            }
            System.out.println("  ---------------------------------------------------------------------");
            System.out.println("  [1] Cargar/Editar Fila | [2] Ir a Pestaña 2 (Gráficos) | [3] Finalizar y Enviar a Sup | [4] Volver");
            System.out.print("  Acción: ");
            int op = sc.nextInt();
            
            if (op == 1) {
                System.out.print("  Número de fila a editar: ");
                int fila = sc.nextInt();
                if (fila > 0 && fila <= resList.size()) {
                    Resultado rSelected = resList.get(fila - 1);
                    System.out.print("  Valor numérico obtenido: ");
                    rSelected.setValorObtenido(sc.nextDouble());
                    rSelected.calcular(); // Ejecuta cálculo matemático automático
                    
                    System.out.print("  ¿Agregar observación en la fila? (s/n): ");
                    if (sc.next().equalsIgnoreCase("s")) {
                        sc.nextLine();
                        System.out.print("  Escriba la observación: ");
                        rSelected.setObservaciones(new NotaTexto(2L,sc.nextLine()));
                    }
                }
            } else if (op == 2) {
                fichaEnsayoPestania2(sc, ensayo, null);
            } else if (op == 3) {
                ensayo.finalizarEnsayo();
                break;
            } else {
                break;
            }
        }
    }

    // FICHA DEL ENSAYO - PESTAÑA 2
    private static void fichaEnsayoPestania2(Scanner sc, Ensayo ensayo, Resultado resultado) {
        System.out.println("\n  =========================================================================");
        System.out.println("    [ PESTAÑA 2: ADJUNTOS INSTRUMENTALES ]");
        System.out.println("  =========================================================================");
        System.out.println("    Gráficos actualmente adjuntos: " + resultado.getGraficos().size());
        
        for (Imagen img : resultado.getGraficos()) {
            System.out.println("    - Archivo: " + img.getNombre() + " (" + img.getContentType() + ")");
        }
        System.out.println("  -------------------------------------------------------------------------");
        System.out.print("    ¿Desea adjuntar un nuevo gráfico instrumental simulado? (s/n): ");
        if (sc.next().equalsIgnoreCase("s")) {
            System.out.print("    Nombre del archivo (ej: voltamograma_oer.png): ");
            String nombreArch = sc.next();
            
            // Simulación física de carga convirtiendo texto plano a bytes
            byte[] bytesSimulados = "[0.1V:1.5mA,0.2V:3.0mA]".getBytes();
            Imagen nuevaImagen = new Imagen((long)(resultado.getGraficos().size()+1), nombreArch, bytesSimulados);
            
            resultado.agregarGrafico(nuevaImagen); // Composición ejecutada
        }
        System.out.println("    Saliendo de la Pestaña 2... Volviendo a la pantalla operativa.");
    }

    // PANEL DE VALIDACIÓN DEL SUPERVISOR 
    private static void mostrarPanelValidacionSupervisor(Scanner sc, Usuario supervisor) {
        System.out.println("\n--- PANEL DE AUDITORÍA CALIFICADA (Supervisor) ---");
        List<Ensayo> porValidar = new ArrayList<>();
        
        for (Muestra m : tablaMuestras) {
            for (Ensayo e : m.getEnsayosAsignados()) {
                if (e.getEstado() == Estado.POR_VALIDAR) {
                    porValidar.add(e);
                    System.out.printf("[%d] Ensayo: %s | Muestra Origen: %s\n", 
                        porValidar.size(), e.getNombre(), m.getCodigoMuestra());
                }
            }
        }
        
        if (porValidar.isEmpty()) {
            System.out.println("No hay ensayos requiriendo firmas de validación en este momento.");
            return;
        }
        
        System.out.print("Seleccione el ensayo a dictaminar: ");
        int sel = sc.nextInt();
        if (sel > 0 && sel <= porValidar.size()) {
            Ensayo eSel = porValidar.get(sel - 1);
            Validacion v = new Validacion((long)(tablaMuestras.size()+800), eSel, supervisor);
            
            sc.nextLine();
            System.out.print("Escriba su dictamen/comentario institucional: ");
            v.setObservacionesFinales(sc.nextLine());
            
            System.out.print("¿Aprueba la publicación del reporte al cliente? (s/n): ");
            if (sc.next().equalsIgnoreCase("s")) {
                v.aprobarEnsayo("✅ Ensayo firmado y aprobado de forma definitiva.");
            } else {
                v.aprobarEnsayo("❌ Ensayo rechazado. Devuelto al banco del analista.");
              
            };
    }
    }
    // PORTAL EXTERNO (Uso del Cliente)
    private static void mostrarPortalCliente(Scanner sc, Usuario clienteUser) {
        System.out.println("\n+-------------------------------------------------------------------------+");
        System.out.println("  PORTAL EXTERNO DE CLIENTES - LABHELPER LIMS                             ");
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.println("  Bienvenido al registro histórico de trazabilidad de sus celdas y ensayos.\n");
        
        for (Muestra m : tablaMuestras) {
            // Validamos a nivel de negocio si la muestra le pertenece al cliente logueado
            if (m.getCliente().perteneceUsuario(clienteUser)) {
                double progreso = m.calcularProgreso();
                System.out.printf("  • Muestra: %-12s | Estado: %-12s | Progreso: %.1f%%\n", 
                    m.getCodigoMuestra(), m.getEstado(), progreso);
                
                if (progreso == 100.0) {
                    System.out.println("    └── 📄 [Descargar Certificado Analítico de Calidad Disponible]");
                } else {
                    System.out.println("    └── ⏳ [Resultados parciales en etapa de auditoría interna]");
                }
            }
        }
        System.out.println("+-------------------------------------------------------------------------+");
        System.out.print("Presione cualquier número para cerrar sesión: ");
        sc.next();
    }
}