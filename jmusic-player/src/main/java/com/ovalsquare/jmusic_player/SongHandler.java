package com.ovalsquare.jmusic_player;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.CompositeParser;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.Parser;
import org.apache.tika.parser.mp3.Mp3Parser;
import java.net.ContentHandler;
import org.xml.sax.helpers;
import org.apache.tika.sax.BasicContentHandlerFactory;

// "A:\vampire heart (slowed  reverb).mp3"
public class SongHandler {
	private Metadata _metadata;
	private String songPath;
	private ArrayList<Song> _songList;
	private Parser _parser;
	private ContentHandler _handler;
	private BasicContentHandlerFactory factory;
	private ParseContext _parseContext;
	
	public SongHandler() {
		this.songPath = "A:\\Bruno Mars - Grenade (Official Music Video).mp3";
		
		this._parser = new Mp3Parser();
		this._metadata = new Metadata();
		this._handler = new BasicContentHandlerFactory().getNewContentHandler()t;
		this._parseContext = new ParseContext();
		this._songList = new ArrayList<Song>();
	}
	
	/**
     * @param args
     */
    public void stackoverflow() {
        String fileLocation = this.songPath;

        try {

        InputStream input = new FileInputStream(new File(fileLocation));
        ContentHandler handler = new DefaultHandler();
        Metadata metadata = new Metadata();
        Parser parser = new Mp3Parser();
        ParseContext parseCtx = new ParseContext();
        parser.parse(input, handler, metadata, parseCtx);
        input.close();

        // List all metadata
        String[] metadataNames = metadata.names();

        for(String name : metadataNames){
        System.out.println(name + ": " + metadata.get(name));
        }

        // Retrieve the necessary info from metadata
        // Names - title, xmpDM:artist etc. - mentioned below may differ based
        System.out.println("----------------------------------------------");
        System.out.println("Title: " + metadata.get("title"));
        System.out.println("Artists: " + metadata.get("xmpDM:artist"));
        System.out.println("Composer : "+metadata.get("xmpDM:composer"));
        System.out.println("Genre : "+metadata.get("xmpDM:genre"));
        System.out.println("Album : "+metadata.get("xmpDM:album"));

        } catch (FileNotFoundException e) {
        e.printStackTrace();
        } catch (IOException e) {
        e.printStackTrace();
        } catch (SAXException e) {
        e.printStackTrace();
        } catch (TikaException e) {
        e.printStackTrace();
        }
       }
    
	
	
	public void printMetadata() {
		InputStream stream = null;
		try {
			stream = new FileInputStream(new File(songPath));
		} catch (FileNotFoundException e) {
			System.out.println("File not found, is the song path correct?");
			e.printStackTrace();
		} 
		try {
		    try {
				_parser.parse(stream, _handler, _metadata, _parseContext);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SAXException e) {
				System.out.println("XHTML SAX processing error with _handler var.");
				e.printStackTrace();
			} catch (TikaException e) {
				System.out.println("Stream can be read but not parsed, likely a corrupt file.");
				e.printStackTrace();
			} // parse the stream
		} finally {
		    try {
				stream.close();
			} catch (IOException e) {
				System.out.println("Failed to close the stream.");
				e.printStackTrace();
			} // close the stream
		}
        
        String[] data = _metadata.names();
        for (String thing : data) {
        	System.out.println(thing + " " + _metadata.get(thing));
        }
		System.out.println(_metadata.names());
		System.out.println("made it to the end somehow");
	}
	
	

}
