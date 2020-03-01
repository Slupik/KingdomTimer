package jw.kingdom.hall.kingdomtimer.recorder.utils;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public class ByteUtils {

    public static byte[] convertToByteArray(Object array) {
        byte[] arrayToSave = new byte[0];
        if (array instanceof byte[]) {
            arrayToSave = (byte[]) array;
        } else if (array instanceof float[]) {
            float[] data = (float[]) array;
            arrayToSave = convertFloatArray2ByteArray(data);
        } else if (array instanceof short[]) {
            short[] data = (short[]) array;
            arrayToSave = convertShortArray2ByteArray(data);
        }

        return arrayToSave;
    }

    public static byte[] convertFloatArray2ByteArray(float[] values){
        ByteBuffer buffer = ByteBuffer.allocate(4 * values.length).order(ByteOrder.LITTLE_ENDIAN);

        for (float value : values){
            buffer.putFloat(value);
        }

        return buffer.array();
    }

    public static byte[] convertShortArray2ByteArray(short[] values){
        ByteBuffer buffer = ByteBuffer.allocate(2 * values.length).order(ByteOrder.LITTLE_ENDIAN);

        for (short value : values){
            buffer.putShort(value);
        }

        return buffer.array();
    }

}
