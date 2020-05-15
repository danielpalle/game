import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class GUI {
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame frame = new JFrame();
    JPanel rightborder = new JPanel();
    JPanel gamearea = new JPanel();
    GamePixel[][] pixels = new GamePixel[18][32];
    Player player = new Player(this);
    String[][] pixelcolor = new String[18][32];
    Boolean moveright = false;
    Boolean moveup = false;
    Boolean moveleft = false;
    Boolean movedown = false;


    public GUI() {
        setColorBackGround();
        buildGameWindow();
        Timer timer = new Timer(this, player);
        timer.tick();
    }

    public void setColorBackGround(){
        for (int i = 0; i< pixelcolor.length; i++) {
            for (int j = 0; j < pixelcolor[i].length; j++) {
                pixelcolor[i][j]="#ff7070";
            }
        }
    }

    private void buildGameWindow(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Virus game");
        gamearea.setBackground(Color.black);
        gamearea.setLayout(new GridLayout(18,32));
        frame.add(gamearea, BorderLayout.LINE_START);
        frame.add(gamearea);
        rightborder.setPreferredSize(new Dimension(200, 540));
        rightborder.setBackground(Color.lightGray);
        frame.add(rightborder, BorderLayout.LINE_END);
        frame.setVisible(true);
        createGamePixels();
        pixels[0][0].setBackground(Color.green);
        pixels[0][31].setBackground(Color.green);
        pixels[17][0].setBackground(Color.green);
        pixels[17][31].setBackground(Color.green);
        frame.pack();
        frame.setVisible(true);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        renderScreen();
        addKeyListenerToFrame();
    }

    private void addKeyListenerToFrame() {

        frame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                //    player.movePlayerRight();
                //}
                //if (e.getKeyCode() == KeyEvent.VK_UP) {
                //    player.movePlayerUp();
                //}
                //if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                //    player.movePlayerLeft();
                //}
                //if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                //    player.movePlayerDown();
                //}

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveright = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveup = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveleft = true;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    movedown = true;
                }


            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    moveright = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    moveup = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    moveleft = false;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    movedown = false;
                }
            }
        });
    }

    public void createGamePixels() {
        for (int i = 0; i< pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j] = new GamePixel(i, j);
                gamearea.add(pixels[i][j]);
                pixels[i][j].setBackground(Color.black);
            }
        }
        frame.setVisible(true);
    }

    public void renderScreen() {
        for (int i = 0; i< pixels.length; i++) {
            for (int j = 0; j < pixels[i].length; j++) {
                pixels[i][j].setBackground(Color.decode(pixelcolor[i][j]));
            }
        }
        frame.setVisible(true);
    }

    public void insertPlayerIntoColor(){
        pixelcolor[player.getHeadRow()][player.getHeadCol()]=("#6365ff");
        pixelcolor[player.getFootRow()][player.getFootCol()]=("#6365ff");

    }

    public String getMoveDirection(){
        String movedirection = null;

        if (moveright)
            movedirection = "right";
        else if (moveleft)
            movedirection = "left";
        else if (moveup)
            movedirection = "up";
        else if (movedown)
            movedirection = "down";

        return movedirection;
    }
}
