public class Player {
    PixelCanvas pixelcanvas;

    private double playerXPos = 90;
    private double playerYPos = 104;
    private double playerXSpeed = 0;
    private double playerYSpeed = 0;

    public void movePlayer(int PlayerMovementsPerTick){
        for (int i=0; i<PlayerMovementsPerTick; i++) {
        movePlayerHorizontal();
        movePlayerVertical();
        }
    }

    private void movePlayerHorizontal() {
        if (!isNextPlayerMovementOutOfBounds())
        playerXPos += playerXSpeed;
    }

    private void movePlayerVertical() {
        calculatePlayerYSpeed();

        if (!isPlayerStandingOnCollisionSurface())
            playerYPos += playerYSpeed;
    }

    public void movePlayerJump() {
        playerYSpeed = -1;
    }

    public double getRoundedPlayerXPos(){
        return (int) Math.round(playerXPos);
    }

    public double getRoundedPlayerYPos(){
        return (int) Math.round(playerYPos);
    }

    public int getPlayerDistanceFromLeftBorder(){
        return (int) (getRoundedPlayerXPos() - PixelCanvas.cameraXPosition);
    }

    public void setPlayerXSpeed(double playerxspeed) {
        this.playerXSpeed = playerxspeed;
    }

    public void calculatePlayerYSpeed(){
        if ((playerYSpeed <1))
            playerYSpeed = playerYSpeed + 0.02;
    }

    public boolean isPlayerStandingOnCollisionSurface() {
        return (pixelcanvas.getCollisionMapValue(((int) Math.round(playerYPos + playerYSpeed)+17),((int) playerXPos))) == 1;
    }

    private boolean isNextPlayerMovementOutOfBounds() {
        return ((playerXPos + playerXSpeed) < 0 || (playerXPos + playerXSpeed) >= 630);
    }

    public void injectPixelCanvas(PixelCanvas pixelcanvas) {
        this.pixelcanvas = pixelcanvas;
    }
}

