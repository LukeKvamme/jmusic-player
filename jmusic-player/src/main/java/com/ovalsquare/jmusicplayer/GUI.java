package com.ovalsquare.jmusicplayer;

import java.io.File;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class GUI extends Application {

	    private ListView<String> fileList;
	    private MediaPlayer mediaPlayer; 

	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("File Browser with MediaPlayer");

	        fileList = new ListView<>();
	        updateFileList(new File(System.getProperty("user.home").concat("\\Music")));

	        fileList.setOnMouseClicked(event -> {
	            String selectedItem = fileList.getSelectionModel().getSelectedItem();
	            if (selectedItem != null) {
	                String filePath = System.getProperty("user.home") + File.separator + selectedItem;
	                File file = new File(filePath);
	                if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
	                    playMP3(file);
	                }
	            }
	        });

	        VBox root = new VBox(fileList);

	        Scene scene = new Scene(root, 400, 300);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private void updateFileList(File directory) {
	        fileList.getItems().clear();
	        File[] files = directory.listFiles();
	        if (files != null) {
	            for (File file : files) {
	                fileList.getItems().add(file.getName());
	            }
	        }
	    }

	    private void playMP3(File file) {
	        if (mediaPlayer != null) {
	            mediaPlayer.stop();
	        }
	        Media media = new Media(file.toURI().toString());
	        mediaPlayer = new MediaPlayer(media);
	        mediaPlayer.play();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


