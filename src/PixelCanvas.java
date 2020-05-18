import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class PixelCanvas extends JPanel {
    GUI gui;
    Player player;
    private final int REALWIDTH = 960;
    private final int REALHEIGHT = 540;
    private final int gamepixelwidth = 320;
    private final int gamepixelheight = 180;
    private final int backgroundpixelwidth = 640;
    private final int backgroundpixelheight = 180;
    private int worldxposition = 0;
    String[][] background = new String[backgroundpixelheight][backgroundpixelwidth];
    String[][] world = new String[backgroundpixelheight][backgroundpixelwidth];
    String[][] gamescreen = new String[gamepixelheight][gamepixelwidth];
    String[][] character = new String[18][10];


    public PixelCanvas(GUI gui, Player player) throws IOException {
        this.gui = gui;
        this.player = player;
        setBackgroundFromFile();
        setCharacterFromFile();
        paintBackgroundToWorld();
        paintWorldToScreen();
    }

    @Override
    public void paintComponent(Graphics g) {
        //long start = System.currentTimeMillis();
        //long elapsedTimeMillis = System.currentTimeMillis()-start;
        //System.out.println(elapsedTimeMillis);
        super.paintComponent(g);
        for (int j = 0, s = 0; j < gamepixelheight; j+=1, s++) {
            for (int i = 0; i < gamepixelwidth; i += 1, s++) {
                g.setColor(decodeHexWithAlpha(gamescreen[j][i]));
                g.fillRect(i*(REALWIDTH /gamepixelwidth), j*(REALHEIGHT /gamepixelheight), REALWIDTH / 32, REALHEIGHT / 18);
            }
        }
    }

    public void setBackgroundFromFile() throws IOException {
        File file = new File("images/backgroundtreeslong.png");
        BufferedImage image = ImageIO.read(file);
        for (int j = 0; j < backgroundpixelwidth; j++) {
            for (int i = 0; i < backgroundpixelheight; i++){
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
        for (int i = 0, s = 0; i< backgroundpixelheight; i++, s++) {
            for (int j = 0; j < backgroundpixelwidth; j++, s++) {
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
        int playerdistancefromleftborder= (int) (player.getRoundedPlayerXPos()-worldxposition);
        return playerdistancefromleftborder;
    }

    public void moveCameraWithPlayer(){
        if (getPlayerDistanceFromLeftBorder()>220)
            moveCameraRight();

        if (getPlayerDistanceFromLeftBorder()<100)
            moveCameraLeft();
    }

    private void moveCameraRight(){
        if (!(worldxposition + 2 > 320))
        worldxposition+=2;
    }

    private void moveCameraLeft(){
        if (!(worldxposition - 2 < 0))
        worldxposition-=2;
    }

    public static Color decodeHexWithAlpha(String nm) throws NumberFormatException {
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
}

