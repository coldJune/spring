package soundsystem;

import java.util.List;

public class BlankDiscWithList implements CompactDisc {
    private String title;
    private String artist;
    private List<String> tracks;
    public BlankDiscWithList(String title, String artist, List<String> tracks){
        this.title= title;
        this.artist = artist;
        this.tracks = tracks;
    }
    public void play() {

    }
}
