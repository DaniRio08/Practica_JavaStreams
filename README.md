# Práctica Java Streams

### Objetivos
El objetivo de esta práctica es aprender sobre los Streams en Java, entender su funcionalidad y utilidad, y utilizar los diferentes tipos de Streams disponibles, como Streams de bytes, caracteres, buffer y objetos. En especial la idea es prácticar el uso de Streams para leer y escribir ficheros.
Además, también se ha puesto en práctica lo aprendido en relación al uso de excepciones en Java.

### ¿Qué son y para que se usan los Streams de Java?
Los Streams en Java proporcionan una forma eficiente y flexible de procesar secuencias de datos. Son especialmente útiles cuando se trabaja con grandes cantidades de datos o cuando se requiere manipular y transformar datos de manera fácil y rápida.  
Básicamente, los Streams una secuencia de elementos que se pueden procesar de forma secuencial o paralela. Los Streams permiten realizar operaciones en los datos de manera declarativa, lo que significa que podemos expresar qué operaciones queremos realizar en lugar de cómo realizarlas. 

### Tipos de Streams
En este trabajo se utilizarán los siguientes tipos de Streams:

1. **Streams de bytes**: Estos Streams se utilizan para procesar datos en forma de bytes. Son útiles cuando se trabaja con archivos binarios o se realiza la lectura y escritura de datos a nivel de bytes.
2. **Streams de caracteres**: Los Streams de caracteres se utilizan para procesar datos en forma de caracteres. Son útiles para trabajar con texto y realizar operaciones como filtrado, transformación y conteo de caracteres.
3. **Streams de buffer**: Estos Streams se utilizan para mejorar el rendimiento al procesar grandes cantidades de datos. Al utilizar un buffer intermedio, se reducen las operaciones de lectura y escritura directa, lo que resulta en un procesamiento más eficiente.
4. **Streams de objetos**: Los Streams de objetos permiten trabajar con objetos de cualquier tipo. Son útiles cuando se necesita realizar operaciones complejas en colecciones de objetos, como filtrado, mapeo y reducción.

## Métodos importantes

#### ficheroByte() --> lectura y escritura usando byte Streams
~~~
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
~~~
- Primero, se crea un objeto FileInputStream para leer el archivo de origen especificado por el usuario.
- Luego, se lee el archivo byte a byte hasta que no haya más bytes para leer. Cada byte leído se convierte a un carácter y se agrega al texto.
- Después, se utiliza el método formatearTexto para dar un formato más legible al texto leído.
- Se crea un objeto FileOutputStream para escribir en el archivo de destino especificado por el usuario.
- Finalmente, se escribe el texto formateado en el archivo de destino utilizando el método write y convirtiendo el texto en un arreglo de bytes.

___
#### ficheroCaracter() --> lectura y escritura usando caracter Streams
~~~
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
~~~
- Se crea un objeto FileReader para leer el archivo de origen especificado por el usuario.
- Luego, se lee el archivo carácter a carácter hasta que no haya más caracteres para leer. Cada carácter leído se agrega al texto.
- Se utiliza el método formatearTexto para dar formato al texto leído.
- Se crea un objeto FileWriter para escribir en el archivo de destino especificado por el usuario.
- Se recorre el texto formateado y se escribe carácter a carácter en el archivo de destino utilizando el método write.

___
#### ficheroBuffer() --> lectura y escritura línea por línea usando buffers
~~~
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
~~~
- Se crea un objeto FileReader para leer el archivo de origen especificado por el usuario.
- Luego, se crea un objeto BufferedReader para leer el archivo línea a línea.
- Se lee cada línea del archivo y se agrega al texto.
- Se utiliza el método formatearTexto para dar formato al texto leído.
- Se crea un objeto FileWriter para escribir en el archivo de destino especificado por el usuario.
- Se crea un objeto BufferedWriter para escribir en el archivo con un buffer.
- El texto formateado se divide en líneas y se escribe línea a línea en el archivo utilizando el método write y newLine.
- Finalmente, se llama al método flush para asegurarse de que todos los datos se han escrito correctamente.

___
#### formatearTexto() 
Este método recibe un texto y haciendo uso de split y bucles lo formatea de la manera que pide el enunciado. Cabe tener en cuenta que en el ejercicio se explicaba como iba a estar escrito el texto original, estando las películas separadas por "{" y los campos de cada película por "#".
~~~
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
~~~
