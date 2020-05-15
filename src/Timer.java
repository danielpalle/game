public class Timer {
    java.util.Timer timer = new java.util.Timer();
    int tickperiod = 10;
    GUI gui;
    int i = 0;

    public Timer(GUI gui){
        this.gui = gui;
    }
    public void tick() {
        java.util.TimerTask task = new java.util.TimerTask() {
            @Override
            public void run() {
                gui.setColorBackGround();
                gui.insertPlayerIntoColor();
                gui.renderScreen();
                i++;
            }
        };
        timer.schedule(task, tickperiod, tickperiod);
    }
}
