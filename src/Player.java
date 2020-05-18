public class Player {
    PixelCanvas pixelcanvas;
    private double playerxpos = 155;
    private double playerypos = 114;
    static double playerxspeed = 0;
    private double playeryspeed = 0;


    public void movePlayer(){
        playerxpos += playerxspeed;

        if (!((pixelcanvas.getCollisionMapValue(((int) Math.round(playerypos + playeryspeed)+17),((int) Math.round(playerxpos)))) == 1))
            playerypos += playeryspeed;
        else if (!((pixelcanvas.getCollisionMapValue(((int) Math.round(playerypos + (playeryspeed/6))+17),((int) Math.round(playerxpos)))) == 1))
            playerypos += (playeryspeed/6);
    }

    public void movePlayerJump() {
        playeryspeed = -3;
    }

    public double getRoundedPlayerXPos(){
        int roundedplayerxpos = (int) Math.round(playerxpos);
        return roundedplayerxpos;
    }

    public double getRoundedPlayerYPos(){
        int roundedplayerypos = (int) Math.round(playerypos);
        return roundedplayerypos;
    }

    public void calculateYSpeed(){
        if (!(playeryspeed>2.5))
            playeryspeed = playeryspeed + 0.13;
    }

    public void setPixelCanvas(PixelCanvas pixelcanvas) {
        this.pixelcanvas = pixelcanvas;
    }
}

