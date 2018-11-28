package Pong;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.effect.BlendMode;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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

        for (int x = 4; x < weight + 4; x++) {
            for (int y = 4; y < height + 4; y++) {
                pw.setArgb(x, y, 0xFFFFFFFF);
            }
        }

        for (int x = 896; x >896 - weight;x--) {
            for (int y = 596; y>596 - height ; y--) {
                pw.setArgb(x, y, 0xFFFFFFFF);
            }
        }

        for (int x = 447; x<459;x++) {
            for (int y = 297; y<309; y++) {
                pw.setArgb(x, y, 0xFFFFFFFF);
            }
        }


        gc.setGlobalBlendMode(BlendMode.SRC_OVER);
        gc.drawImage(wr, 0, 0, 900, 600);
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
}
