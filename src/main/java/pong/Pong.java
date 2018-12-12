package pong;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
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

        Canvas pole = new Canvas(Controller.BOARD_WIDTH, Controller.BOARD_HEIGHT);
        gridPane.add(pole, 0, 0, 4, 5);

        label = new Label(0 + " : " + 0);
        gridPane.add(label, 2, 5, 2, 1);

        con = new Controller(pole);
        con.initialize();
        //MyThread t = new MyThread(con);

        Button zagraj = new Button("Zagraj");
        gridPane.add(zagraj, 0, 5, 1, 1);
        zagraj.setOnAction(con::initialDraw);
        //t.start();

        Button zakoncz = new Button("Zakoncz");
        gridPane.add(zakoncz, 1, 5, 1, 1);
        zakoncz.setOnAction(con::clearGame);
        //t.stop();

        Scene scene = new Scene(gridPane, Controller.BOARD_WIDTH, Controller.BOARD_HEIGHT + 50);
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


