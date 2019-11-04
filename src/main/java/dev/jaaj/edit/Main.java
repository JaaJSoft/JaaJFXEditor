package dev.jaaj.edit;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

import javafx.stage.Stage;
import jfxtras.styles.jmetro.JMetro;
import jfxtras.styles.jmetro.Style;

import java.io.File;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        //ResourceBundle strings = ResourceBundle.getBundle("strings");
        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        primaryStage.setTitle("JaaJEdit");
        Scene scene = new Scene(root, 800, 800);
        new JMetro(scene, Style.LIGHT);
        primaryStage.setScene(scene);
        primaryStage.show();

//        FileChooser fileChooser = new FileChooser();
//        fileChooser.setTitle("Open Resource File");
//        fileChooser.getExtensionFilters().addAll(
//                new ExtensionFilter("Text Files", "*.txt"),
//                new ExtensionFilter("Image Files", "*.png", "*.jpg", "*.gif"),
//                new ExtensionFilter("Audio Files", "*.wav", "*.mp3", "*.aac"),
//                new ExtensionFilter("All Files", "*.*"));
//        File selectedFile = fileChooser.showOpenDialog(primaryStage);
//        if (selectedFile != null) {
//            System.out.println(selectedFile.toString());
//        }
    }


    public static void main(String[] args) {
        System.out.println("Launching app");
        launch(args);
        System.out.println("Exiting app");
    }
}
