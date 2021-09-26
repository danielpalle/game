public class Bomb {
    PixelCanvas pixelCanvas;

    private double xPosition;
    private double yPosition;
    private double xSpeed;
    private double ySpeed;

    public Bomb(double xPosition, double yPosition, double xSpeed, double ySpeed, PixelCanvas pixelCanvas) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
        this.pixelCanvas = pixelCanvas;
    }

    public void moveBomb(int BombMovementsPerTick) {
        for (int i=0; i<BombMovementsPerTick; i++) {
            moveBombHorizontal();
            moveBombVertical();
        }
    }

    private void moveBombHorizontal() {
        if (!isNextBombMovementOutOfBounds()) // this makes the bomb reverse its speed if it hits the edge of the map (i.e bounce on the edge)
            xPosition += xSpeed;

        else xSpeed = -xSpeed;
    }

    private void moveBombVertical() {
        calculateBombYSpeed();

        if (!isBombStandingOnCollisionSurface())
            yPosition += ySpeed;
    }

    public double getRoundedBombXPos() {
        return (int) Math.round(xPosition);
    }

    public double getRoundedBombYPos() {
        return (int) Math.round(yPosition);
    }

    private boolean isNextBombMovementOutOfBounds() {
        return ((xPosition + xSpeed) < 13 || (xPosition + xSpeed) >= 618);
    }

    public void calculateBombYSpeed() {
        if ((ySpeed <1))
            ySpeed = ySpeed + 0.025;
    }

    public boolean isBombStandingOnCollisionSurface() {
        return (pixelCanvas.getCollisionMapValue(((int) Math.round(yPosition + ySpeed)+7),((int) xPosition))) == 1;
    }
}
