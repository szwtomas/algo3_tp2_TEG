package edu.fiuba.algo3.modelo;

import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import java.util.ArrayList;

import static org.mockito.Mockito.*;


import static org.junit.jupiter.api.Assertions.*;

public class TurnoAtaqueTest {

    @Test
    public void test01sePideAtacarYElTurnoDelegaEnElTableroCorrectamente(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count=0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(2);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        Jugador atacante = mock(Jugador.class);
        Tablero tablero = mock(Tablero.class);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        verify(tablero).conquisto(unPais, otroPais, 2);
    }

    @Test
    public void test02elAtaqueConquistaYElTurnoTransfiereLasFichasPedidas(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        Tablero tablero = mock(Tablero.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(2);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenAnswer(new Answer() {

            public Object answer(InvocationOnMock invocation) {
                return 2;
            }
        });
        when(tablero.conquisto(unPais, otroPais, 2)).thenAnswer(new Answer() {

            public Object answer(InvocationOnMock invocation) {
                return true;
            }
        });
        Jugador atacante = mock(Jugador.class);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(3, unPais.getCantidadEjercitos());
    }

    @Test
    public void test03elAtaqueNoConquistaYElTurnoNoTransfiereLasFichasPedidas(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        Tablero tablero = mock(Tablero.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(3);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return 2;
            }
        });
        when(tablero.conquisto(unPais, otroPais, 2)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return false;
            }
        });
        Jugador atacante = mock(Jugador.class);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        turno.realizarTurnoYContinuar(tablero);
        assertEquals(5, unPais.getCantidadEjercitos());
    }

    @Test
    public void test04sePideAtacarYElJugadorCumplioUnObjetivo(){
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        EleccionAtaque eleccion = mock(EleccionAtaque.class);
        Tablero tablero = mock(Tablero.class);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            int count = 0;
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                if(count == 0){
                    lista.add(unPais);
                    lista.add(otroPais);
                    lista.add(3);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return 2;
            }
        });
        when(tablero.conquisto(unPais, otroPais, 3)).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                return true;
            }
        });
        Jugador atacante = mock(Jugador.class);
        when(atacante.jugadorGano()).thenReturn(true);
        TurnoAtaque turno = new TurnoAtaque(atacante, eleccion);
        assertFalse(turno.realizarTurnoYContinuar(tablero));
    }

}