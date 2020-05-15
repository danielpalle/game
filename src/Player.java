import java.awt.*;

public class Player {
    int col = 10;
    int row = 10;
    GUI gui;

    public Player(GUI gui){
        this.gui = gui;
    }


    public int getCol(){
        return col;
    }
    public int getRow(){
        return row;
    }

    public void movePlayerRight(){
        if (col+1 !=gui.pixels[0].length)
        col++;
    }

    public void movePlayerLeft(){
        if (col-1 != -1)
        col--;
    }

    public void movePlayerDown(){
        if (row+1 !=gui.pixels.length)
        row++;
    }

    public void movePlayerUp(){
        if (row-1 != -1)
        row--;
    }


}
