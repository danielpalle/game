import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class GUI {
    PixelCanvas pixelCanvas;
    Player player;
    Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
    JFrame frame = new JFrame();
    JPanel rightBorder = new JPanel();

    public void injectPixelCanvasAndPlayer(PixelCanvas pixelcanvas, Player player) {
        this.pixelCanvas = pixelcanvas;
        this.player = player;
    }

    public void buildGameWindow() {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Game");
        frame.setResizable(false);
        frame.setFocusable(true);
        rightBorder.setPreferredSize(new Dimension(200, 540));
        rightBorder.setBackground(Color.lightGray);
        frame.add(rightBorder, BorderLayout.LINE_END);
        frame.add(pixelCanvas);
        pixelCanvas.setPreferredSize(new Dimension(480,540));
        frame.pack();
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setVisible(true);
        addKeyListenerToFrame();
        addMouseListenerToFrame();
    }

    private void addKeyListenerToFrame() {

        frame.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {

                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setPlayerXSpeed(1);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    if (player.isPlayerStandingOnCollisionSurface())
                    player.movePlayerJump();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setPlayerXSpeed(-1);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
                    player.setPlayerXSpeed(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT) {
                    player.setPlayerXSpeed(0);
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                }
            }
        });
    }

    private void addMouseListenerToFrame() {
        pixelCanvas.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                System.out.println((e.getX()/3) + PixelCanvas.cameraXPosition + "," + e.getY()/3);
            }
        });
    }
}
