package com.ovalsquare.jmusic_player;



public class Song {
	private String _title;
	private String _subject;
	private String _album;
	private String _track;
	private String _year;
	private String _genre;
	private String _comment;
	
	public Song() {
		this.set_title("");
		this.set_subject("");
		this.set_album("");
		this.set_track("");
		this.set_year("");
		this.set_genre("");
		this.set_comment("");
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
	 * @return the _subject
	 */
	public String get_subject() {
		return _subject;
	}

	/**
	 * @param _subject the _subject to set
	 */
	public void set_subject(String _subject) {
		this._subject = _subject;
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

}
