package com.ovalsquare.jmusic_player;



public class Song {
	private String _songPath;
	private String _title;
	private String _artist;
	private String _album;
	private String _track;
	private String _year;
	private String _genre;
	private String _comment;
	
	public Song() {
		this._songPath = null;
		this.set_title("");
		this.set_artist("");
		this.set_album("");
		this.set_track("");
		this.set_year("");
		this.set_genre("");
		this.set_comment("");
	}
	
	/**
	 * Overloaded constructor that takes in a String array where each index value is mapped:
	 * 		0 = song title
	 * 		1 = song artist
	 * 		2 = song album
	 * 		3 = song track
	 * 		4 = song year
	 * 		5 = song genre
	 * 		6 = song comment
	 * 		7 = the song file path
	 * @param songAttributes
	 */
	public Song(String[] song_attributes) {
		this._title = song_attributes[0];
		this._artist = song_attributes[1];
		this._album = song_attributes[2];
		this._track = song_attributes[3];
		this._year = song_attributes[4];
		this._genre = song_attributes[5];
		this._comment = song_attributes[6];
		this._songPath = song_attributes[7];
	}

	/**
	 * @return the _title
	 */
	public String get_title() {
		return _title;
	}

	/**
	 * @param _title the _title to set
	 */
	public void set_title(String _title) {
		this._title = _title;
	}

	/**
	 * @return the _artist
	 */
	public String get_artist() {
		return _artist;
	}

	/**
	 * @param artist the _artist to set
	 */
	public void set_artist(String artist) {
		this._artist = artist;
	}

	/**
	 * @return the _album
	 */
	public String get_album() {
		return _album;
	}

	/**
	 * @param _album the _album to set
	 */
	public void set_album(String _album) {
		this._album = _album;
	}

	/**
	 * @return the _track
	 */
	public String get_track() {
		return _track;
	}

	/**
	 * @param _track the _track to set
	 */
	public void set_track(String _track) {
		this._track = _track;
	}

	/**
	 * @return the _year
	 */
	public String get_year() {
		return _year;
	}

	/**
	 * @param _year the _year to set
	 */
	public void set_year(String _year) {
		this._year = _year;
	}

	/**
	 * @return the _genre
	 */
	public String get_genre() {
		return _genre;
	}

	/**
	 * @param _genre the _genre to set
	 */
	public void set_genre(String _genre) {
		this._genre = _genre;
	}

	/**
	 * @return the _comment
	 */
	public String get_comment() {
		return _comment;
	}

	/**
	 * @param _comment the _comment to set
	 */
	public void set_comment(String _comment) {
		this._comment = _comment;
	}
	
	/**
	 * @return the _songPath
	 */
	public String get_songPath() {
		return _songPath;
	}

	/**
	 * @param song_path the _songPath to set
	 */
	public void set_songPath(String song_path) {
		this._songPath = song_path;
	}

}
