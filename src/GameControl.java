import java.io.IOException;

public class GameControl {
    GUI gui;
    Timer ticktimer;
    Player player;

    public GameControl() throws IOException {
        player = new Player();
        gui = new GUI(player);
        ticktimer = new Timer(this);
    }

    public void startGame(){
        gui.buildGameWindow();
        ticktimer.startTimer();
    }

    public void tick(){
        player.calculateYSpeed();
        player.movePlayer();
        gui.repaintPixelCanvas();
    }
}
