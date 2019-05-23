/*
 * Created 05.09.18 02:49.
 * Last modified 03.09.18 21:14
 * This file is part of KingdomHallTimer which is released under "no licence".
 */

package jw.kingdom.hall.kingdomtimer.downloader.model.soundbox.schedule.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
class WebPageUtils {
    static String download(final String url) throws IOException {
        StringBuilder all = new StringBuilder();
        BufferedReader in = null;
        try {
            URL myUrl = new URL(url);
            in = new BufferedReader(new InputStreamReader(myUrl.openStream()));
            String line;
            while ((line = in.readLine()) != null) {
                all.append(line);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }

        return all.toString();
    }
}
