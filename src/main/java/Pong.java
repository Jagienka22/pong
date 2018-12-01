/**
 * Created by aga on 27.11.18.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import pong.Controller;

public class Pong extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
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

        Button zagraj = new Button("Zagraj");
        Button zakoncz = new Button("Zakoncz");
        Button L = new Button("L");
        Button P = new Button("P");
        Label label = new Label("         0  :  0");
        Canvas pole = new Canvas(900, 600);
        GraphicsContext gc = pole.getGraphicsContext2D();
        //gc.setFill(Color.BLACK);
        Controller con = new Controller(pole);
        con.initialize();

        gridPane.add(zagraj, 1, 0, 1, 6);
        gridPane.add(zakoncz, 2, 0, 1, 6);
        gridPane.add(L, 1, 9, 1, 6);
        gridPane.add(P, 2, 9, 1, 6);
        gridPane.add(label, 1, 12, 1, 6);
        gridPane.add(pole, 0, 0, 1, 6);
        zagraj.onActionProperty();
        zagraj.setOnAction(con::draw);
        zakoncz.setOnAction(con::clearGame);

        Scene scene = new Scene(gridPane,1100,600);
        //Scene scene = new Scene(root);
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
