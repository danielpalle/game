import java.io.IOException;

public class GameControl {
    GUI gui;
    Timer timer;
    Player player;

    public GameControl() throws IOException {
        player = new Player();
        gui = new GUI(player);
        timer = new Timer(this);
    }

    public void startGame(){
        gui.buildGameWindow();
        timer.startTimer();
    }

    public void tick(){
        player.movePlayer();
        gui.repaintPixelCanvas();
    }


}
