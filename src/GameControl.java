import java.io.IOException;
import java.util.ArrayList;

public class GameControl {
    GUI gui;
    Timer tickTimer;
    Player player;
    PixelCanvas pixelCanvas;
    ArrayList<Bomb> bombs;

    public GameControl() throws IOException {
        tickTimer = new Timer(this);
        gui = new GUI();
        pixelCanvas = new PixelCanvas();
        player = new Player();
        bombs = new ArrayList<Bomb>();
        bombs.add(new Bomb(120, 110, 1, 0));
        injectDependencies();
        startGame();
    }

    public void injectDependencies() {
        gui.injectPixelCanvasAndPlayer(pixelCanvas, player);
        pixelCanvas.injectPlayer(player);
        player.injectPixelCanvas(pixelCanvas);
        pixelCanvas.injectBombs(bombs);
    }

    public void startGame() {
        gui.buildGameWindow();
        tickTimer.startTimer();
    }

    public void tick() {
        player.movePlayer(2);
        moveBombs();
        pixelCanvas.repaintPixelCanvas();
    }

    public void moveBombs() {
        for (int i = 0; i < bombs.size(); i++) {
            bombs.get(i).moveBomb(1);
        }
    }
}
