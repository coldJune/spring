package soundsystem;

import org.springframework.stereotype.Component;

@Component
public class SgtPeppers implements CompactDisc {
    private String title = "pepper's title";
    private String artist = "pepper's artist";

    public void play() {
        System.out.println("playing "+ title +" by " + artist);
    }
}
