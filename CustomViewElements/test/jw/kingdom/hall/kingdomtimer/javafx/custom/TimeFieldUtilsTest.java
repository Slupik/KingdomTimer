package jw.kingdom.hall.kingdomtimer.javafx.custom;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * All rights reserved & copyright ©
 */
public class TimeFieldUtilsTest {

    @Test
    public void getFormattedText() {
        assertEquals("00:00:00", TimeFieldUtils.getFormattedText("00:00:00"));
        assertEquals("43:02:43", TimeFieldUtils.getFormattedText("43:020:43"));
        assertEquals("43:02:43", TimeFieldUtils.getFormattedText("4$^3:02gbe0000:4gfsd3"));
        assertEquals("43:02:43", TimeFieldUtils.getFormattedText("43:2000:43"));
    }

    @Test
    public void getFormattedElement() {
        assertEquals("23", TimeFieldUtils.getFormattedElement("23"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("230"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("023"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("0230"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("23a"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("2a3"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("2a30"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("0000ada2a30gfr000"));
        assertEquals("23", TimeFieldUtils.getFormattedElement("234"));
    }

    @Test
    public void removeNonDigitChars() {
        assertEquals("2340000", TimeFieldUtils.removeNonDigitChars("2gef3few4gas0^^#&*^*#@#00/.,//[0"));
    }

    @Test
    public void trimZeros() {
        assertEquals("234", TimeFieldUtils.trimZeros("2340000"));
        assertEquals("234", TimeFieldUtils.trimZeros("0000234"));
        assertEquals("234", TimeFieldUtils.trimZeros("0000234000"));
        assertEquals("2304", TimeFieldUtils.trimZeros("00002304000"));
    }

    @Test
    public void trimZeroOnRight() {
        assertEquals("234", TimeFieldUtils.trimZeroOnRight("2340000"));
        assertEquals("000234", TimeFieldUtils.trimZeroOnRight("0002340000"));
        assertEquals("0002034", TimeFieldUtils.trimZeroOnRight("00020340000"));
    }

    @Test
    public void trimZeroOnLeft() {
        assertEquals("234", TimeFieldUtils.trimZeroOnLeft("0000234"));
        assertEquals("23400", TimeFieldUtils.trimZeroOnLeft("000023400"));
        assertEquals("230400", TimeFieldUtils.trimZeroOnLeft("0000230400"));
    }

    @Test
    public void isTextNormal() {
        assertEquals(true, TimeFieldUtils.isTextNormal("00:00:00"));
        assertEquals(true, TimeFieldUtils.isTextNormal("00:01:00"));
        assertEquals(true, TimeFieldUtils.isTextNormal("43:23:43"));
        assertEquals(true, TimeFieldUtils.isTextNormal("43:23:4"));
        assertEquals(false, TimeFieldUtils.isTextNormal("23:24:"));
        assertEquals(false, TimeFieldUtils.isTextNormal("23:24:423"));
        assertEquals(false, TimeFieldUtils.isTextNormal("23:234:42"));
        assertEquals(false, TimeFieldUtils.isTextNormal("233:24:42"));
    }

    @Test
    public void getAllSeconds() {
        int time = 12*60*60+23*60+41;
        String asText = Integer.toString(time/(60*60))+":"+
                        Integer.toString((time%3600)/(60))+":"+
                        Integer.toString(time%(60));
        int returnTime = TimeFieldUtils.getAllSeconds(asText);
        Assert.assertEquals(time, returnTime);
    }

    @Test
    public void isNormalInt() {
        Assert.assertEquals(true, TimeFieldUtils.isNormalInt("0"));
        Assert.assertEquals(true, TimeFieldUtils.isNormalInt("01"));
        Assert.assertEquals(true, TimeFieldUtils.isNormalInt("010"));
        Assert.assertEquals(true, TimeFieldUtils.isNormalInt("-10"));
        Assert.assertEquals(true, TimeFieldUtils.isNormalInt("-010"));

        Assert.assertEquals(false, TimeFieldUtils.isNormalInt("0.1"));//double
        Assert.assertEquals(false, TimeFieldUtils.isNormalInt("0,1"));// , instead of .
        Assert.assertEquals(false, TimeFieldUtils.isNormalInt("0a10"));//wrong character
    }
}