package jw.kingdom.hall.kingdomtimer.recorder.xt;

/**
 * All rights reserved & copyright ©
 */
public class RecordTest {
    static XtRecorder recorder;

    public static void main(String[] args) {
        new Thread(()->{
            recorder = new XtRecorder();
            recorder.onStart();
            sleep(5000);
            recorder.onStop();
            System.out.println("EXIT");
        }).start();
        System.out.println("END MAIN");
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
