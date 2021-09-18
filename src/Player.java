public class Player {
    PixelCanvas pixelcanvas;

    static double playerxpos = 90;
    static double playerypos = 104;
    static double playerxspeed = 0;
    static double playeryspeed = 0;

    public void movePlayer(int PlayerMovementsPerTick){
        for (int i=0; i<PlayerMovementsPerTick; i++) {
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

    public void movePlayerJump() {
        playeryspeed = -1;
    }

    public double getRoundedPlayerXPos(){
        return (int) Math.round(playerxpos);
    }

    public double getRoundedPlayerYPos(){
        return (int) Math.round(playerypos);
    }

    public int getPlayerDistanceFromLeftBorder(){
        return (int) (getRoundedPlayerXPos() - PixelCanvas.cameraXPosition);
    }

    public void calculatePlayerYSpeed(){
        if ((playeryspeed<1))
            playeryspeed = playeryspeed + 0.02;
    }

    private boolean isPlayerStandingOnCollisionSurface() {
        return (pixelcanvas.getCollisionMapValue(((int) Math.round(playerypos + playeryspeed)+17),((int) playerxpos))) == 1;
    }

    private boolean isNextPlayerMovementOutOfBounds() {
        return ((playerxpos + playerxspeed) < 0 || (playerxpos + playerxspeed) >= 630);
    }

    public void injectPixelCanvas(PixelCanvas pixelcanvas) {
        this.pixelcanvas = pixelcanvas;
    }
}

