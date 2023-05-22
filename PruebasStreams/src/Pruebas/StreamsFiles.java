package Pruebas;

import java.io.*;
import java.util.Arrays;
import java.util.Scanner;

public class StreamsFiles {
    public static void main(String [] arg) {

        Scanner sc = new Scanner(System.in);

        System.out.println("Ejemplo de como leer un fichero usando CharStreams:");
        System.out.println("");
        LeerCharStreams("C:\\Users\\profe\\1_DAW\\Programacion\\JAVA\\PruebasStreams\\src\\PracticaStreams\\CarteleraVieja.txt");
        System.out.println("");
        System.out.println("----------------------------------------------------------------");
        System.out.println("");
        System.out.println("Ejemplo de como escribir en un fichero usando CharStreams:");
        System.out.println("");
        System.out.println("Escribe lo que quieras en el fichero:");
        String texto = sc.nextLine();
        EscribirCharStreams("C:\\Users\\profe\\1_DAW\\Programacion\\JAVA\\PruebasStreams\\src\\Pruebas\\Adios.txt", texto);
        System.out.println("");
        System.out.println("----------------------------------------------------------------");
        System.out.println("");
        System.out.println("Ejemplo de como leer un fichero usando Buffers:");
        System.out.println("");
        lecturaBufferReader("C:\\Users\\profe\\1_DAW\\Programacion\\JAVA\\PruebasStreams\\src\\Pruebas\\Hola.txt");
        System.out.println("");
        System.out.println("----------------------------------------------------------------");
        System.out.println("");
        System.out.println("");
        System.out.println("Ejemplo de como escribir en un fichero usando Buffers:");
        System.out.println("");
        /*System.out.println("Escribe lo que quieras en el fichero:");
        String mensaje = sc.nextLine();*/
        escrituraBufferWriter("C:\\Users\\profe\\1_DAW\\Programacion\\JAVA\\PruebasStreams\\src\\Pruebas\\Mamaou.txt");
        System.out.println("");
        System.out.println("----------------------------------------------------------------");
        System.out.println("");

        sc.close();
    }

    public static void LeerCharStreams(String origen) {
        File archivo = null;
        FileReader lector = null;
        int caracter;

        try {
            archivo = new File(origen);
            lector = new FileReader(archivo);
            while ((caracter = lector.read()) != -1) {
                System.out.print((char) caracter);
            }
            System.out.println("");
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
                System.out.println("Fichero leído...");
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    public static void EscribirCharStreams(String destino, String mensaje) {
        FileWriter escritor = null;

        try {
            escritor = new FileWriter(destino);
            for (int i = 0; i < mensaje.length(); i++) {
                escritor.write(mensaje.charAt(i));
            }
        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            try {
                if (escritor != null) {
                    escritor.close();
                }
                System.out.println("Fichero escrito...");
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    public static void lecturaBufferReader(String origen) {
        File archivo = null;
        FileReader fr = null;
        BufferedReader lector = null;

        try {
            archivo = new File(origen);
            fr = new FileReader(archivo);
            lector = new BufferedReader(fr);

            //variables
            boolean eof = false;
            String linea = "";  
            String[] brokenText = null;
            while (!eof) {
                //Lee una línea entera
                linea = lector.readLine();
                //Imprime la línea por pantalla
                if (linea != null) {
                    System.out.println(linea);
                    //Una vez leída la podemos partir con el método split
                    brokenText = linea.split(" ");
                    System.out.println(Arrays.toString(brokenText));
                } else {
                    eof = true;
                }
            }
            /*String linea;
            while ((linea=lector.readLine())!=null) {
                System.out.println(linea);
            }*/
        } catch (Exception e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
                System.out.println("Fichero leído...");
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    public static void escrituraBufferWriter(String destino) {
        File archivo = null;
        FileWriter fw = null;
        BufferedWriter escritor = null;

        try {
            String línea1 = "Soy la línea 1#";
            String línea2 = "Yo soy la línea 2#";
            String línea3 = "Y finalmente la línea 3#";
            archivo = new File(destino);
            fw = new FileWriter(archivo);
            escritor = new BufferedWriter(fw);

            //Escribe el archivo de salida a través del bufferWriter
            escritor.write(línea1, 0, línea1.length());
            escritor.newLine();
            escritor.write(línea2, 0, línea2.length());
            escritor.newLine();
            escritor.write(línea3, 0, línea3.length());
            escritor.newLine();

        } catch (Exception e) {
            System.out.println("Error al escribir en el archivo: " + e.getMessage());
        } finally {
            try {
                if (escritor != null) {
                    escritor.close();
                }
                System.out.println("Fichero escrito...");
            } catch (IOException e) {
                System.out.println("Error al cerrar el fichero: " + e.getMessage());
            }
        }
    }

    public static void ficheroErrores(String mensaje) {
    }
}