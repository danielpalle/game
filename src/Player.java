import java.util.Objects;

public class Player {
    int headcol = 10;
    int headrow = 9;
    int footcol = 10;
    int footrow = 10;
    GUI gui;

    public Player(GUI gui){
        this.gui = gui;
    }

    public int getHeadCol(){
        return headcol;
    }

    public int getFootCol(){
        return footcol;
    }

    public int getHeadRow(){
        return headrow;
    }

    public int getFootRow(){
        return footrow;
    }

    public void movePlayer(){
        if (Objects.equals(gui.getMoveDirection(),"right"))
            movePlayerRight();
        else if (Objects.equals(gui.getMoveDirection(),"left"))
            movePlayerLeft();
        else if (Objects.equals(gui.getMoveDirection(),"up"))
            movePlayerUp();
        else if (Objects.equals(gui.getMoveDirection(),"down"))
            movePlayerDown();
    }

    public void movePlayerRight(){
        if (headcol +1 !=gui.pixels[0].length) {
            headcol++;
            footcol++;
        }
    }

    public void movePlayerLeft(){
        if (headcol -1 != -1) {
            headcol--;
            footcol--;
        }
    }

    public void movePlayerDown(){
        if (footrow +1 !=gui.pixels.length) {
            footrow++;
            headrow++;
        }

    }

    public void movePlayerUp() {
        if (headrow - 1 != -1) {
            headrow--;
            footrow--;
        }
    }
}

