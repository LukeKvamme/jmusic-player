package com.ovalsquare.jmusic_player;

import java.io.IOException;
import java.util.ArrayList;

import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.UnsupportedTagException;

/**
 * Hello world!
 *
 */
public class App 
{

    public static void main( String[] args ) throws UnsupportedTagException, InvalidDataException, IOException
    {
    	SongHandler sh = new SongHandler("A:\\");
    	ArrayList<Song> songs = sh.createSongList();
    	
    	for (Song s : songs) {
    		System.out.println(s.get_songPath());
    	}
    	
    	

    }
}
