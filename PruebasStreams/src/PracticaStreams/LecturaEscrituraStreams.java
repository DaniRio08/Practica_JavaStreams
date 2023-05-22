package PracticaStreams;

import java.io.*;
import java.util.Scanner;

public class LecturaEscrituraStreams {

    private final static String[] campos = {"Título: ", "Año: ", "Director: ", "Duración: ", "Sinopsis: ", "Reparto: ", "Sesión: "};
    private final static String lineSeparator = System.getProperty("line.separator");

    /*Pide al usuario la ruta de un fichero
    Si la ruta del fichero de salida es incorrecta (que esté vacia), pedirá
    constantemente que se introduzca una ruta hasta que esta sea correcta.*/
    public static String pedirRuta(String tipo) throws RutaInvalida {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce la ruta del archivo de " + tipo + ":");
        System.out.println("(Ejemplo archivo.txt)");
        String ruta = leer.nextLine();
        if ("".equals(ruta)) { // si la ruta introducida está vacía        
                throw new RutaInvalida("Ruta de "+tipo+ " sin informar"); //lanza la excepción de Fichero de Salida       
        }
        return ruta;
    }

    
    //Lectura y escritura del fichero de cartelera byte a byte (byte Streams).
    public static void ficheroByte() throws RutaInvalida {
        FileInputStream lectura = null;
        FileOutputStream escritura = null;

        try {
            lectura = new FileInputStream(pedirRuta("origen"));
            boolean eof = false;
            String texto = "";
            //Bucle que irá leyendo el archivo hasta que no haya una nueva línea
            while (!eof) {
                int byte_entrada = lectura.read();
                if (byte_entrada != -1) {
                    char caracter = (char) byte_entrada;
                    texto += caracter;
                } else {
                    eof = true;
                }
            }
            //Se usa el método formatearTexto para escribir el texto leído de forma más bonita
            String textoEscribir = formatearTexto(texto);
            escritura = new FileOutputStream(pedirRuta("destino"));
            //De esta forma se escribe todo el texto formateado
            escritura.write(textoEscribir.getBytes());

        } catch (IOException e) {
            String mensajeError = "Error al leer o escribir el archivo: " + e.getMessage();
            System.out.println(mensajeError);
        } finally {
            try {
                if (lectura != null) {
                    lectura.close();
                }
                if (escritura != null) {
                    escritura.close();
                }
            } catch (IOException e) {
                String mensajeError = "Error al cerrar el fichero: " + e.getMessage();
                System.out.println(mensajeError);
            }
        }
    }

    //Lectura y escritura de fichero de cartelera carácter a carácter (character Streams).
    public static void ficheroCaracter() throws RutaInvalida {
        FileReader lector = null;
        FileWriter escritor = null;
        int carácter;

        try {
            lector = new FileReader(pedirRuta("origen"));            
            String texto = "";
            //Bucle que irá leyendo el archivo hasta que no haya un nuevo carácter
            while ((carácter = lector.read()) != -1) {
                texto += (char) carácter;
            }
            //Se usa el método formatearTexto para escribir el texto leído de forma más bonita
            String textoEscribir = formatearTexto(texto);
            escritor = new FileWriter(pedirRuta("destino"));
            //Bucle para escribir texto formateado carácter a carácter
            for (int i = 0; i < textoEscribir.length(); i++) {
                escritor.write(textoEscribir.charAt(i));
            }

        } catch (IOException e) {
            String mensajeError = "Error al leer o escribir el archivo: " + e.getMessage();
            System.out.println(mensajeError);
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
                if (escritor != null) {
                    escritor.close();
                }
            } catch (IOException e) {
                String mensajeError = "Error al cerrar el fichero: " + e.getMessage();
                System.out.println(mensajeError);
            }
        }
    }

    //Lectura y escritura de fichero línea a línea con buffers (character Streams).
    public static void ficheroBuffer() throws RutaInvalida {
        FileReader fr = null;
        BufferedReader lector = null;
        FileWriter fw = null;
        BufferedWriter escritor = null;

        try {
            fr = new FileReader(pedirRuta("origen"));    
            lector = new BufferedReader(fr);      
            String texto = "";
            //Bucle que irá leyendo el archivo hasta que no haya una nueva línea
            String lineaLectura;
            while ((lineaLectura = lector.readLine()) != null) {
                texto += lineaLectura;
            }
            
            //Se usa el método formatearTexto para escribir el texto leído de forma más bonita
            String textoEscribir = formatearTexto(texto);
            fw = new FileWriter(pedirRuta("destino"));
            escritor = new BufferedWriter(fw);
            //Separamos el texto formateado en líneas
            String[] lineas = textoEscribir.split("\n");
            //Bucle que irá escribiendo el texto formateado línea a línea
            for (int i = 0; i < lineas.length; i++) {
                escritor.write(lineas[i]);
                escritor.newLine();
            }
            //Forma de asegurarse que todos los datos se han escrito correctamente
            escritor.flush();
        } catch (IOException e) {
            String mensajeError = "Error al leer o escribir el archivo: " + e.getMessage();
            System.out.println(mensajeError);
        } finally {
            try {
                if (lector != null) {
                    lector.close();
                }
                if (escritor != null) {
                    escritor.close();
                }
            } catch (IOException e) {
                String mensajeError = "Error al cerrar el fichero: " + e.getMessage();
                System.out.println(mensajeError);
            }
        }
    }

    //Método que recibe un texto como parámetro y lo formatea de la manera que explica el enunciado
    public static String formatearTexto(String texto) {
        String dev = "Cartelera de Cine CIFPBMOLL\n\n";
        String[] películas = texto.split("\\{");
        for (int i = 0; i < películas.length; i++) {
            String[] campos = películas[i].split("#");
                dev += "-----" + campos[0] + "-----\n\n";
                dev += "Year: " + campos[1] + "\n\n";
                dev += "Director: " + campos[2] + "\n\n";
                dev += "Duration: " + campos[3] + "\n\n";
                dev += "Synopsis: " + campos[4] + "\n\n";
                dev += "Cast: " + campos[5] + "\n\n";
                dev += "Session: " + campos[6] + "\n\n";
        }
        return dev;
    }


    //Método de lectura de un fichero de objetos y escritura por consola, leerá un fichero con ObjectInputStream y lo mostrará por consola.
    public static void leerLineaEscribirObj() throws RutaInvalida {
        try (BufferedReader lectorBuffer = new BufferedReader(new FileReader(pedirRuta("entrada")));
                ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("salida")))) {
            boolean eof = false;
            String lineaLeida;
            String[] peliculas;
            String[] texto;
            int i = 0; //indice de campos
            Pelicula p = new Pelicula(); //inicializa una película
            while (!eof) {
                lineaLeida = lectorBuffer.readLine(); //lee una línea
                if (lineaLeida != null) {
                    peliculas = lineaLeida.split("\\{"); //divide la línea por '{' (películas)
                    for (int j = 0; j < peliculas.length; j++) {
                        texto = peliculas[j].split("#"); //divide la línea por '#' (campos)
                        for (int k = 0; k < texto.length; k++) {
                            switch (i) { // añade el texto en el campo que toque (atributos del objeto)
                                case 0:
                                    p.setTitulo(p.getTitulo() + texto[k] + " ");
                                    break;
                                case 1:
                                    p.setAno(p.getAno() + texto[k] + " ");
                                    break;
                                case 2:
                                    p.setDirector(p.getDirector() + texto[k] + " ");
                                    break;
                                case 3:
                                    p.setDuracion(p.getDuracion() + texto[k] + " ");
                                    break;
                                case 4:
                                    p.setSinopsis(p.getSinopsis() + texto[k] + " ");
                                    break;
                                case 5:
                                    p.setReparto(p.getReparto() + texto[k] + " ");
                                    break;
                                case 6:
                                    p.setSesion(p.getSesion() + texto[k] + " ");
                                    break;
                            }
                            //si hay más de un elemento en el array "texto" y no es el último elemento del array (pasar al siguiente campo)
                            if (k < texto.length - 1) {
                                i++;
                            }
                        }
                        //si hay más de un elemento en el array "peliculas" y no es el último elemento del array (pasamos a la siguiente película)
                        if (j < peliculas.length - 1) {
                            objectSalida.writeObject(p);
                            p.mostrarPelicula();
                            p = new Pelicula();
                            i = 0;
                        }
                    }
                } else {
                    eof = true;
                }
            }
            objectSalida.writeObject(p);
            //p.mostrarPelicula();
        } catch (EOFException ex) {
            System.out.println("FIN DE FICHERO");
        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        }
    }

     //Método de lectura de un fichero de objetos y escritura en otro fichero de objetos.
     //Leerá un fichero con ObjectInputStream y escribirá en otro con ObjectOutputStream
    public static void leerObjEscribirObj() throws RutaInvalida {
        try (ObjectInputStream objectEntrada = new ObjectInputStream(new FileInputStream(pedirRuta("entrada")));
                ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("salida")))) {
            boolean eof = false;
            while (!eof) {
                objectSalida.writeObject(new Pelicula((Pelicula) objectEntrada.readObject()));
            }
        } catch (EOFException e) {
            System.out.println("fin de fichero");
        } catch (IOException ex) {
            System.out.println("Error de lectura/escritura");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase");
        }
    }

    //Método de lectura de un fichero de objetos y escritura por consola. Leerá un fichero con ObjectInputStream y lo mostrará por consola.
    public static void leerObjEscribirCons() throws RutaInvalida {
        try (ObjectInputStream objectEntrada = new ObjectInputStream(new FileInputStream(pedirRuta("entrada")))) {
            boolean eof = false;
            Pelicula p = new Pelicula();
            while (!eof) {
                p = (Pelicula) objectEntrada.readObject();
                p.mostrarPelicula();
            }
        } catch (EOFException e) {
            System.out.println("fin de fichero");
        } catch (ClassNotFoundException ex) {
            System.out.println("Error de clase");
        } catch (IOException ex) {
            System.out.println("Error de lectura");
        }
    }

    //Método de lectura por consola y escritura de objetos en fichero.
    //Pedirá al usuario los atributos del objeto Pelicula y lo escribirá en un fichero de objetos con ObjectOutputStream.
    public static void leerConsEscribirObj() throws RutaInvalida {
        try (ObjectOutputStream objectSalida = new ObjectOutputStream(new FileOutputStream(pedirRuta("salida")))) {
            Pelicula p = new Pelicula(); //crea una nueva película  
            p.pedirPelicula();  //llenamos sus atributos pidiendolos por pantalla
            objectSalida.writeObject(p); //lo guardamos en un fichero
        } catch (IOException ex) {
            System.out.println("Error de escritura");
        }
    }
    
}
