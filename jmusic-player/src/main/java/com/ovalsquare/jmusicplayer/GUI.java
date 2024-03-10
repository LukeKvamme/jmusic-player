package com.ovalsquare.jmusicplayer;

import java.io.File;
import java.net.URISyntaxException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

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

        HBox controls = new HBox(playButton, pauseButton, stopButton, queueNextButton, queueLastButton);

        BorderPane root = new BorderPane();
        root.setLeft(_musicListView);
        root.setBottom(controls);

        Scene scene = new Scene(root, 600, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void updateFileList() {
        _musicListView.getItems().clear();
        _musicFiles.getItems().clear();
        
        for (Song song : _songHandler._songList) {
        	_musicListView.getItems().add(song.get_title());
        	_musicFiles.getItems().add(song);
        }
    	
    }

    private void playSelectedFile() {
    	Song selected_song = _musicFiles.getItems().get(_musicListView.getSelectionModel().getSelectedIndex());
//    	System.out.println(selected_song);
//    	System.out.println(_musicFiles.getItems());
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


