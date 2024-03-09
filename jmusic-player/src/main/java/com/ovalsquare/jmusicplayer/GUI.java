package com.ovalsquare.jmusicplayer;

import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;

public class GUI extends Application {
	public static void main(String[] args) {
        launch(args);
        
    }
    
    @Override
    public void start(Stage primaryStage) {
    	Media media = null;
        try {
          media = new Media(getClass().getResource("/vampire heart (slowed  reverb).mp3").toURI().toString());
          MediaPlayer mediaPlayer = new MediaPlayer(media);
          mediaPlayer.play();
        } catch (URISyntaxException e) {
          e.printStackTrace();
        } 
//        primaryStage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        primaryStage.setScene(new Scene(root, 300, 250));
//        primaryStage.show();

    }
    
    
    
    
}
