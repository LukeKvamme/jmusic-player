package com.ovalsquare.jmusicplayer;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class GUI extends Application {

	private static SongHandler _songHandler;
	private ListView<String> _musicListView;
	private ListView<Song> _musicFiles;
    private MediaPlayer mediaPlayer;
    private ToggleButton playButton;
    private ToggleButton pauseButton;
    private ToggleGroup _group;
    private Button stopButton;
    private Button queueNextButton;
    private Button queueLastButton;

    @Override
    public void start(Stage primaryStage) {
    	primaryStage.setTitle("JMusic Player");
    	_songHandler = new SongHandler();
    	_musicListView = new ListView<>();
    	_musicFiles = new ListView<>();
    	updateFileList();

        playButton = new ToggleButton("Play");
        playButton.setOnAction(event -> playSelectedFile());

        pauseButton = new ToggleButton("Pause");
        pauseButton.setOnAction(event -> pausePlayback());
        
        playButton.setToggleGroup(_group);
        pauseButton.setToggleGroup(_group);

        stopButton = new Button("Stop");
        stopButton.setOnAction(event -> stopPlayback());

        queueNextButton = new Button("Queue Next");
        // Add functionality for queuing next

        queueLastButton = new Button("Queue Last");
        // Add functionality for queuing last
        

        // ImageView above ProgressBar
        ImageView imageView = new ImageView(new Image("space.jpg"));
        imageView.setPreserveRatio(true);
        imageView.fitWidthProperty().bind(primaryStage.widthProperty().multiply(0.8)); // 60% of the window width
        imageView.fitHeightProperty().bind(primaryStage.heightProperty().multiply(0.6)); // 60% of the window height
        
        
        Pane left_spacer = new Pane();
        HBox.setHgrow(left_spacer, Priority.ALWAYS); // Allow spacer to expand vertically

        Pane right_spacer = new Pane();
        HBox.setHgrow(right_spacer, Priority.ALWAYS); // Allow spacer to expand vertically
        
        HBox controls = new HBox(left_spacer, playButton, pauseButton, stopButton, queueNextButton, queueLastButton, right_spacer);
        HBox centered_image_view = new HBox(left_spacer, imageView, right_spacer);

     // Empty space between ImageView and ProgressBar
        Pane image_view_spacer = new Pane();
        VBox.setVgrow(image_view_spacer, Priority.ALWAYS); // Allow spacer to expand vertically
        
        // Empty space between ImageView and ProgressBar
        Pane progress_bar_spacer = new Pane();
//        spacer.setPrefHeight(200);
        VBox.setVgrow(progress_bar_spacer, Priority.ALWAYS); // Allow spacer to expand vertically

        // ProgressBar
        ProgressBar progressBar = new ProgressBar();
        progressBar.setProgress(0.5); // Example progress
        progressBar.prefWidthProperty().bind(primaryStage.widthProperty()); // 60% of the window width


        // VBox for ImageView and ProgressBar
        VBox vBox = new VBox(image_view_spacer, centered_image_view, progress_bar_spacer, progressBar, controls);
        vBox.setSpacing(10);
   

        // BorderPane to hold ListView on the left and VBox + Buttons on the right
        BorderPane root = new BorderPane();
        root.setLeft(_musicListView);
        root.setCenter(vBox);
       //root.setBottom(controls);

        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Jmusic Player");
        primaryStage.show();
        
    }

    /**
     * Updates the list of songs in the ListView on the left side of the application.
     * 		Displays _musicListView to the user.
     * 		Stores the actual Song object in the _musicFiles ListView for the backend.
     */
    private void updateFileList() {
        _musicListView.getItems().clear();
        _musicFiles.getItems().clear();
        
        for (Song song : _songHandler._songList) {
        	_musicListView.getItems().add(song.get_title());
        	_musicFiles.getItems().add(song);
        }
    	
    }

    /**
     * Plays the selected song. 
     * 
     * Checks if a song has been selected before Play was hit. If Play is hit before
     * 		a song is selected, an error message will pop up asking the user to select a song before trying again.
     * 
     * Then checks whether song has already been selected and is on-pause before creating a new media player 
     * 		on the newly-selected song.
     */
    private void playSelectedFile() {
    	Song selected_song = null;
    	try {
    		selected_song = _musicFiles.getItems().get(_musicListView.getSelectionModel().getSelectedIndex());
    	} catch (IndexOutOfBoundsException exception) {
    		nothingSelectedError();
    	}

    	if (pauseButton.isSelected()) {
    		mediaPlayer.play();
    		playButton.setSelected(true);
    		pauseButton.setSelected(false);
    	}
    	else if (_songHandler._songList.contains(selected_song)) {
    		File song_mp3_file = new File(selected_song.get_songPath());
    		stopPlayback();
    		
    		Media media = new Media(song_mp3_file.toURI().toString());
    		mediaPlayer = new MediaPlayer(media);
    		mediaPlayer.play();
    		playButton.setSelected(true);
    		pauseButton.setSelected(false);
    	}
    	else {
    		System.err.println("Something catastrophic occurred.");
    	}
    }

    private void nothingSelectedError() {
    	Stage error_stage = new Stage();
    	StackPane root = new StackPane();
    	Label error_label = new Label("Please select a song and press Play again.");
    	
    	root.getChildren().add(error_label);
		Scene error_scene = new Scene(root, 400, 100);
		
		error_stage.setScene(error_scene);
		error_stage.setTitle("Alert");
		error_stage.setAlwaysOnTop(true);
		error_stage.show();
	}

	private void pausePlayback() {
        if (mediaPlayer != null && mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
            mediaPlayer.pause();
            playButton.setSelected(false);
            pauseButton.setSelected(true);
        }
    }

    private void stopPlayback() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


