package jw.kingdom.hall.kingdomtimer.recorder.xt;

import jw.kingdom.hall.kingdomtimer.recorder.common.settings.DefaultAudioSettingsBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;

/**
 * All rights reserved & copyright ©
 */
public class RecordTest {
    static XtRecorder recorder;

    public static void main(String[] args) {
        new Thread(()->{
            recorder = new XtRecorder(new DefaultAudioSettingsBean());
            recorder.onStart();
            sleep(5000);
            recorder.onStop();
            System.out.println("EXIT");
            System.exit(0);
        }).start();
        System.out.println("END MAIN");
//        try {
//
//            byte[] content = Files.readAllBytes(new File("M1F1-float32-AFsp.wav").toPath());//sample clean video from web
////            for(int i=0;i<content.length;i++) {
//            System.out.println("size: "+content[16]);
//            System.out.println("type: "+content[20]);
//            System.out.println("format: "+content[34]);
//            System.out.println("format 0: "+content[35]);
//            System.out.println("36: "+content[36]);
//            System.out.println("37: "+content[37]);
//            for(int i=0;i<100;i++) {
//                byte value = content[i];
//                System.out.println(i+": "+(char)value + "("+value+")");
////                if(value == (byte)'d') {
////                    System.out.println("i = " + i);
////                }
//            }
//            byte[] bts = new byte[4];
//            bts[0] = content[46];
//            bts[1] = content[47];
//            bts[2] = content[48];
//            bts[3] = content[49];
////            ByteBuffer buffer = ByteBuffer.wrap(float2ByteArray(4534543));
//            ByteBuffer buffer = ByteBuffer.wrap(bts);
//            float second = buffer.getFloat();
//            System.out.println("second = " + second);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
    }

    public static byte [] float2ByteArray (float value)
    {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }

    public static void sleep(int time) {
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
