import javax.swing.*;
import java.awt.*;

class GamePixel extends JPanel {
    int row, col;

    public GamePixel(int i, int j) {
        row = i;
        col = j;
        setPreferredSize(new Dimension(30,30));
    }
}

