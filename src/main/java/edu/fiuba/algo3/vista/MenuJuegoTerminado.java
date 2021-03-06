package edu.fiuba.algo3.vista;

import edu.fiuba.algo3.App;
import edu.fiuba.algo3.modelo.flujoDeJuego.Ronda;
import javafx.event.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.*;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class MenuJuegoTerminado extends VistaMenu implements Observer {

    private static MenuJuegoTerminado instancia;
    private ImageView interfazJuegoTerminado;
    private Rectangle bandera = new Rectangle();
    //private Button botonVolverAlMenu;


    private MenuJuegoTerminado(Ronda ronda) throws IOException {
        super(ronda, 120, 72);
        inicializarInterfaz();
        this.getChildren().add(this.bandera);
        this.getChildren().add(this.interfazJuegoTerminado);
        //this.getChildren().add(this.botonVolverAlMenu);
    }


    private void inicializarInterfaz() throws FileNotFoundException {
        FileInputStream inputInterfazJuegoTerminado = new FileInputStream("./src/imagenes/juegoTerminado.png");
        Image imagenInterfazJuegoTerminado = new Image(inputInterfazJuegoTerminado);
        this.interfazJuegoTerminado = new ImageView(imagenInterfazJuegoTerminado);

        this.bandera.setHeight(550);
        this.bandera.setWidth(400);

        this.bandera.setTranslateX(-110);
        this.bandera.setTranslateY(30);

        /*
        this.botonVolverAlMenu = new Button("Menú Principal");
        this.botonVolverAlMenu.setStyle("-fx-background-color: rgb(204, 51, 17);" +
                "-fx-border-color: rgb(0, 0, 0);" +
                "-fx-font-weight: bold;" +
                "-fx-text-fill: rgb(255,255,255);");

        this.botonVolverAlMenu.setTranslateX(0);
        this.botonVolverAlMenu.setTranslateY(200);

        this.botonVolverAlMenu.addEventHandler(MouseEvent.MOUSE_CLICKED, e -> {

            App.empezarDevuelta();
            e.consume();
        });
        //this.botonVolverAlMenu.setTranslateX(75);
        */
    }

    private void cambiarColorBandera() {
        Color colorJugadorGanador = AsignadorDeColores.colorEjercitoSegunElNumero(ronda.jugadorGanador().getNumero());
        bandera.setFill(colorJugadorGanador);
    }


    public static void crearInstancia(Ronda ronda) throws IOException{
        if(instancia == null){
            instancia = new MenuJuegoTerminado(ronda);
        }
    }

    public static MenuJuegoTerminado obtenerInstancia(){
        return instancia;
    }

    @Override
    public void update(Observable o, Object arg) {
        if(this.ronda.juegoTerminado()){
            cambiarColorBandera();
            visibilizarMenu();
        }
    }

    private void visibilizarMenu() {
        this.setVisible(true);
        Reproductor.reproducirNuevaMusica("./src/sonidos/the-shire.wav");
        String ganador = AsignadorDeColores.jugadorActualSegunElNumero(this.ronda.jugadorGanador().getNumero());
        Label jugadorGanador = new Label("Jugador Ganador: " + ganador);
        jugadorGanador.setStyle("-fx-font: 46 arial; -fx-text-fill: black; -fx-font-weight: bold;");
        this.getChildren().add(jugadorGanador);

        jugadorGanador.setTranslateY(-300);
    }

    public boolean adentro(double mx, double my){
        return true;
    }
}
