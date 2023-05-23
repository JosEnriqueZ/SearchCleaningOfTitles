package pe.edu.utp;

import java.util.Scanner;
import pe.edu.utp.Busqueda.TitleSearch;
import pe.edu.utp.service.Core;
/**
 *
 * @author EnriqueZ Gutierrez Arias
 */
public class Main {

    public static void main(String[] args) {
    
    
    
        Scanner scanner = new Scanner(System.in);

        System.out.println("¡Bienvenido a la interfaz de consola!");
        System.out.println("---------------------------------------");

        while (true) {
            System.out.println("Selecciona una opción:");
            System.out.println("1. Buscar titulos en archico .bib");
            System.out.println("2. Encontrar titulos duplicados desde un txt");
            System.out.println("3. Salir");

            System.out.print("Opción: ");
            int opcion = scanner.nextInt();
            System.out.println("---------------------------------------");
            switch (opcion) {
                case 1:
                    System.out.println("Buscar titulos");
                    TitleSearch tc = new TitleSearch();
                    tc.RUN();
                    break;
                case 2:
                    System.out.println("Encontrar titulos duplicados");
                    Core run = new Core();
                    break;
                case 3:
                    System.out.println("¡Adiós!");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Opción inválida. Inténtalo de nuevo.");
                    break;
            }

            System.out.println("---------------------------------------");
        }
    }
}
