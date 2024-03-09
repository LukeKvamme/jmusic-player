package com.ovalsquare.jmusicplayer;

import java.io.IOException;
import java.net.URISyntaxException;
import java.io.File;
import java.util.ArrayList;

import javafx.animation.*;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;


public class SongHandler {
	private ArrayList<Song> _songList;
	private String _songDirectory;
	private Song _song;
	private Mp3File _mp3file;
	private ID3v1 _v1Tag;
	private ID3v2 _v2Tag;

	public SongHandler(String song_directory) {
		
		this._songDirectory = (song_directory);
		this._songList = new ArrayList<Song>();
		this._song = new Song();
		this._mp3file = null;
		this._v1Tag = null;
		this._v2Tag = null;
	}
	
//	public void play() {
//		Media media = null;
//		try {
//		  media = new Media(getClass().getResource("/music/hero.mp3").toURI().toString());
//		} catch (URISyntaxException e) {
//		  e.printStackTrace();
//		} 
//	}
	
	public ArrayList<Song> createSongList() {
		File dir = new File(this._songDirectory);
		File[] song_files = dir.listFiles((File pathname) -> pathname.getName().endsWith(".mp3"));
		
		for (File song : song_files) {
			this._songList.add(createSong(song.getAbsolutePath()));
		}
		
		return this._songList;
	}

	/**
	 * Given a filepath to the .mp3 song, creates a Song object with attributes it obtains from the metadata of the
	 * 	.mp3 file. Gets the attributes from the MP3 ID3v1 or ID3v2 tag, whichever it has.
	 * @param song_path
	 * @return
	 */
	private Song createSong(String song_path) {
		try {
			this._mp3file = new Mp3File(song_path);
		} catch (UnsupportedTagException e) {
			e.printStackTrace();
		} catch (InvalidDataException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (this._mp3file.hasId3v1Tag()) {
			this._v1Tag = this._mp3file.getId3v1Tag();
			String[] song_attributes = {
					this._v1Tag.getTitle(),
					this._v1Tag.getArtist(),
					this._v1Tag.getAlbum(),
					this._v1Tag.getTrack(),
					this._v1Tag.getYear(),
					this._v1Tag.getGenreDescription(),
					this._v1Tag.getComment(),
					song_path
					};
			this._song = new Song(song_attributes);
		}

		else if (this._mp3file.hasId3v2Tag()) {
			this._v2Tag = this._mp3file.getId3v2Tag();
			String[] song_attributes = {
					this._v2Tag.getTitle(),
					this._v2Tag.getArtist(),
					this._v2Tag.getAlbum(),
					this._v2Tag.getTrack(),
					this._v2Tag.getYear(),
					this._v2Tag.getGenreDescription(),
					this._v2Tag.getComment(),
					song_path
					};
			this._song = new Song(song_attributes);
		}
		
		return this._song;
	}


}
