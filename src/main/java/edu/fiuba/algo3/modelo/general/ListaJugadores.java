package edu.fiuba.algo3.modelo.general;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.modelo.jugador.*;

import java.util.ArrayList;

public class ListaJugadores {

    ArrayList<Jugador> listaJugadores;
    int indiceActual;
    final int cantidadMinimaJugadores = 2;
    final int cantidadMaximaJugadores = 6;


    public ListaJugadores(int cantidadJugadores, IRandomizador randomizador, ArrayList<Objetivo> listaObjetivos){
        if(cantidadJugadores < this.cantidadMinimaJugadores || cantidadJugadores > this.cantidadMaximaJugadores)
                throw new CantidadInvalidaDeJugadoresException();
        this.listaJugadores = new ArrayList<>();
        randomizador.mezclarObjetivos(listaObjetivos);
        ArrayList<Objetivo> listaObjetivosUsados = new ArrayList<>();

        for(int i=0; i < cantidadJugadores; i++) {
            Objetivo objetivoDelJugador = listaObjetivos.remove(0);
            listaObjetivosUsados.add(objetivoDelJugador);
            Jugador nuevoJugador = new Jugador(objetivoDelJugador);
            this.listaJugadores.add(nuevoJugador);
        }

        for(int i=0; i < cantidadJugadores; i++){
            listaObjetivosUsados.get(i).establecerJugadores(this.listaJugadores, i);
        }

        mezclar(randomizador);
    }

    public boolean estaAlFinalDeLaLista(){
        return this.siguiente().esNulo();
    }

    public Jugador siguiente(){
        if(indiceActual >= listaJugadores.size()) {
            indiceActual = 0;
            return new JugadorNulo();
        }

        Jugador jugadorActual = listaJugadores.get(indiceActual);
        indiceActual++;
        return jugadorActual;
    }

    public int size(){
        return listaJugadores.size();
    }


    public void reiniciar(){
        this.indiceActual = 0;
    }

    private void mezclar(IRandomizador randomizador){
        int jugadorInicial = randomizador.generar(0,this.listaJugadores.size());
        ArrayList<Jugador> listaJugadoresAuxiliar = new ArrayList<>();
        for(int i=jugadorInicial; i>=0; i--){
            listaJugadoresAuxiliar.add(this.listaJugadores.get(i));
        }
        for(int i=this.listaJugadores.size()-1; i>jugadorInicial; i--){
            listaJugadoresAuxiliar.add(this.listaJugadores.get(i));
        }
        this.listaJugadores = listaJugadoresAuxiliar;
    }

    public boolean hayGanador(Tablero tablero){
        boolean hayGanador = false;
        int iterador = 0;
        while(!hayGanador && iterador < listaJugadores.size()){
            hayGanador = listaJugadores.get(iterador).jugadorGano(tablero);
            iterador++;
        }
        return hayGanador;
    }
}
