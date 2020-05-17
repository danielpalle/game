import java.util.Objects;

public class Player {
    private int playerxpos = 155;
    private int playerypos = 124;

    Boolean moveright = false;
    Boolean moveup = false;
    Boolean moveleft = false;
    Boolean movedown = false;

    public Player(){
    }


    public void movePlayer(){
        if (Objects.equals(getMoveDirection(),"right"))
            movePlayerRight();
        else if (Objects.equals(getMoveDirection(),"left"))
            movePlayerLeft();
        else if (Objects.equals(getMoveDirection(),"up"))
            movePlayerUp();
        else if (Objects.equals(getMoveDirection(),"down"))
            movePlayerDown();
    }

    public String getMoveDirection(){
        String movedirection = null;

        if (moveright) {
            movedirection = "right";}
        else if (moveleft)
            movedirection = "left";
        else if (moveup)
            movedirection = "up";
        else if (movedown)
            movedirection = "down";

        return movedirection;
    }

    public void movePlayerRight(){
        if (!(playerxpos + 2 >= 630))
        playerxpos += 2;
    }

    public void movePlayerLeft(){
        if (!(playerxpos - 2 < 0))
        playerxpos -= 2;
    }

    public void movePlayerDown(){
    }

    public void movePlayerUp() {
    }

    public int getPlayerXPos(){
        return playerxpos;
    }

    public int getPlayerYPos(){
        return playerypos;
    }
}

