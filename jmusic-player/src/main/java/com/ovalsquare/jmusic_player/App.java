package com.ovalsquare.jmusic_player;

/**
 * Hello world!
 *
 */
public class App 
{
	public void SongTest() {
		SongHandler sh = new SongHandler();
		sh.printMetadata();
	}
    public static void main( String[] args )
    {
    	
    	SongHandler sh = new SongHandler();
//		sh.printMetadata();
    	sh.stackoverflow();
    }
}
