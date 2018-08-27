package jw.kingdom.hall.kingdomtimer.recorder.utils;

import java.nio.ByteBuffer;

/**
 * All rights reserved & copyright ©
 */
public class ByteUtils {

    public static byte[] convertToByteArray(Object array) {
        byte[] arrayToSave = new byte[0];
        if (array instanceof byte[]) {
            arrayToSave = (byte[]) array;
        } else if (array instanceof float[]) {
            float[] data = (float[]) array;
            arrayToSave = FloatArray2ByteArray(data);
        }
        return arrayToSave;
    }

    public static byte[] FloatArray2ByteArray(float[] values){
        ByteBuffer buffer = ByteBuffer.allocate(4 * values.length);

        for (float value : values){
            buffer.putFloat(value);
        }

        return buffer.array();
    }

    public static byte [] long2ByteArray (long value)
    {
        return ByteBuffer.allocate(8).putLong(value).array();
    }

    public static byte [] float2ByteArray (float value)
    {
        return ByteBuffer.allocate(4).putFloat(value).array();
    }
}
