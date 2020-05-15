public class Timer {
    java.util.Timer timer = new java.util.Timer();
    int tickperiod = 10;
    GUI gui;
    Player player;
    int i = 0;

    public Timer(GUI gui, Player player){
        this.gui = gui;
        this.player = player;
    }
    public void tick() {
        java.util.TimerTask task = new java.util.TimerTask() {
            @Override
            public void run() {
                gui.setColorBackGround();
                player.movePlayer();
                gui.insertPlayerIntoColor();
                gui.renderScreen();
                i++;
            }
        };
        timer.schedule(task, tickperiod, tickperiod);
    }
}
