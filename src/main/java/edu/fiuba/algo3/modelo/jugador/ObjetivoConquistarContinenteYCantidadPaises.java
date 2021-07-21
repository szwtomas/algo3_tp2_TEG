package edu.fiuba.algo3.modelo.jugador;

import edu.fiuba.algo3.modelo.general.Tablero;

import java.util.ArrayList;
import java.util.Map;

public class ObjetivoConquistarContinenteYCantidadPaises implements Objetivo{
    private Jugador jugador;
    private ObjetivoConquistar2Continentes objetivo1;
    private ObjetivoCantidadPorContinente objetivo2;


    public ObjetivoConquistarContinenteYCantidadPaises(Jugador unJugador, String continente,Map<String, Integer> listaObjetivos2){
        this.jugador = unJugador;
        ArrayList<String> listaObjetivo1 = new ArrayList<>();
        listaObjetivo1.add(continente);
        listaObjetivo1.add(continente);
        this.objetivo1 = new ObjetivoConquistar2Continentes(unJugador, listaObjetivo1);
        this.objetivo2 = new ObjetivoCantidadPorContinente(unJugador, listaObjetivos2);
    }
    public boolean haGanado(Tablero tablero, ArrayList<Jugador> listaJugadores){
        return (this.objetivo1.haGanado(tablero, listaJugadores) && this.objetivo2.haGanado(tablero, listaJugadores));
    }
}