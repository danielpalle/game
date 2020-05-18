import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        GameControl gamecontrol = new GameControl();
        gamecontrol.setObjectDependencies();
        gamecontrol.startGame();
    }
}
