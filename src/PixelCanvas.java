import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PixelCanvas extends JPanel {
    Player player;

    private static final int GAME_WIDTH = 160; // GAME_ refers to the pixels we see of the gameworld. At 180 width we can see a quarter of the gameworld at once.
    private static final int GAME_HEIGHT = 180;
    private static final int BACKGROUND_WIDTH = 640; // BACKGROUND_ refers to the dimensions of the picture file that we use as the background for the gameworld
    private static final int BACKGROUND_HEIGHT = 180;
    private static final int CHARACTER_WIDTH = 10; // CHARACTER_ refers to the dimensions of the picture file we use to create our game character
    private static final int CHARACTER_HEIGHT = 18;
    static int cameraXPosition = 20; // This variable is used to decide where the "camera" is in the gameworld on the horizontal plane, i.e what pixels to show to the player
    private static String[][] background = new String[BACKGROUND_HEIGHT][BACKGROUND_WIDTH];
    private static int[][] backgroundCollisionMap = new int[BACKGROUND_HEIGHT][BACKGROUND_WIDTH];
    private static String[][] gameWorld = new String[BACKGROUND_HEIGHT][BACKGROUND_WIDTH];
    private static String[][] visibleGameWorld = new String[GAME_HEIGHT][GAME_WIDTH];
    private static String[][] character = new String[CHARACTER_HEIGHT][CHARACTER_WIDTH];

    public PixelCanvas() throws IOException {
        setBackgroundFromFile();
        setBackgroundCollisionMapFromFile();
        setCharacterFromFile();
        paintBackgroundToGameWorld();
        paintWorldToVisibleGameWorld();
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int j = 0, s = 0; j < GAME_HEIGHT; j += 1, s++) {
            for (int i = 0; i < GAME_WIDTH; i += 1, s++) {
                g.setColor(decodeHexWithAlpha(visibleGameWorld[j][i]));
                g.fillRect(i*3, j*3, 3, 3); // the number 3 here is to scale the graphics up, our picture file is only 180 pixels high but our gamewindow is 540 pixels high, so one gamepixel becomes 3 "real" pixels on the screen.
            }
        }
    }

    public void repaintPixelCanvas() {
        paintBackgroundToGameWorld();
        paintCharacterToGameWorld();
        paintWorldToVisibleGameWorld();
        moveCameraWithPlayer();
        repaint();
    }

    public void setBackgroundFromFile() throws IOException {
        File file = new File("images/backgroundtreeslong.png");
        BufferedImage image = ImageIO.read(file);
        for (int j = 0; j < BACKGROUND_WIDTH; j++) {
            for (int i = 0; i < BACKGROUND_HEIGHT; i++){
                int color = image.getRGB(j, i);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color >> 0) & 0xff;
                String hex = String.format("#%02x%02x%02x%02x", alpha, red, green, blue);
                background[i][j] = hex;
            }
        }
    }

    public void setBackgroundCollisionMapFromFile() throws IOException {
        File file = new File("images/backgroundcollisionmap.png");
        BufferedImage image = ImageIO.read(file);
        for (int j = 0; j < BACKGROUND_WIDTH; j++) {
            for (int i = 0; i < BACKGROUND_HEIGHT; i++){
                int color = image.getRGB(j, i);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color >> 0) & 0xff;
                String hex = String.format("#%02x%02x%02x%02x", alpha, red, green, blue);
                if (Objects.equals(hex,"#ffff00ff"))
                backgroundCollisionMap[i][j] = 1;
            }
        }
    }

    public void setCharacterFromFile() throws IOException {
        File file = new File("images/characterlila.png");
        BufferedImage image = ImageIO.read(file);
        for (int i = 0, s = 0; i < CHARACTER_HEIGHT; i++, s++) {
            for (int j = 0; j < CHARACTER_WIDTH; j++, s++) {
                int color = image.getRGB(j, i);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color >> 0) & 0xff;
                String hex = String.format("#%02x%02x%02x%02x", alpha, red, green, blue);
                character[i][j] = hex;
            }
        }
    }

    public void paintBackgroundToGameWorld() {
        for (int i = 0, s = 0; i< BACKGROUND_HEIGHT; i++, s++) {
            for (int j = 0; j < BACKGROUND_WIDTH; j++, s++) {
                gameWorld[i][j] = background[i][j];
            }
        }
    }

    public void paintCharacterToGameWorld() {
        for (int i = 0, y = (int) player.getRoundedPlayerYPos(); i < character.length; i++, y++) {
            for (int j = 0, x = (int) player.getRoundedPlayerXPos(); j < character[0].length; j++, x++) {
                if(!Objects.equals(character[i][j],"#ffff00ff"))
                    gameWorld[y][x] = character[i][j];
            }
        }
    }

    public void paintWorldToVisibleGameWorld() {
        for (int i = 0, y = 0; i < visibleGameWorld.length; i++, y++) {
            for (int j = 0, x = 0; j < visibleGameWorld[0].length; j++, x++) {
                    visibleGameWorld[y][x] = gameWorld[i][j+ cameraXPosition];
            }
        }
    }

    public void moveCameraWithPlayer() {
        if (player.getPlayerDistanceFromLeftBorder()>110)
            moveCameraRight();

        if (player.getPlayerDistanceFromLeftBorder()<50)
            moveCameraLeft();
    }

    private void moveCameraRight() {
        if ((cameraXPosition + 2) < 470)
        cameraXPosition += 2;
    }

    private void moveCameraLeft() {
        if ((cameraXPosition - 2) > 10)
        cameraXPosition -=2;
    }

    private Color decodeHexWithAlpha(String nm) throws NumberFormatException {
        Long intval = Long.decode(nm);
        Long i = intval.longValue();

        long la = (i >> 24) & 0xff;
        int a = (int) la;
        long lr = (i >> 16) & 0xFF;
        int r = (int) lr;
        long lg = (i >> 8) & 0xFF;
        int g = (int) lg;
        long lb = i & 0xFF;
        int b = (int) lb;

        return new Color(r, g, b, a);
    }

    public int getCollisionMapValue(int i, int j) {
        return backgroundCollisionMap[i][j];
    }

    public void injectPlayer(Player player) {
        this.player = player;
    }
}

