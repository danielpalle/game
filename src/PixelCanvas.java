import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PixelCanvas extends JPanel {

    Player player;

    private static final int GAME_PIXEL_WIDTH = 180;
    private static final int GAME_PIXEL_HEIGHT = 180;
    private static final int BACKGROUND_PIXEL_WIDTH = 640;
    private static final int BACKGROUND_PIXEL_HEIGHT = 180;
    private int worldxposition = 0;
    private String[][] background = new String[BACKGROUND_PIXEL_HEIGHT][BACKGROUND_PIXEL_WIDTH];
    int[][] backgroundcollisionmap = new int[BACKGROUND_PIXEL_HEIGHT][BACKGROUND_PIXEL_WIDTH];
    String[][] world = new String[BACKGROUND_PIXEL_HEIGHT][BACKGROUND_PIXEL_WIDTH];
    String[][] gamescreen = new String[GAME_PIXEL_HEIGHT][GAME_PIXEL_WIDTH];
    String[][] character = new String[18][10];

    public PixelCanvas() throws IOException {
        setBackgroundFromFile();
        setBackgroundCollisionMapFromFile();
        setCharacterFromFile();
        paintBackgroundToWorld();
        paintWorldToScreen();
    }

    @Override
    protected void paintComponent(Graphics g) {
        //long start = System.currentTimeMillis();
        //long elapsedTimeMillis = System.currentTimeMillis()-start;
        //System.out.println(elapsedTimeMillis);
        super.paintComponent(g);
        for (int j = 0, s = 0; j < GAME_PIXEL_HEIGHT; j += 1, s++) {
            for (int i = 0; i < GAME_PIXEL_WIDTH; i += 1, s++) {
                g.setColor(decodeHexWithAlpha(gamescreen[j][i]));
                int realWidth = 540;
                int realHeight = 540;
                g.fillRect(i*(realWidth / GAME_PIXEL_WIDTH), j*(realHeight / GAME_PIXEL_HEIGHT), realWidth / 64, realHeight / 18);
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
                background[i][j] = hex;
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
                backgroundcollisionmap[i][j] = 1;
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
                world[i][j] = background[i][j];
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
        for (int i = 0, y = 0; i < gamescreen.length; i++, y++) {
            for (int j = 0, x = 0; j < gamescreen[0].length; j++, x++) {
                    gamescreen[y][x] = world[i][j+worldxposition];
            }
        }
    }

    public int getPlayerDistanceFromLeftBorder(){
        return (int) (player.getRoundedPlayerXPos()-worldxposition);
    }

    public void moveCameraWithPlayer(){
        if (getPlayerDistanceFromLeftBorder()>120)
            moveCameraRight();

        if (getPlayerDistanceFromLeftBorder()<60)
            moveCameraLeft();
    }

    private void moveCameraRight(){
        if ((worldxposition + 2 < 460))
        worldxposition+=2;
    }

    private void moveCameraLeft(){
        if ((worldxposition - 2 > 0))
        worldxposition-=2;
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
        return backgroundcollisionmap[i][j];
    }

    public void injectPlayer(Player player){
        this.player = player;
    }
}

