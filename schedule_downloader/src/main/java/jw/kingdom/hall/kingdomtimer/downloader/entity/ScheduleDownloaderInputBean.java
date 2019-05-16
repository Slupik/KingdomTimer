/*
 * Created 05.09.18 03:41.
 * Last modified 05.09.18 03:41
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.entity;

public class ScheduleDownloaderInputBean implements ScheduleDownloader.InputData {
    private boolean circuitVisit = false;
    private String langCode = "en";
    private String destUrl = "";
    private ScheduleTranslator translator = new DefaultScheduleTranslator();
    private int timeToEvaluate = -1;

    public void setCircuitVisit(boolean circuitVisit) {
        this.circuitVisit = circuitVisit;
    }

    public void setLangCode(String langCode) {
        this.langCode = langCode;
    }

    public void setDestUrl(String destUrl) {
        this.destUrl = destUrl;
    }

    public void setTranslator(ScheduleTranslator translator) {
        this.translator = translator;
    }

    public void setTimeToEvaluate(int seconds) {
        this.timeToEvaluate = seconds;
    }

    @Override
    public boolean isCircuitVisit() {
        return circuitVisit;
    }

    @Override
    public String getLangCode() {
        return langCode;
    }

    @Override
    public String getDestUrl() {
        return destUrl;
    }

    @Override
    public ScheduleTranslator getTranslator() {
        return translator;
    }

    @Override
    public int getTimeToEvaluate() {
        return timeToEvaluate;
    }

}
