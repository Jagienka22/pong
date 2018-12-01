/**
 * Created by aga on 27.11.18.
 */

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;


public class Pong extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");

        /*TextField textBox = new TextField();
        textBox.setPromptText("Write here");
        textBox.setOnKeyPressed(ke -> System.out.println("Key Pressed: " + ke.getText()));
        root.getChildren().add(textBox);
        */

        Scene scene = new Scene(root);
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
