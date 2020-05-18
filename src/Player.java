public class Player {
    private double playerxpos = 155;
    private double playerypos = 114;
    static double playerxspeed = 0;
    private double playeryspeed = 0;


    public void movePlayer(){
        playerxpos += playerxspeed;

        if (!((playerypos + playeryspeed)>124))
        playerypos += playeryspeed;
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
}

