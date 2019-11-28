import java.io.PrintStream;

public class Minstrel {
    private PrintStream stream;
    public Minstrel(PrintStream stream){
        this.stream = stream;
    }

    public void singBeforeQuest(){
        stream.println("before quest");
    }

    public void singAfterQuest(){
        stream.println("after quest");
    }
}
