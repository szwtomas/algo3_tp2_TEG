package edu.fiuba.algo3.modelo.flujoDeJuego;

import edu.fiuba.algo3.infraestructura.IRandomizador;
import edu.fiuba.algo3.infraestructura.Randomizador;
import edu.fiuba.algo3.modelo.ataque.ConjuntoDados;
import edu.fiuba.algo3.modelo.ataque.ConstructorDeConjuntoDados;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Jugador;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class JugadaAtacarTest {

    @Test
    public void test01unJugadorAtacaYConquistaPorLoQueLaSiguienteRondaEsDeReagrupe(){
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(1);
        otroPais.asignarJugador(otroJugador);
        Jugada jugadaAtacar = new JugadaAtacar(unPais, otroPais, 1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.ejercitosPerdidos(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });
        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        ArrayList<ConjuntoDados> lista = new ArrayList<>();
        lista.add(atacante);
        lista.add(defensor);
        when(constructor.obtenerConjuntosDados(1,1)).thenReturn(lista);
        Tablero tablero = new Tablero(new HashMap<>(), constructor);
        Ronda ronda = mock(Ronda.class);
        jugadaAtacar.ejecutar(tablero, ronda);
        verify(ronda, times(1)).jugadorDebeReagrupar();
    }

    @Test
    public void test02unJugadorAtacaYConquistaPorLoQueElPaisPasaASerSuyo(){
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(1);
        otroPais.asignarJugador(otroJugador);
        Jugada jugadaAtacar = new JugadaAtacar(unPais, otroPais, 1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.ejercitosPerdidos(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });
        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        ArrayList<ConjuntoDados> lista = new ArrayList<>();
        lista.add(atacante);
        lista.add(defensor);
        when(constructor.obtenerConjuntosDados(1,1)).thenReturn(lista);
        Tablero tablero = new Tablero(new HashMap<>(), constructor);
        Ronda ronda = new Ronda(tablero, new ListaJugadores(new ArrayList<>()));
        jugadaAtacar.ejecutar(tablero, ronda);
        assertTrue(otroPais.getJugador().equals(unJugador));
    }

    @Test
    public void test03unJugadorAtacaYNoConquistaPorLoQueElPaisNoPasaASerSuyo(){
        Jugador unJugador = new Jugador();
        Jugador otroJugador = new Jugador();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(2);
        unPais.asignarJugador(unJugador);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        otroPais.asignarJugador(otroJugador);
        Jugada jugadaAtacar = new JugadaAtacar(unPais, otroPais, 1);
        ConjuntoDados atacante = mock(ConjuntoDados.class);
        ConjuntoDados defensor = mock(ConjuntoDados.class);
        when(atacante.ejercitosPerdidos(defensor)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList<Integer> lista = new ArrayList();
                lista.add(0);
                lista.add(1);
                return lista;
            }
        });
        ConstructorDeConjuntoDados constructor = mock(ConstructorDeConjuntoDados.class);
        ArrayList<ConjuntoDados> lista = new ArrayList<>();
        lista.add(atacante);
        lista.add(defensor);
        when(constructor.obtenerConjuntosDados(1,2)).thenReturn(lista);
        Tablero tablero = new Tablero(new HashMap<>(), constructor);
        Ronda ronda = new Ronda(tablero, new ListaJugadores(new ArrayList<>()));
        jugadaAtacar.ejecutar(tablero, ronda);
        assertTrue(otroPais.getJugador().equals(otroJugador));
    }

}