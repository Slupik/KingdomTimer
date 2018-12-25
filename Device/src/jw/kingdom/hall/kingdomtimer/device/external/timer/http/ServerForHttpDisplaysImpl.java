package jw.kingdom.hall.kingdomtimer.device.external.timer.http;

import jw.kingdom.hall.kingdomtimer.domain.task.TaskBean;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeController;
import jw.kingdom.hall.kingdomtimer.domain.time.TimeListenerProxy;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * All rights reserved & copyright ©
 */
public class ServerForHttpDisplaysImpl implements ServerForHttpDisplays {

    private final List<String> IPs = new ArrayList<>();
    private Executor executor = Executors.newFixedThreadPool(3);

    public ServerForHttpDisplaysImpl(TimeController controller) {
        controller.addListener(new TimeListenerProxy() {
            @Override
            public void onStart(TaskBean task) {
                super.onStart(task);
                if(task.isCountdownDown()) {
                    sendCommand("start,"+task.getTime()+",down");
                } else {
                    sendCommand("start,"+task.getTime()+",up");
                }
                task.addPropertyChangeListener(evt -> {
                    if("countdownDown".equalsIgnoreCase(evt.getPropertyName())) {
                        if(((Boolean) evt.getNewValue())) {
                            sendCommand("direct,down");
                        } else {
                            sendCommand("direct,up");
                        }
                    }
                });
            }

            @Override
            public void onPause() {
                super.onPause();
                sendCommand("pause");
            }

            @Override
            public void onResume() {
                super.onResume();
                sendCommand("start");
            }

            @Override
            public void onStop() {
                super.onStop();
                sendCommand("stop");
            }

            @Override
            public void onTimeEnforce(int enforced) {
                super.onTimeEnforce(enforced);
                sendCommand("start,"+enforced);
            }

            @Override
            public void onTimeAdded(int totalAdded, int added) {
                super.onTimeAdded(totalAdded, added);
                if(added>0) {
                    sendCommand("add,"+added);
                } else {
                    sendCommand("remove,"+Math.abs(added));
                }
            }
        });
        Runtime.getRuntime().addShutdownHook(new Thread(() -> sendCommand("stop")));
    }

    void sendCommand(final String command) {
        executor.execute(()->{
            for(String ip:IPs) {
                try {
                    if(!ip.startsWith("http")) {
                        ip = "http://"+ip;
                    }
                    HttpUtils.sendGet(ip+"/"+command);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void add(String ip) {
        IPs.add(ip);
    }

    @Override
    public void remove(String ip) {
        IPs.remove(ip);
    }
}
