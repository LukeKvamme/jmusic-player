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

	    private ListView<String> _fileList;
	    private MediaPlayer _mediaPlayer;

	    @Override
	    public void start(Stage primaryStage) {
	        primaryStage.setTitle("Jmusic Player");

	        _fileList = new ListView<>();
	        updateFileList(new File(System.getProperty("user.home").concat("\\Music")));

	        _fileList.setOnMouseClicked(event -> {
	            String selected_item = _fileList.getSelectionModel().getSelectedItem();
	            if (selected_item != null) {
	                String file_path = System.getProperty("user.home").concat("\\Music") + File.separator + selected_item;
	                File file = new File(file_path);
	                if (file.isFile() && file.getName().toLowerCase().endsWith(".mp3")) {
	                    playMP3(file);
	                }
	            }
	        });

	        VBox file_list_view = new VBox(_fileList);
	        
	        BorderPane root = new BorderPane();
	        root.setRight(file_list_view);

	        Scene scene = new Scene(root, 500, 400);
	        primaryStage.setScene(scene);
	        primaryStage.show();
	    }

	    private void updateFileList(File directory) {
	        _fileList.getItems().clear();
	        File[] files = directory.listFiles();
	        
	        if (files != null) {
	            for (File file : files) {
	            	if (file.getName().toLowerCase().endsWith(".mp3")) {
	            		_fileList.getItems().add(file.getName());
	            	}
	            }
	        }
	    }

	    private void playMP3(File file) {
	        if (_mediaPlayer != null) {
	            _mediaPlayer.stop();
	        }
	        Media media = new Media(file.toURI().toString());
	        _mediaPlayer = new MediaPlayer(media);
	        _mediaPlayer.play();
	    }

	    public static void main(String[] args) {
	        launch(args);
	    }
	}


