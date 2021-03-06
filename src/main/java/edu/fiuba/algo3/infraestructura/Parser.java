package edu.fiuba.algo3.infraestructura;

import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.general.Signo;
import edu.fiuba.algo3.modelo.general.SignoComodin;
import edu.fiuba.algo3.modelo.general.Tarjeta;
import edu.fiuba.algo3.modelo.jugador.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.lang.*;


public class Parser {

    private String rutaPaises;
    private String rutaObjetivos;
    private String rutaTarjetas;
    private HashMap paises;
    private HashMap continentes;
    private HashMap<Pais, int[]> paisesParaVista;
    private ArrayList<Tarjeta> tarjetas;
    private ArrayList<Objetivo> objetivos;


    public Parser(String rutaArchivoPaises, String rutaArchivoObjetivos, String rutaArchivoTarjetas) {
        this.rutaObjetivos = rutaArchivoObjetivos;
        this.rutaPaises = rutaArchivoPaises;
        this.rutaTarjetas = rutaArchivoTarjetas;
        this.leerArchivos();
    }

    private void leerArchivos() {

        try {

            ArrayList<HashMap> listaParseados;
            listaParseados = this.parsearPaisesParaTablero(this.rutaPaises);
            this.paises = listaParseados.get(0);
            this.continentes = listaParseados.get(1);

            HashMap<Pais, int[]> hashPaisesParaVista;
            hashPaisesParaVista = this.parsearPaisesParaVista(this.rutaPaises, this.paises);
            this.paisesParaVista = hashPaisesParaVista;


            ArrayList<Tarjeta> tarjetasLeidas;
            tarjetasLeidas = this.parsearTarjetas(this.rutaTarjetas, this.paises);
            this.tarjetas = tarjetasLeidas;

            ArrayList<Objetivo> listaObjetivos;
            listaObjetivos = this.parsearObjetivos(this.rutaObjetivos);
            this.objetivos = listaObjetivos;

        } catch (IOException e) {

            throw new ArchivoInvalidoException();
        }

    }

    public HashMap<String, Pais> getPaisesParaTablero() {

        return this.paises;
    }

    public HashMap<String, Continente> getContinentes() {

        return this.continentes;
    }

    public HashMap<Pais, int[]> getPaisesParaVista() {

        return this.paisesParaVista;
    }

    public ArrayList<Objetivo> getObjetivos() {

        return this.objetivos;
    }

    public ArrayList<Tarjeta> getTarjetas() {

        return this.tarjetas;
    }

    public static ArrayList<ArrayList> leerArchivo(String rutaArchivo) throws IOException {
        ArrayList<ArrayList> lineasArchivo = new ArrayList<ArrayList>();

        try {
			BufferedReader csvReader = new BufferedReader(new FileReader(rutaArchivo));
			String row = "";

			while ((row = csvReader.readLine()) != null) {
    		    ArrayList<String> linea = new ArrayList<>();
    			String[] data = row.split(",");

                Collections.addAll(linea, data);
                lineasArchivo.add(linea);
    		}
			csvReader.close();
		}
		catch(Exception e) {
            System.out.println(e);
            throw e;
		}
        return lineasArchivo;
	}

	private HashMap<Pais, int[]> parsearPaisesParaVista(String rutaArchivo, HashMap<String, Pais> hashPaises) throws IOException {
        ArrayList<ArrayList> datosPaisesCompleto = Parser.leerArchivo(rutaArchivo);

        HashMap<Pais, int[]> hashPaisesConCoord = new HashMap<Pais, int[]>();

        for(ArrayList<String> linea : datosPaisesCompleto) {

            Pais pais = hashPaises.get(linea.get(0));

            int[] coordenadas = new int[2];
            coordenadas[0] = Integer.parseInt(linea.get(3));
            coordenadas[1] = Integer.parseInt(linea.get(4));

            hashPaisesConCoord.put(pais, coordenadas);
        }

        return hashPaisesConCoord;
    }

    private ArrayList<HashMap> parsearPaisesParaTablero(String rutaArchivo) throws IOException {
        /*
        *
        * Devuelve un ArrayList donde en la posicion 0 tiene un Hash con los paises
        * y en la posicion 1 una Hash de los continentes
        * * */
        ArrayList<ArrayList> datosPaisesCompleto;
        datosPaisesCompleto = Parser.leerArchivo(rutaArchivo);

        HashMap<String, Pais> paises = new HashMap<String, Pais>();

        HashMap<String, ArrayList<Pais>> hashContinentesConPaises = new HashMap<String, ArrayList<Pais>>();
        hashContinentesConPaises.put("America Del Sur", new ArrayList());
        hashContinentesConPaises.put("Europa", new ArrayList());
        hashContinentesConPaises.put("America Del Norte", new ArrayList());
        hashContinentesConPaises.put("Asia", new ArrayList());
        hashContinentesConPaises.put("Oceania", new ArrayList());
        hashContinentesConPaises.put("Africa", new ArrayList());


        for(ArrayList<String> linea : datosPaisesCompleto) {

            Pais pais = new Pais(linea.get(0));
            paises.putIfAbsent(linea.get(0), pais);

            String nombreContinente = linea.get(1);
            ArrayList<Pais> continente = hashContinentesConPaises.get(nombreContinente);
            continente.add(pais);
        }


        for(ArrayList<String> linea : datosPaisesCompleto) {

            String stringLimitrofesConComillas = linea.get(2);
            String stringLimitrofes = stringLimitrofesConComillas.substring(1, stringLimitrofesConComillas.length() - 1);

            //Genera la lista limitrofes que contiene el nombre de los paises limitrofes
            String[] data = stringLimitrofes.split(";");
            ArrayList<String> limitrofes = new ArrayList<String>();
            Collections.addAll(limitrofes, data);

            String paisKey = linea.get(0);
            for(String nombreLimitrofe : limitrofes) {
                Pais paisAAgregarLimitrofe = paises.get(paisKey);
                Pais limitrofe = paises.get(nombreLimitrofe);

                paisAAgregarLimitrofe.agregarLimitrofe(limitrofe);
            }
        }

        HashMap<String, Continente> hashContinentes = new HashMap<String, Continente>();

        HashMap<String, Integer> ejercitosPorContinentes = new HashMap<String, Integer>();
        ejercitosPorContinentes.put("America Del Sur", 3);
        ejercitosPorContinentes.put("America Del Norte", 5);
        ejercitosPorContinentes.put("Europa", 5);
        ejercitosPorContinentes.put("Asia", 7);
        ejercitosPorContinentes.put("Africa", 3);
        ejercitosPorContinentes.put("Oceania", 2);

        hashContinentesConPaises.forEach((key,value) -> {
                    String nombreContinente = key;
                    ArrayList<Pais> listaPaisesEnContinente = value;

                    hashContinentes.put(nombreContinente, new Continente(listaPaisesEnContinente, nombreContinente, ejercitosPorContinentes.get(nombreContinente)));
                });


        //ArrayList<Pais> listaPaises = new ArrayList<Pais>(paises.values());

        ArrayList<HashMap> devolucion = new ArrayList<HashMap>();
        devolucion.add(paises);
        devolucion.add(hashContinentes);

        return devolucion;
    }

    public static Signo obtenerSigno(String stringSigno) {

        switch (stringSigno) {
            case "Barco":
                return new Signo(0);

            case "Globo":
                return new Signo(1);

            case "Canion":
                return new Signo(2);

            case "Comodin":
                return new SignoComodin();
        }

        return new Signo(-10);
    }


    private ArrayList<Tarjeta> parsearTarjetas(String rutaArchivoTarjetas, HashMap<String, Pais> paises) throws IOException {
        ArrayList<ArrayList> datosTarjetrasCompleto = Parser.leerArchivo(rutaArchivoTarjetas);

        ArrayList<Tarjeta> arrayTarjetas = new ArrayList<Tarjeta>();

        for (ArrayList<String> linea : datosTarjetrasCompleto) {
            Pais paisActual = paises.get(linea.get(0));
            Signo signoActual = Parser.obtenerSigno(linea.get(1));

            arrayTarjetas.add(new Tarjeta(paisActual, signoActual));
        }

        return arrayTarjetas;
    }

    private static Objetivo seleccionarSegunNombreObjetivo(String nombreObjetivo, ArrayList<String> linea) {

        Objetivo objetivo = null;

        switch(nombreObjetivo) {
            case "cantidad_por_continente":
                HashMap<String, Integer> hashCantidades = new HashMap<String, Integer>();
                String nombreContinenteActual = "";
                for(int i=1; i < linea.size(); i++) {
                    if(i % 2 != 0) { //Si i es impar, esta el nombre del continente
                        nombreContinenteActual = linea.get(i);
                    } else { //Si es impar, esta la cantidad a conquistar en el continente
                        hashCantidades.put(nombreContinenteActual, Integer.parseInt(linea.get(i)));
                    }
                }

                objetivo = new ObjetivoCantidadPorContinente(hashCantidades);
                break;

            case "conquistar_2_continentes":

                ArrayList<String> listaContinentes = new ArrayList<String>();

                for(int i=1; i < linea.size(); i++) {

                    listaContinentes.add(linea.get(i));
                }
                objetivo = new ObjetivoConquistar2Continentes(listaContinentes);
                break;

            case "conquistar_continentes_y_cantidad_paises":

                String nombreContinente = linea.get(1);
                HashMap<String, Integer> hashContinentesConCantidad = new HashMap<String, Integer>();
                String nombreActual = null;
                for(int i=2; i < linea.size(); i++) {
                    if(i % 2 == 0) { //si el split de la linea es par, aparece el nombre del continente
                        nombreActual = linea.get(i);
                    } else {
                        hashContinentesConCantidad.put(nombreActual, Integer.parseInt(linea.get(i)));
                    }
                }

                objetivo = new ObjetivoConquistarContinenteYCantidadPaises(nombreContinente, hashContinentesConCantidad);
                break;

            case "destruir_jugador":
                objetivo = new ObjetivoDestruir();
                break;
        }
        return objetivo;
    }
    /*
    *cantidad por continente --> tipo,continente1,num1,continente2,num2,...,continenteN,numN

    conquistar 2 continentes --> tipo,continente1,continente2

    conquistar continentes y cantidad paises -	-> tipo,continente,continente1,cant1,continente2,cant2,...,continenteN,cantN

    destruir jugador --> tipo

    * */
    private ArrayList<Objetivo>  parsearObjetivos(String rutaArchivoObjetivos) throws IOException {

        ArrayList<ArrayList> datosObjetivos = Parser.leerArchivo(rutaArchivoObjetivos);

        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();

        for(ArrayList<String> linea : datosObjetivos) {

            String nombreTipoObjetivo = linea.get(0);
            listaObjetivos.add(seleccionarSegunNombreObjetivo(nombreTipoObjetivo, linea));
        }

        return listaObjetivos;
    }


}