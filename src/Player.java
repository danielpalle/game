public class Player {
    PixelCanvas pixelcanvas;

    private double playerXPos = 110;
    private double playerYPos = 104;
    private double playerXSpeed = 0;

    // temporary testing method
    public void setPlayerYSpeed(double playerYSpeed) {
        this.playerYSpeed = playerYSpeed;
    }

    private double playerYSpeed = 0;

    public void movePlayer(int PlayerMovementsPerTick) {
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
        playerYSpeed = -1.5;
    }

    public double getRoundedPlayerXPos() {
        return (int) Math.round(playerXPos);
    }

    public double getRoundedPlayerYPos() {
        return (int) Math.round(playerYPos);
    }

    public int getPlayerDistanceFromLeftBorder() {
        return (int) (getRoundedPlayerXPos() - PixelCanvas.cameraXPosition);
    }

    public void setPlayerXSpeed(double playerXSpeed) {
        this.playerXSpeed = playerXSpeed;
    }

    public void calculatePlayerYSpeed() {
        if ((playerYSpeed <1))
            playerYSpeed = playerYSpeed + 0.025;
    }

    public boolean isPlayerStandingOnCollisionSurface() {
        return (pixelcanvas.getCollisionMapValue(((int) Math.round(playerYPos + playerYSpeed)+17),((int) playerXPos))) == 1;
    }

    private boolean isNextPlayerMovementOutOfBounds() {
        return ((playerXPos + playerXSpeed) < 13 || (playerXPos + playerXSpeed) >= 618);
    }

    public void injectPixelCanvas(PixelCanvas pixelcanvas) {
        this.pixelcanvas = pixelcanvas;
    }
}

