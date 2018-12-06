package pong;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;


public class Pong extends Application {
    private static int numberP = 0;
    private static int numberL = 0;
    private static Label label;
    private Controller con;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MineCraft");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        Canvas pole = new Canvas(900, 600);
        gridPane.add(pole, 0, 0, 1, 6);

        label = new Label(0 + " : " + 0);
        gridPane.add(label, 1, 12, 1, 6);

        con = new Controller(pole, this);
        con.initialize();

        Button zagraj = new Button("Zagraj");
        gridPane.add(zagraj, 1, 0, 1, 6);
        zagraj.setOnAction(con::initialDraw);

        Button zakoncz = new Button("Zakoncz");
        gridPane.add(zakoncz, 2, 0, 1, 6);
        zakoncz.setOnAction(con::clearGame);

//        Button L = new Button("L");
//        gridPane.add(L, 1, 9, 1, 6);
//        L.setOnAction(this::clikL);
//
//        Button P = new Button("P");
//        gridPane.add(P, 2, 9, 1, 6);
//        P.setOnAction(this::clikP);//przenies ta metode tu i niech ona zwieksza ktoras z liczb

        //wydziel liczby zeby dalo sie je zmieniac

        Scene scene = new Scene(gridPane, 1100, 600);
        addKeyHandler(scene);

        primaryStage.setScene(scene);
        primaryStage.show();

        MyThread myThread = new MyThread(con);
        myThread.start();
    }


    public static void clikL() {
        numberL++;
        displayNewLabel();
    }

    private static void displayNewLabel() {
        label.setText(numberL + " : " + numberP);
//        label.textProperty().bind(valueProperty);
    }

    public static void clikP() {
        numberP++;
        displayNewLabel();
    }

    private void addKeyHandler(Scene scene) {
        scene.setOnKeyPressed(keyEvent -> {
            KeyCode keyCode = keyEvent.getCode();
            if (keyCode.equals(KeyCode.W)) {
                con.drawLeftAgainUp();
                return;
            }
            if (keyCode.equals(KeyCode.S)) {
                con.drawLeftAgainDown();
            }

            if (keyCode.equals(KeyCode.DOWN)) {
                con.drawRightAgainDown();
            }

            if (keyCode.equals(KeyCode.UP)) {
                con.drawRightAgainUp();
            }
        });
    }
}


