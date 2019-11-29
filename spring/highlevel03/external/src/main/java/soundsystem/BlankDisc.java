package soundsystem;


public class BlankDisc {
    private String title;
    private String artist;
    public BlankDisc(String title, String artist){
        this.title = title;
        this.artist = artist;
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }
}
