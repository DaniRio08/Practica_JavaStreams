package PracticaStreams;

import java.io.Serializable;
import java.util.Scanner;

//Esta clase define como son los objetos "película" que serán leidos y escritos en ficheros
public class Pelicula implements Serializable {

    // Atributos
    private String titulo = "";
    private String ano = ""; //año
    private String director = "";
    private String duracion = "";
    private String sinopsis = "";
    private String reparto = "";
    private String sesion = "";

    //Constructores

    //Constructor vacío
    public Pelicula() {
    }

    //Constructor con parámetros
    public Pelicula(String titulo, String ano, String director, String duracion, String sinopsis, String reparto, String sesion) {
        this.titulo = titulo;
        this.ano = ano;
        this.director = director;
        this.duracion = duracion;
        this.sinopsis = sinopsis;
        this.reparto = reparto;
        this.sesion = sesion;
    }

    //Constructor copia
    public Pelicula(Pelicula p) {
        this.titulo = p.titulo;
        this.ano = p.ano;
        this.director = p.director;
        this.duracion = p.duracion;
        this.sinopsis = p.sinopsis;
        this.reparto = p.reparto;
        this.sesion = p.sesion;
    }
    
    //Getters y Setters
    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAno() {
        return ano;
    }
    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getDirector() {
        return director;
    }
    public void setDirector(String director) {
        this.director = director;
    }

    public String getDuracion() {
        return duracion;
    }
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getSinopsis() {
        return sinopsis;
    }
    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public String getReparto() {
        return reparto;
    }
    public void setReparto(String reparto) {
        this.reparto = reparto;
    }

    public String getSesion() {
        return sesion;
    }
    public void setSesion(String sesion) {
        this.sesion = sesion;
    }

    //Muestra por consola los atributos de una instancia de "Pelicula"
    public void mostrarPelicula() {
        System.out.println("Título: " + this.getTitulo());
        System.out.println("Año: " + this.getAno());
        System.out.println("Duración: " + this.getDuracion());
        System.out.println("Sinopsis: " + this.getSinopsis());
        System.out.println("Reparto: " + this.getReparto());
        System.out.println("Sesión: " + this.getSesion());
    }

    //Pide por consola los atributos para una instancia de "Pelicula"
    public void pedirPelicula() {
        Scanner leer = new Scanner(System.in);
        System.out.println("Introduce el título: ");
        this.setTitulo(leer.nextLine());
        System.out.println("Introduce el año: ");
        this.setAno(leer.nextLine());
        System.out.println("Introduce la duración: ");
        this.setDuracion(leer.nextLine());
        System.out.println("Introduce la sinopsis: ");
        this.setSinopsis(leer.nextLine());
        System.out.println("Introduce el reparto: ");
        this.setReparto(leer.nextLine());
        System.out.println("Introduce la sesion: ");
        this.setSesion(leer.nextLine());
    }
}