/**
 * Created by aga on 27.11.18.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import pong.Controller;

public class Pong extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Hello World");
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(10);

        /*TextField textBox = new TextField();
        textBox.setPromptText("Write here");
        textBox.setOnKeyPressed(ke -> System.out.println("Key Pressed: " + ke.getText()));
        root.getChildren().add(textBox);
        */
        Canvas pole = new Canvas(900, 600);
        gridPane.add(pole, 0, 0, 1, 6);

        Controller con = new Controller(pole);
        con.initialize();

        Button zagraj = new Button("Zagraj");
        gridPane.add(zagraj, 1, 0, 1, 6);
        zagraj.setOnAction(con::draw);

        Button zakoncz = new Button("Zakoncz");
        gridPane.add(zakoncz, 2, 0, 1, 6);
        zakoncz.setOnAction(con::clearGame);

        Button L = new Button("L");
        gridPane.add(L, 1, 9, 1, 6);
        L.setOnAction(con::clikL);

        Button P = new Button("P");
        gridPane.add(P, 2, 9, 1, 6);
        P.setOnAction(con::clikP);

        Label label = new Label("         0  :  0");
        gridPane.add(label, 1, 12, 1, 6);

        Scene scene = new Scene(gridPane, 1100, 600);
        addKeyHandler(scene);

        primaryStage.setScene(scene);
        primaryStage.show();
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
            }
        });
    }
}
