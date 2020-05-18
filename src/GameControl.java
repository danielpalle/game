import java.io.IOException;

public class GameControl {
    GUI gui;
    Timer ticktimer;
    Player player;
    PixelCanvas pixelcanvas;

    public GameControl() throws IOException {
        ticktimer = new Timer(this);
        gui = new GUI();
        pixelcanvas = new PixelCanvas();
        player = new Player();
    }

    public void setObjectDependencies(){
        gui.setPixelCanvasAndPlayer(pixelcanvas, player);
        pixelcanvas.setGUIAndPlayer(gui, player);
        player.setPixelCanvas(pixelcanvas);
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
