public class Timer {

    java.util.Timer timer = new java.util.Timer();
    GameControl gameControl;
    private static final int TICK_PERIOD = 15;

    public Timer(GameControl gameControl){
        this.gameControl = gameControl;
    }

    public void startTimer() {
       java.util.TimerTask task = new java.util.TimerTask() {
           @Override
           public void run() {
               gameControl.tick();
           }
       };
       timer.schedule(task, TICK_PERIOD, TICK_PERIOD);
    }

}
