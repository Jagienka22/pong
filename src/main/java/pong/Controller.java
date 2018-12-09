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
    public static final int WHITE = 0xFFFFFFFF;
    public static final int BOARD_WIDTH = 900;
    public static final int BOARD_HEIGHT = 600;
    public static final int MARGIN = 4;
    public static final int PAD_WIDTH = 10;
    public static final int PAD_HEIGHT = 100;
    private Canvas canvas;
    private GraphicsContext gc;
    private int width = 10;
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
        WritableImage wr = new WritableImage(BOARD_WIDTH, BOARD_HEIGHT);
        PixelWriter pw = wr.getPixelWriter();

        narysujLewaPaletke(pw, BOARD_HEIGHT/2);
        narysujPrawaPaletke(pw, BOARD_HEIGHT/2);
        ruchPilki(pw, kierunekx, kieruneky);

        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, BOARD_WIDTH, BOARD_HEIGHT);
    }

    public void initialDraw(ActionEvent actionEvent) {
        draw();
    }
    public void newDraw() {
        width = 10;
        height = 100;
        wysokoscL = 300;
        wysokoscP = 299;
        kierunekx = 0;
        kieruneky = -3;
        znakOperacjiY = 1;
        znakOperacjiX = 1;
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
                pw.setArgb(x, y, WHITE);
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
                newDraw();
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
                newDraw();
            }
        }
        kierunekx = kierunekx + (10 * znakOperacjiX);
    }

    private void narysujPrawaPaletke(PixelWriter pw, int middleY) {
        int x = BOARD_WIDTH-1-MARGIN;
        for (int i = 0; i < PAD_WIDTH; i++) {
            int y = middleY-PAD_HEIGHT/2;
            for (int j = 0; j < PAD_HEIGHT; j++) {
                pw.setArgb(x, y, WHITE);
                y++;
            }
            x--;
        }
    }

    private void narysujLewaPaletke(PixelWriter pw, int middleY) {
        int x = MARGIN;
        for (int i = 0; i < PAD_WIDTH; i++) {
            int y = middleY-PAD_HEIGHT/2;
            for (int j = 0; j < PAD_HEIGHT; j++) {
                pw.setArgb(x, y, WHITE);
                y++;
            }
            x++;
        }
    }

    public void clearGame(ActionEvent actionEvent) {
        clear(gc);

    }
}

// bug przy kliknieciu zakoncz (pamieta wczesniejsze pola)
//ruch kazdej paletki w jednej metodzie