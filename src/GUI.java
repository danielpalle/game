import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class GUI {
    PixelCanvas pixelcanvas;
    Player player;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame frame = new JFrame();
    JPanel rightborder = new JPanel();

    public void setPixelCanvasAndPlayer(PixelCanvas pixelcanvas, Player player){
        this.pixelcanvas = pixelcanvas;
        this.player = player;
    }

    public void buildGameWindow(){
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setFocusable(true);
        rightborder.setPreferredSize(new Dimension(200, 540));
        rightborder.setBackground(Color.lightGray);
        frame.add(rightborder, BorderLayout.LINE_END);
        frame.add(pixelcanvas);
        pixelcanvas.setPreferredSize(new Dimension(960,540));
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
        addKeyListenerToFrame();
    }

    private void addKeyListenerToFrame() {

        frame.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.playerxspeed = 2;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {

                    player.movePlayerJump();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.playerxspeed = -2;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.playerxspeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.playerxspeed = 0;
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                }
            }
        });
    }

    public void repaintPixelCanvas(){
        pixelcanvas.paintBackgroundToWorld();
        pixelcanvas.paintCharacterToWorld();
        pixelcanvas.paintWorldToScreen();
        pixelcanvas.moveCameraWithPlayer();
        pixelcanvas.repaint();
    }
}
