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
        injectDependencies();
        startGame();
    }

    public void injectDependencies(){
        gui.injectPixelCanvasAndPlayer(pixelCanvas, player);
        pixelCanvas.injectPlayer(player);
        player.injectPixelCanvas(pixelCanvas);
    }

    public void startGame(){
        gui.buildGameWindow();
        tickTimer.startTimer();
    }

    public void tick(){
        player.movePlayer();
        gui.repaintPixelCanvas();
    }
}
