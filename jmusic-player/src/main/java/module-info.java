module jmusicplayer {
	requires javafx.controls;
	requires mp3agic;
	requires transitive javafx.graphics;
	//requires junit;
	
	exports com.ovalsquare.jmusicplayer to javafx.graphics;
}