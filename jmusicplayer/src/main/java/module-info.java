module jmusicplayer {
	requires javafx.controls;
	requires mp3agic;
	requires transitive javafx.graphics;
	requires java.desktop;
	requires javafx.media;
	requires javafx.fxml;
	//requires junit;
	
	exports com.ovalsquare.jmusicplayer to javafx.graphics;
}