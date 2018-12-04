package pong;

import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;


public class Controller {
    private Label label;                                            // Etykieta
    private Canvas canvas;                                        // "PĹĂłtno" do rysowania
    private GraphicsContext gc;
    private double x1, y1, x2, y2;
    private int weight = 10;
    private int height = 100;
    private int wysokoscL = 300;
    private int wysokoscP = 299;


    public Controller(Canvas canvas) {
        this.canvas = canvas;
    }

    public void initialize() {
        System.out.println(canvas);
        gc = canvas.getGraphicsContext2D();
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        System.out.println(canvas);
        System.out.println("clear");
        gc.setFill(Color.BLACK);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void draw() {
        clear(gc);
        WritableImage wr = new WritableImage(900, 600);
        PixelWriter pw = wr.getPixelWriter();

        narysujLewaPaletke(pw, wysokoscL - (height / 2));
        narysujPrawaPaletke(pw, wysokoscP + (height / 2));
        narysujPilke(pw, 237);

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, 900, 600);
    }

    public void initialDraw(ActionEvent actionEvent) {
        draw();
    }

    public void drawLeftAgainDown() {
        if (wysokoscL > 540) {//te ify nie sa dobre, 4 linie powinny byc trzema liniami
            wysokoscL = 540;
        }
        wysokoscL = wysokoscL + 10;

        //draw();
    }

    public void drawLeftAgainUp() {
        if (wysokoscL < 120) {
            wysokoscL = 120;
        }
        wysokoscL = wysokoscL - 10;

        //draw();
    }

    public void drawRightAgainDown() {
        if (wysokoscP > 539) {
            wysokoscP = 539;
        }
        wysokoscP = wysokoscP + 10;

        //draw();
    }

    public void drawRightAgainUp() {
        if (wysokoscP < 119) {
            wysokoscP = 119;
        }
        wysokoscP = wysokoscP - 10;

        //draw();
    }

    private void narysujPilke(PixelWriter pw, int kierunek) {
        for (int x = 447 + kierunek; x < 459 + kierunek; x++) { //czy to jest srodek planszy - jak nie to zmienic x i y
            for (int y = 297 - kierunek; y < 309 - kierunek; y++) {//bedzie sie przemieszczal o jeden piksel w ukosie
                pw.setArgb(x, y, 0xFFFFFFFF);//- wyestraktowac medote a potem przesunac o 10 w gore i prawo
            }
        }
    }


    //dodaj 2 klawisze "P" i "L" i okno/pole tekstowe z "0 : 0" na pasku klawiszy
    // i niech przyciścięcie "P" zwiększa prawą liczbę o 1 a "L" lewą

    private void narysujPrawaPaletke(PixelWriter pw, int yp) {
        for (int x = 896; x > 896 - weight; x--) {
            for (int y = yp; y > yp - height; y--) {
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
}

// bug przy kliknieciu zakoncz (pamieta wczesniejsze pola)
//ruch kazdej paletki w jednej metodzie