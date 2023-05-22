package PracticaStreams;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Arrays;


//Excepción que se usará cuando no se pueda acceder al fichero de entrada
public class RutaInvalida extends Exception {

    //atributo salto de línea
    private final String lineSeparator = System.getProperty("line.separator");

    //Constructor vacío
    public RutaInvalida() {
    }

    /**
     * Constructor con mensaje
     *
     * @param string Un mensaje que se indica manualmente al llamar la excepción
     */
    public RutaInvalida(String mensaje) {
        super(mensaje);
    }

    //Añade en un log de errores información sobre la excepción producida
    public void impError() {
        try (FileWriter ferror = new FileWriter("C:\\Users\\profe\\1_DAW\\Programacion\\JAVA\\PruebasStreams\\src\\PracticaStreams\\errores.txt", true)) {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            ferror.write(super.getMessage());
            ferror.write(lineSeparator);
            ferror.write(timestamp.toString());
            ferror.write(lineSeparator);
            ferror.write(Arrays.toString(this.getStackTrace()));
            ferror.write(lineSeparator + lineSeparator);
        } catch (IOException ex) {
            System.out.println("Error abriendo error.txt");
        }
    }

}