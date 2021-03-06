package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.jugador.Objetivo;
import edu.fiuba.algo3.modelo.jugador.ObjetivoDestruir;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import edu.fiuba.algo3.modelo.general.*;

import java.util.ArrayList;
import java.util.HashMap;


public class JugadaColocarTest {

    @Test
    public void test01paisPasaDe3A5EjercitosTrasJugadaColocarCon2Ejercitos(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        int unaCantidad = 2;
        Tablero tablero = new Tablero(new HashMap<String, Continente>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        Jugada jugada = new JugadaColocar(unPais, unaCantidad);
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        jugada.ejecutar(tablero, new Ronda(tablero, new ListaJugadores(2, new Randomizador(), listaObjetivos)));

        assertEquals(5, unPais.getCantidadEjercitos());
    }

    @Test
    public void test02paisPasaDe5A8EjercitosTrasJugadaColocarCon3Ejercitos(){
        Pais unPais = new Pais("Chile");
        unPais.colocarEjercitos(4);
        int unaCantidad = 3;
        Tablero tablero = new Tablero(new HashMap<String, Continente>(), new ConstructorDeConjuntoDados(new Randomizador()), new Mazo(new ArrayList<>(), new Randomizador()));
        Jugada jugada = new JugadaColocar(unPais, unaCantidad);
        ArrayList<Objetivo> listaObjetivos = new ArrayList<Objetivo>();
        listaObjetivos.add(new ObjetivoDestruir());
        listaObjetivos.add(new ObjetivoDestruir());
        jugada.ejecutar(tablero, new Ronda(tablero, new ListaJugadores(2, new Randomizador(), listaObjetivos)));

        assertEquals(8, unPais.getCantidadEjercitos());
    }
}
