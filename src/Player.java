public class Player {
    PixelCanvas pixelcanvas;
    static double playerxpos = 90;
    static double playerypos = 104;
    static double playerxspeed = 0;
    static double playeryspeed = 0;


    public void movePlayer(int numberOfPlayerMovementsPerTick){
        int i;
        for (i=0; i<numberOfPlayerMovementsPerTick; i++) {
        movePlayerHorizontal();
        movePlayerVertical();
        }
    }

    private void movePlayerHorizontal() {
        if (!isNextPlayerMovementOutOfBounds())
        playerxpos += playerxspeed;
    }

    private void movePlayerVertical() {
        calculatePlayerYSpeed();

        if (!isPlayerStandingOnCollisionSurface())
            playerypos += playeryspeed;
    }

    private boolean isPlayerStandingOnCollisionSurface() {
        return (pixelcanvas.getCollisionMapValue(((int) Math.round(playerypos + playeryspeed)+17),((int) playerxpos))) == 1;
    }

    private boolean isNextPlayerMovementOutOfBounds() {
        return ((playerxpos + playerxspeed) < 0 || (playerxpos + playerxspeed) >= 630);
    }

    public void movePlayerJump() {
        playeryspeed = -1;
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
        if ((playeryspeed<1))
            playeryspeed = playeryspeed + 0.02;
    }

    public void injectPixelCanvas(PixelCanvas pixelcanvas) {
        this.pixelcanvas = pixelcanvas;
    }
}

