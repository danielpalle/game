public class Timer {
    java.util.Timer timer = new java.util.Timer();
    int tickperiod = 20;
    GameControl gamecontrol;

    public Timer(GameControl gamecontrol){
        this.gamecontrol = gamecontrol;
    }

    public void startTimer() {
       java.util.TimerTask task = new java.util.TimerTask() {
           @Override
           public void run() {
                gamecontrol.tick();
           }
       };
       timer.schedule(task, tickperiod, tickperiod);
    }

}
