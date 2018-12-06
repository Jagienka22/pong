package pong;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

public class Controller {
    private Canvas canvas;
    private GraphicsContext gc;
    private int weight = 10;
    private int height = 100;
    private int wysokoscL = 300;
    private int wysokoscP = 299;
    private int kierunekx = 0;
    private int kieruneky = -3;
    private int znakOperacjiY = 1;
    private int znakOperacjiX = 1;

    public Controller(Canvas canvas) {
        this.canvas = canvas;
    }

    public void initialize() {
        gc = canvas.getGraphicsContext2D();
        clear(gc);
    }

    private void clear(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
    }

    public void draw() {
        clear(gc);
        WritableImage wr = new WritableImage(898, 600);
        PixelWriter pw = wr.getPixelWriter();

        narysujLewaPaletke(pw, wysokoscL - (height / 2));
        narysujPrawaPaletke(pw, wysokoscP + (height / 2));
        ruchPilki(pw, kierunekx, kieruneky);

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, 898, 600);
    }

    public void initialDraw(ActionEvent actionEvent) {
        draw();
    }

    public void drawLeftAgainDown() {
        if (wysokoscL > 540) {
            wysokoscL = 540;
        }
        wysokoscL = wysokoscL + 10;
    }

    public void drawLeftAgainUp() {
        if (wysokoscL < 120) {
            wysokoscL = 120;
        }
        wysokoscL = wysokoscL - 10;
    }

    public void drawRightAgainDown() {
        if (wysokoscP > 539) {
            wysokoscP = 539;
        }
        wysokoscP = wysokoscP + 10;
    }

    public void drawRightAgainUp() {
        if (wysokoscP < 119) {
            wysokoscP = 119;
        }
        wysokoscP = wysokoscP - 10;
    }

    private void ruchPilki(PixelWriter pw, int kierunekx, int kieruneky) {
        for (int x = 444 + kierunekx; x < 454 + kierunekx; x++) { //czy to jest srodek planszy - jak nie to zmienic x i y
            for (int y = 297 - kieruneky; y < 307 - kieruneky; y++) {
                pw.setArgb(x, y, 0xFFFFFFFF);
            }
        }
    }

    public void iterate() {
        if (kieruneky > 234 || kieruneky <= (-283)) {
            znakOperacjiY = znakOperacjiY * (-1);
        }
        kieruneky = kieruneky + (10 * znakOperacjiY);

        if (kierunekx > 420) {
            znakOperacjiX = znakOperacjiX * (-1);

            int goraPilki = 297 - kieruneky;
            int dolPiki = goraPilki + 9;
            int dolPrawejPaletki = wysokoscP + (height / 2);
            int goraPrawejPaletki = dolPrawejPaletki - 99;
            if (!(dolPiki > goraPrawejPaletki && goraPilki < dolPrawejPaletki)) {
                Platform.runLater(Pong::clikL);
            }
        }

        if (kierunekx < (-420)) {
            znakOperacjiX = znakOperacjiX * (-1);

            int goraPilki = 297 - kieruneky;
            int dolPiki = goraPilki + 9;
            int goraLewejPaletki = wysokoscL - (height / 2);
            int dolLewejPaletki = goraLewejPaletki + 99;
            if (!(dolPiki > goraLewejPaletki && goraPilki < dolLewejPaletki)) {
                Platform.runLater(Pong::clikP);
            }
        }
        kierunekx = kierunekx + (10 * znakOperacjiX);
    }

    private void narysujPrawaPaletke(PixelWriter pw, int yp) {
        for (int x = 893; x > 893 - weight; x--) {
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