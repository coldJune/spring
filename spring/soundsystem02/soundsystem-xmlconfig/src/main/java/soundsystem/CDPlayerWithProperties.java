package soundsystem;

import org.springframework.beans.factory.annotation.Autowired;

public class CDPlayerWithProperties implements MediaPlayer {
    private CompactDisc compactDisc;

    @Autowired
    public void setCompactDisc(CompactDisc compactDisc){
        this.compactDisc = compactDisc;
    }
    public void play() {

    }
}
