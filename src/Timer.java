public class Timer {
    java.util.Timer timer = new java.util.Timer();

    public Timer(GameControl gameControl){
        this.gameControl = gameControl;
    }

    int tickPeriod = 20;
    GameControl gameControl;

    public void startTimer() {
       java.util.TimerTask task = new java.util.TimerTask() {
           @Override
           public void run() {
                gameControl.tick();
           }
       };
       timer.schedule(task, tickPeriod, tickPeriod);
    }

}
