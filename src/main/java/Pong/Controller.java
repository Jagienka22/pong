package Pong;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.awt.event.KeyEvent;

/**
 * Created by aga on 27.11.18.
 */
public class Controller {
    public Label label;                                            // Etykieta
    public Canvas canvas;                                        // "PĹĂłtno" do rysowania
    private GraphicsContext gc;
    private double x1, y1, x2, y2;
    private int weight = 10;
    private int height = 100;
    Rectangle rectangle1 = new Rectangle();
    Rectangle rectangle2 = new Rectangle();

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        System.out.println("clear");
        gc.setFill(Color.BLACK);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }


    private void rect(GraphicsContext gc) {
        rectangle1.setX(150.0f);
        rectangle1.setY(75.0f);
        rectangle1.setWidth(300.0f);
        rectangle1.setHeight(150.0f);

    }

    public void draw(ActionEvent actionEvent) {
        System.out.println("draw");

        WritableImage wr = new WritableImage(900, 600);
        PixelWriter pw = wr.getPixelWriter();

        narysujLewaPaletke(pw, 300-(height/2));

        narysujPrawaPaletke(pw, 300+(height/2));
// przy próbach robienia czegokolwiek zostawic karty w przegladarce ktore nie pomogly i wynomentowany
// kod który nie działał - ma być go przynajmniej 4 próby na każdą rzecz
        narysujPilke(pw, 100, 100);


        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, 900, 600);
    }

    private void narysujPilke(PixelWriter pw, int kierunekX, int kierunekY) {
        for (int x = 447 + kierunekX; x<459 + kierunekX;x++) { //czy to jest srodek planszy - jak nie to zmienic x i y
            for (int y = 297 - kierunekY; y<309-kierunekY; y++) {//bedzie sie przemieszczal o jeden piksel w ukosie
                pw.setArgb(x, y, 0xFFFFFFFF);//- wyestraktowac medote a potem przesunac o 10 w gore i prawo
            }
        }
    }

    //znalezc cos w javie fx do czytania klawiszy, jak ktos nacisnie klawisz "w" w aplikacji sout'nąć "w"

    //dodaj 2 klawisze "P" i "L" i okno/pole tekstowe z "0 : 0" na pasku klawiszy
    // i niech przyciścięcie "P" zwiększa prawą liczbę o 1 a "L" lewą

    private void narysujPrawaPaletke(PixelWriter pw, int yp) {
        for (int x = 896; x >896 - weight;x--) {
            for (int y = yp; y>yp - height ; y--) {
                pw.setArgb(x, y, 0xFFFFFFFF);
            }
        }
    }

    private void narysujLewaPaletke(PixelWriter pw, int yl) {
        for (int x = 4; x < weight + 4; x++) {
            for (int y = yl; y < height + yl; y++) {
                pw.setArgb(x, y, 0xFFFFFFFF);
            }
        }
    }

    public void clearGame(ActionEvent actionEvent) {
        clear(gc);
    }

    public void mouseMoves(Event event) {
    }

    public void mousePressed(MouseEvent mouseEvent) {
        x1 = mouseEvent.getX();
        y1 = mouseEvent.getY();
        x2 = x1;
        y2 = y1;
    }

    public void mouseReleased(Event event) {
        rect(gc);
        System.out.format("%f %f %f %f\n", x1, y1, x2, y2);
    }

    public void keyReleased(KeyEvent e)
    {
        System.out.println("Key Released: " + e.getKeyChar() + "\n");
    }

    public void keyPressed(KeyEvent e)
    {
        System.out.println("Key Pressed: " + e.getKeyChar() + "\n");
    }

    private void addKeyHandler(Scene scene) {
        scene.setOnKeyPressed(ke -> {
            KeyCode keyCode = ke.getCode();
            if (keyCode.equals(KeyCode.S)) {
                System.out.println("S");
                return;
            }
            if (keyCode.equals(KeyCode.R)) {
                System.out.println("R");
                return;
            }
        });
    }

    private void createScenneEventHandling(Scene scene){
        //scene.setOnKeyPressed((KeyEvent event)) -> {

        //}
    }
    public void clikL(ActionEvent actionEvent) {

    }

    public void clikP(ActionEvent actionEvent) {
    }
}
