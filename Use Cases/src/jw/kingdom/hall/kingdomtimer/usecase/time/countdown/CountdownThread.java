package jw.kingdom.hall.kingdomtimer.usecase.time.countdown;

/**
 * All rights reserved & copyright ©
 */
class CountdownThread extends Thread {

    private boolean running = true;
    private Runnable task;

    CountdownThread(Runnable task) {
        this.task = task;
    }

    @Override
    public void run() {
        super.run();
        while (running) {
            try {
                sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            task.run();
        }
    }

    void stopCountdown(){
        running = false;
    }
}
