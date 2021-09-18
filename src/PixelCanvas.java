import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PixelCanvas extends JPanel {

    Player player;

    private final int GAME_PIXEL_WIDTH = 180; // GAME_PIXEL dimensions refers to how many pixels we see when playing the game, at 180 only a quarter of the gameworld is visible at once
    private final int GAME_PIXEL_HEIGHT = 180;
    private final int BACKGROUND_PIXEL_WIDTH = 640; // BACKGROUND_PIXEL refers to the dimensions of the picture file that we use as the background for the gameworld
    private final int BACKGROUND_PIXEL_HEIGHT = 180;
    private final int CHARACTER_PIXEL_WIDTH = 10; // CHARACTER_PIXEL refers to the dimensions of the picture file we use to create our game character
    private final int CHARACTER_PIXEL_HEIGHT = 18;
    private int cameraXPosition = 0; // This variable is used to decide where the "camera" is in the gameworld on the horizontal plane, i.e what pixels to show to the player
    private String[][] BACKGROUND = new String[BACKGROUND_PIXEL_HEIGHT][BACKGROUND_PIXEL_WIDTH];
    private int[][] BACKGROUND_COLLISION_MAP = new int[BACKGROUND_PIXEL_HEIGHT][BACKGROUND_PIXEL_WIDTH];
    private String[][] world = new String[BACKGROUND_PIXEL_HEIGHT][BACKGROUND_PIXEL_WIDTH];
    private String[][] gameScreen = new String[GAME_PIXEL_HEIGHT][GAME_PIXEL_WIDTH];
    private String[][] character = new String[CHARACTER_PIXEL_HEIGHT][CHARACTER_PIXEL_WIDTH];

    public PixelCanvas() throws IOException {
        setBackgroundFromFile();
        setBackgroundCollisionMapFromFile();
        setCharacterFromFile();
        paintBackgroundToWorld();
        paintWorldToScreen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        for (int j = 0, s = 0; j < GAME_PIXEL_HEIGHT; j += 1, s++) {
            for (int i = 0; i < GAME_PIXEL_WIDTH; i += 1, s++) {
                g.setColor(decodeHexWithAlpha(gameScreen[j][i]));
                g.fillRect(i*3, j*3, 3, 3); // the number 3 here is to scale the graphics up, our picture file is only 180 pixels high but our gamewindow is 540 pixels high, so one gamepixel becomes 3 "real" pixels on the screen.
            }
        }
    }

    public void setBackgroundFromFile() throws IOException {
        File file = new File("images/backgroundtreeslong.png");
        BufferedImage image = ImageIO.read(file);
        for (int j = 0; j < BACKGROUND_PIXEL_WIDTH; j++) {
            for (int i = 0; i < BACKGROUND_PIXEL_HEIGHT; i++){
                int color = image.getRGB(j, i);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color >> 0) & 0xff;
                String hex = String.format("#%02x%02x%02x%02x", alpha, red, green, blue);
                BACKGROUND[i][j] = hex;
            }
        }
    }

    public void setBackgroundCollisionMapFromFile() throws IOException {
        File file = new File("images/backgroundcollisionmap.png");
        BufferedImage image = ImageIO.read(file);
        for (int j = 0; j < BACKGROUND_PIXEL_WIDTH; j++) {
            for (int i = 0; i < BACKGROUND_PIXEL_HEIGHT; i++){
                int color = image.getRGB(j, i);
                int alpha = (color >> 24) & 0xff;
                int red = (color >> 16) & 0xff;
                int green = (color >> 8) & 0xff;
                int blue = (color >> 0) & 0xff;
                String hex = String.format("#%02x%02x%02x%02x", alpha, red, green, blue);
                if (Objects.equals(hex,"#ffff00ff"))
                BACKGROUND_COLLISION_MAP[i][j] = 1;
            }
        }
    }

    public void setCharacterFromFile() throws IOException {
        File file = new File("images/characterlila.png");
        BufferedImage image = ImageIO.read(file);
        for (int i = 0, s = 0; i < character.length; i++, s++) {
            for (int j = 0; j < character[0].length; j++, s++) {
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

    public void paintBackgroundToWorld() {
        for (int i = 0, s = 0; i< BACKGROUND_PIXEL_HEIGHT; i++, s++) {
            for (int j = 0; j < BACKGROUND_PIXEL_WIDTH; j++, s++) {
                world[i][j] = BACKGROUND[i][j];
            }
        }
    }

    public void paintCharacterToWorld() {
        for (int i = 0, y = (int) player.getRoundedPlayerYPos(); i < character.length; i++, y++) {
            for (int j = 0, x = (int) player.getRoundedPlayerXPos(); j < character[0].length; j++, x++) {
                if(!Objects.equals(character[i][j],"#ffff00ff"))
                    world[y][x] = character[i][j];
            }
        }
    }

    public void paintWorldToScreen(){
        for (int i = 0, y = 0; i < gameScreen.length; i++, y++) {
            for (int j = 0, x = 0; j < gameScreen[0].length; j++, x++) {
                    gameScreen[y][x] = world[i][j+ cameraXPosition];
            }
        }
    }

    public int getPlayerDistanceFromLeftBorder(){
        return (int) (player.getRoundedPlayerXPos()- cameraXPosition);
    }

    public void moveCameraWithPlayer(){
        if (getPlayerDistanceFromLeftBorder()>120)
            moveCameraRight();

        if (getPlayerDistanceFromLeftBorder()<60)
            moveCameraLeft();
    }

    private void moveCameraRight(){
        if ((cameraXPosition + 2 < 460))
        cameraXPosition +=2;
    }

    private void moveCameraLeft(){
        if ((cameraXPosition - 2 > 0))
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

    public int getCollisionMapValue(int i, int j){
        return BACKGROUND_COLLISION_MAP[i][j];
    }

    public void injectPlayer(Player player){
        this.player = player;
    }
}

