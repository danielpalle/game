public class Timer {

    java.util.Timer timer = new java.util.Timer();
    GameControl gameControl;
    final int tickPeriod = 15;

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
       timer.schedule(task, tickPeriod, tickPeriod);
    }

}
