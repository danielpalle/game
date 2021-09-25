public class Bomb {

    private double xPosition;
    private double yPosition;
    private double xSpeed;
    private double ySpeed;

    public Bomb(double xPosition, double yPosition, double xSpeed, double ySpeed) {
        this.xPosition = xPosition;
        this.yPosition = yPosition;
        this.xSpeed = xSpeed;
        this.ySpeed = ySpeed;
    }

    public void moveBomb(int BombMovementsPerTick) {
        for (int i=0; i<BombMovementsPerTick; i++) {
            moveBombHorizontal();
            //moveBombVertical();
        }
    }

    private void moveBombHorizontal() {
        if (!isNextBombMovementOutOfBounds()) // this makes the bomb reverse its speed once it hits the edge of the map (i.e bounce on the edge)
            xPosition += xSpeed;

        else xSpeed = -xSpeed;
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

}
