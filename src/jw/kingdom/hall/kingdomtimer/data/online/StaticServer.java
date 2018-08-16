package jw.kingdom.hall.kingdomtimer.data.online;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import jw.kingdom.hall.kingdomtimer.data.online.model.Meeting;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class StaticServer {
    public static List<Meeting> getMeetings(){
        String content = getPageContent();
        Type type = new TypeToken<List<Meeting>>() {}.getType();
        return new Gson().fromJson(content, type);
    }

    private static String getPageContent() {
        try {
            return WebPageUtils.download("https://soundbox.blob.core.windows.net/meeting-feeds/feed.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "[]";
    }
}
