package edu.fiuba.algo3.infraestructura;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import edu.fiuba.algo3.modelo.general.*;
import edu.fiuba.algo3.modelo.jugador.Objetivo;

public class Randomizador implements IRandomizador {
    public int generar(int low, int high){
        Random rand = new Random();
        return rand.nextInt(high)+low;
    }

    public void shuffle(ArrayList<Tarjeta> lista) {
        Collections.shuffle(lista);
    }

    public void mezclarObjetivos(ArrayList<Objetivo> lista) {
        Collections.shuffle(lista);
    }


}
