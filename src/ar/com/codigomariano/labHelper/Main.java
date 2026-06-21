package ar.com.codigomariano.labHelper;

import java.util.Random;
import java.util.Scanner;



public class Main {
	
	private static String codigo;
	private static String tipoDeMuestra;
	private static String cliente;
	private static String observaciones;
	private static String estadoDeMuestra;
	private static String observacionesFinales;
	
	private final static String[] estado=new String[] {"RECIBIDO","EN_ANALISIS","REVISION_PENDIENTE","APROBADO","REPETIR","REPORTADO"};
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		menuPrincipal(scanner);
		scanner.close();

	}
	
	//Menu Principal

	private static int menu(Scanner sc) {
		imprimir("------------------------------------------------------");
		imprimir("\u001B[31m"+"\u001B[47m"+"****Bienvenido al sistema LabHelper****"+"\u001B[0m");
		imprimir("------------------------------------------------------");
		imprimir("1.Registrar muestra");
		imprimir("2.Editar estado de muestra");
		imprimir("3.Ver muestra ingresada");
		imprimir("4.Cargar resultado");
		imprimir("0.Salir del sistema");
		imprimir("------------------------------------------------------");
		System.out.print("Seleccione una opcion: ");
		int opcion=sc.nextInt();
		sc.nextLine();
		return opcion;
	}
	
	public static void imprimir(String mensaje) {
		System.out.println(mensaje);
	}
	
	private static void menuPrincipal(Scanner scanner) {
		boolean salir=false;
		while(!salir) {
			int opcion=menu(scanner);
			if(opcion==1) {
				registrarMuestra(scanner);
				estadoDeMuestra=Main.estado[0];
			}else if(opcion==2) {
				estadoDeMuestra=seleccionarEstado(scanner);
			}else if(opcion==3) {
				verMuestra();
			}else if(opcion==4) {
				cargarResultado(scanner);
			}else if(opcion==0) {
				System.out.println("Saliendo del programa");
				salir=true;					
			}else {
				System.out.println("Opción invalida.Intente nuevamente");
			}
		
		}
	}

		private static String datosIngresados(String texto,Scanner scanner) {
			String dato;
			System.out.print(texto);
			dato=scanner.nextLine();
			return dato;
		}
		
		
		private static String seleccionarEstado(Scanner sc) {
		    imprimir("------------------------------------------------------");
		    imprimir("¿En que estado se encuentra la muestra?:");
		    for (int i = 0; i < estado.length; i++) {
		        imprimir((i + 1) + "." + estado[i]);
		    }
		    imprimir("------------------------------------------------------");
		    System.out.print("Seleccione una opcion: ");
		    int seleccionada = sc.nextInt();
		    sc.nextLine();

		    if (seleccionada >= 1 && seleccionada <= estado.length) {
		        return estado[seleccionada - 1];
		    } else {
		        System.out.println("Opción inválida. No se cambió el estado.");
		        return estadoDeMuestra; 
		    }
		}
		
		
		
		private static void registrarMuestra(Scanner sc) {
			Random r = new Random();
			codigo="AQ"+r.nextInt(0000, 1000);
			tipoDeMuestra=datosIngresados("Ingrese tipo de muestra: ", sc);
			cliente=datosIngresados("Nombre del cliente: ",sc);
			observaciones=datosIngresados("Observaciones a considerar: ",sc);
		}
		

		private static void verMuestra() {
			imprimir("------------------------------------------------------");
			imprimir("Código: "+codigo);
			imprimir("Tipo: "+tipoDeMuestra);
			imprimir("Cliente: "+cliente);
			imprimir("Estado: "+estadoDeMuestra);
			imprimir("------------------------------------------------------");
			
		}
		private static void cargarResultado(Scanner sc) {
			verMuestra();
			String ensayo=datosIngresados("Ingrese el tipo de ensayo: ", sc);
			String resultado=datosIngresados("Ingrese los resultados obtenidos: ", sc);
			estadoDeMuestra=Main.estado[2];
			observacionesFinales=datosIngresados("Observaciones: ",sc);
			imprimir("Resultado agregado correctamente");
			imprimir("Ensayo :"+ ensayo);
			imprimir("Resultado: "+resultado);
			imprimir("Observaciones: "+ observacionesFinales);
			imprimir("Estado actualizado: "+ estadoDeMuestra);
		}


}
