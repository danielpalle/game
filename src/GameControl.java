import java.io.IOException;

public class GameControl {
    GUI gui;
    Timer tickTimer;
    Player player;
    PixelCanvas pixelCanvas;

    public GameControl() throws IOException {
        tickTimer = new Timer(this);
        gui = new GUI();
        pixelCanvas = new PixelCanvas();
        player = new Player();
        setObjectDependencies();
        startGame();
    }

    public void setObjectDependencies(){
        gui.setPixelCanvasAndPlayer(pixelCanvas, player);
        pixelCanvas.setPlayer(player);
        player.setPixelCanvas(pixelCanvas);
    }

    public void startGame(){
        gui.buildGameWindow();
        tickTimer.startTimer();
    }

    public void tick(){
        player.calculateYSpeed();
        player.movePlayer();
        gui.repaintPixelCanvas();
    }
}
