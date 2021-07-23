package edu.fiuba.algo3.modelo.flujoDeJuego;

public class RondaAtaqueTest {

    /*
    Tablero tablero;
    Comunicacion comunicacion;
    EleccionAtaque eleccion;
    ArrayList<Jugador> jugadores;
    Pais unPais;
    Pais otroPais;

    public void setUp(){
        unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        tablero = mock(Tablero.class);
        comunicacion = mock(Comunicacion.class);
        eleccion = mock(EleccionAtaque.class);
        jugadores = new ArrayList<Jugador>();
    }


    @Test
    public void test01seCreaUnaRondaDeAtaqueNoSeConquistaYEstaDevuelveContinuar(){
        setUp();
        Jugador jugadorUno = new Jugador();
        Jugador jugadorDos = new Jugador();
        jugadores.add(jugadorUno);
        jugadores.add(jugadorDos);
        when(comunicacion.getEleccionAtaque()).thenReturn(eleccion);
        when(eleccion.pedirAtaque()).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                ArrayList lista = new ArrayList();
                lista.add("Terminar");
                return lista;
            }
        });
        RondaAtaque ronda = new RondaAtaque(jugadores, comunicacion);
        assertTrue(ronda.realizarRondaYContinuar(tablero));
    }

    @Test
    public void test02seCreaUnaRondaDeAtaqueSeConquistaUnJugadorGanoYEstaDevuelveNoContinuar(){
        setUp();
        Pais unPais = new Pais("Argentina");
        unPais.colocarEjercitos(5);
        Pais otroPais = new Pais("Chile");
        otroPais.colocarEjercitos(2);
        Jugador jugadorUno = mock(Jugador.class);
        Jugador jugadorDos = mock(Jugador.class);
        jugadores.add(jugadorUno);
        jugadores.add(jugadorDos);
        when(comunicacion.getEleccionAtaque()).thenReturn(eleccion);
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
        when(tablero.conquisto(eq(unPais), eq(otroPais), eq(3))).thenReturn(true);
        when(jugadorUno.jugadorGano()).thenReturn(true);
        RondaAtaque ronda = new RondaAtaque(jugadores, comunicacion);
        assertFalse(ronda.realizarRondaYContinuar(tablero));
    }

    @Test
    public void test03unaRondaDeDosJugadoresJugador1AtacaYConquista2PaisesDelJugador2(){
        setUp();
        Pais otroPaisMas = new Pais("Brasil");
        otroPaisMas.colocarEjercitos(2);
        Jugador jugadorUno = mock(Jugador.class);
        Jugador jugadorDos = mock(Jugador.class);
        this.unPais.asignarJugador(jugadorUno);
        this.otroPais.asignarJugador(jugadorDos);
        otroPaisMas.asignarJugador(jugadorDos);
        jugadores.add(jugadorUno);
        jugadores.add(jugadorDos);
        when(comunicacion.getEleccionAtaque()).thenReturn(eleccion);
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
                else if(count == 1){
                    lista.add(unPais);
                    lista.add(otroPaisMas);
                    lista.add(3);
                    count++;
                }
                else{
                    lista.add("Terminar");
                }
                return lista;
            }
        });
        when(eleccion.cantidadAMover()).thenReturn(1);
        when(tablero.conquisto(eq(unPais), eq(otroPais), eq(3))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                otroPais.serConquistadoPor(jugadorUno);
                return true;
            }
        });
        when(tablero.conquisto(eq(unPais), eq(otroPaisMas), eq(3))).thenAnswer(new Answer() {
            public Object answer(InvocationOnMock invocation) {
                otroPaisMas.serConquistadoPor(jugadorUno);
                return true;
            }
        });
        when(jugadorUno.jugadorGano()).thenReturn(false);
        RondaAtaque ronda = new RondaAtaque(jugadores, comunicacion);

        int cantidadOriginalUnPais = otroPais.getCantidadEjercitos();
        int cantidadOriginalUnPaisMas = otroPais.getCantidadEjercitos();

        assertTrue(ronda.realizarRondaYContinuar(tablero));

        assertEquals(unPais.getCantidadEjercitos(), 3);
        assertEquals(otroPais.getCantidadEjercitos(), cantidadOriginalUnPais+1);
        assertEquals(otroPaisMas.getCantidadEjercitos(), cantidadOriginalUnPaisMas+1);
    }
*/
}
