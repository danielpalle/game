public class Player {
    PixelCanvas pixelcanvas;
    static double playerxpos = 90;
    static double playerypos = 104;
    static double playerxspeed = 0;
    static double playeryspeed = 0;


    public void movePlayer(){
        playerxpos += playerxspeed;

        calculatePlayerYSpeed();

        if (((pixelcanvas.getCollisionMapValue(((int) Math.round(playerypos + playeryspeed)+17),((int) playerxpos))) != 1))
            playerypos += playeryspeed;
        else if (((pixelcanvas.getCollisionMapValue(((int) Math.round(playerypos + (playeryspeed/6))+17),((int) playerxpos))) != 1))
            playerypos += (playeryspeed/6);
    }

    public void movePlayerJump() {
        playeryspeed = -4;
    }

    public double getRoundedPlayerXPos(){
        int roundedplayerxpos = (int) Math.round(playerxpos);
        return roundedplayerxpos;
    }

    public double getRoundedPlayerYPos(){
        int roundedplayerypos = (int) Math.round(playerypos);
        return roundedplayerypos;
    }

    public void calculatePlayerYSpeed(){
        if (!(playeryspeed>2.5))
            playeryspeed = playeryspeed + 0.25;
    }

    public void injectPixelCanvas(PixelCanvas pixelcanvas) {
        this.pixelcanvas = pixelcanvas;
    }
}

