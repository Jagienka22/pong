/**
 * Created by aga on 27.11.18.
 */

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pong.Controller;

public class Pong extends Application {
    private int numberP = 0;
    private int numberL = 0;
    private Label label;
    private Controller con;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MineCraft Pong");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Canvas pole = new Canvas(900, 600);
        gridPane.add(pole, 0, 0, 1, 6);

        con = new Controller(pole);
        con.initialize();

        Button zagraj = new Button("Zagraj");
        gridPane.add(zagraj, 1, 0, 1, 6);
        zagraj.setOnAction(con::initialDraw);

        Button zakoncz = new Button("Zakoncz");
        gridPane.add(zakoncz, 2, 0, 1, 6);
        zakoncz.setOnAction(con::clearGame);

        Button L = new Button("L");
        gridPane.add(L, 1, 9, 1, 6);
        L.setOnAction(this::clikL);

        Button P = new Button("P");
        gridPane.add(P, 2, 9, 1, 6);
        P.setOnAction(this::clikP);//przenies ta metode tu i niech ona zwieksza ktoras z liczb

        //wydziel liczby zeby dalo sie je zmieniac
        label = new Label(numberL + " : " + numberP);
        gridPane.add(label, 1, 12, 1, 6);

        Scene scene = new Scene(gridPane, 1100, 600);
        addKeyHandler(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void clikL(ActionEvent actionEvent) {
        numberL++;
        displayNewLabel();
    }

    private void displayNewLabel() {
        SimpleStringProperty valueProperty = new SimpleStringProperty(numberL + " : " + numberP);
        label.textProperty().bind(valueProperty);
    }

    public void clikP(ActionEvent actionEvent) {
        numberP++;
        displayNewLabel();
    }

    private void addKeyHandler(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            KeyEvent keyEvent1 = keyEvent;
            KeyCode keyCode = keyEvent1.getCode();
            if (keyCode.equals(KeyCode.W)) {
               con.drawLeftAgainUp();
                System.out.println("W");
                return;
            }
            if (keyCode.equals(KeyCode.S)) {
                con.drawLeftAgainDown();
                System.out.println("S");
            }

            if (keyCode.equals(KeyCode.DOWN)) {
                con.drawRightAgainDown();
                System.out.println("Arrow Down");
            }

            if(keyCode.equals(KeyCode.UP)){
                con.drawRightAgainUp();
            }
        });
    }
}
