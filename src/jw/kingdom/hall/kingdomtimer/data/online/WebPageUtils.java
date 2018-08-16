package jw.kingdom.hall.kingdomtimer.data.online;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * All rights reserved & copyright ©
 */
public class WebPageUtils {
    public static String download(final String url) throws IOException {
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
