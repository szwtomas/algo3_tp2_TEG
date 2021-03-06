package edu.fiuba.algo3.modelo.general;

import java.util.ArrayList;
import java.util.HashMap;

import edu.fiuba.algo3.infraestructura.*;

public class Mazo {

    private ArrayList<Tarjeta> listaTarjetas;
    IRandomizador randomizador;

    public Mazo(ArrayList<Tarjeta> listaTarjetas, IRandomizador randomizador) {
        this.listaTarjetas = listaTarjetas;
        this.randomizador = randomizador;
        randomizador.shuffle(this.listaTarjetas);
    }

    public Tarjeta entregarTarjeta(){
        if (this.listaTarjetas.size() == 0)
            throw new NoQuedanTarjetasException();

        return this.listaTarjetas.remove(0);
    }

    public void agregarTarjetas(Tarjeta t1, Tarjeta t2, Tarjeta t3){
        this.listaTarjetas.add(t1);
        this.listaTarjetas.add(t2);
        this.listaTarjetas.add(t3);
        t1.desactivar();
        t2.desactivar();
        t3.desactivar();
    }

}
