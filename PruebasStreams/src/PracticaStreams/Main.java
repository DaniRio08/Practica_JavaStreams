package PracticaStreams;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        //Scanners
        Scanner scint = new Scanner(System.in);

        int opcion = 0;

        do {
            System.out.println("");
            System.out.println("=======================================================");
            System.out.println("|                                                     |");
            System.out.println("|  1. Leer y escribir un fichero byte a byte          |");
            System.out.println("|                                                     |");
            System.out.println("|  2. Leer y escribir un fichero carácter a carácter  |");
            System.out.println("|                                                     |");
            System.out.println("|  3. Leer y escribir un fichero línea a línea        |");
            System.out.println("|                                                     |");
            System.out.println("|  4. Tratamiento de objetos                          |");
            System.out.println("|                                                     |");
            System.out.println("|  5. Salir                                           |");
            System.out.println("|                                                     |");
            System.out.println("=======================================================");
            System.out.println("");
            
            System.out.print("¿Qué opción eliges?: ");
            opcion = scint.nextInt();

            switch (opcion) {
                case (1):
                    //Llama al método que lee el fichero byte a byte
                    try {
                        LecturaEscrituraStreams.ficheroByte();
                    } catch (RutaInvalida e) {
                        e.printStackTrace();
                    }
                    break;
                case (2):
                    //Llama al método que lee el fichero carácter a carácter
                    try {
                        LecturaEscrituraStreams.ficheroCaracter();
                    } catch (RutaInvalida e) {
                        e.printStackTrace();
                    }
                    break;
                case (3): 
                    //Llama al método que lee el fichero línea a línea
                    try {
                        LecturaEscrituraStreams.ficheroBuffer();
                    } catch (RutaInvalida e) {
                        e.printStackTrace();
                    }
                    break;
                case (4):
                    try {
                        tratamientoDeObjetos();
                    } catch (RutaInvalida e) {
                        e.printStackTrace();
                    }
                case (5): 
                    System.out.println("Hasta la próxima");
                    break;
                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 5);

        scint.close();
    }

    public static void tratamientoDeObjetos() throws RutaInvalida {

        boolean salir = false;
        while (!salir) {
            switch (pedirOpcionObjetos()) {
                case 1:
                    LecturaEscrituraStreams.leerLineaEscribirObj();
                    break;
                case 2:
                    LecturaEscrituraStreams.leerObjEscribirObj();
                    break;
                case 3:
                    LecturaEscrituraStreams.leerObjEscribirCons();
                    break;
                case 4:
                    LecturaEscrituraStreams.leerConsEscribirObj();
                    break;
                case 5:
                    salir = true;
                    break;
                default:
                    System.out.println("Opción no válida");
            }
        }
    }

    //Muestra un menú por consola y se queda a la espera de que el usuario introduzca una opción.
    public static int pedirOpcionObjetos() {
        Scanner leer = new Scanner(System.in);
        System.out.println("---------------------------");
        System.out.println("1) Lectura línea a línea y escritura con objetos.");
        System.out.println("2) Lectura de objetos y escritura de objetos.");
        System.out.println("3) Lectura de objetos y escritura por consola.");
        System.out.println("4) Lectura por consola y escritura de objetos. (añadirá objetos al final del fichero existente)");
        System.out.println("5) Volver al menú principal.");
        System.out.println("---------------------------");
        System.out.println("Introduce una opción: ");
        return leer.nextInt();
    }
}
