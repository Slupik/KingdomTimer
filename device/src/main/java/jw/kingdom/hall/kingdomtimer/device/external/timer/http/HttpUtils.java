package jw.kingdom.hall.kingdomtimer.device.external.timer.http;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import static com.google.common.net.HttpHeaders.USER_AGENT;

/**
 * All rights reserved & copyright ©
 */
public abstract class HttpUtils {

    private HttpUtils(){}

    public static String sendGet(String url) throws IOException {
        return sendGet(url, "KHT");
    }

    public static String sendGet(String url, String agent) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty(USER_AGENT, agent);

        int responseCode = con.getResponseCode();
        String response = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder responseBuilder = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            in.close();

            response = responseBuilder.toString();
        }
        return response;
    }

    public static String sendPost(String url, String postParams, String agent) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty(USER_AGENT, agent);

        // For POST only - START
        con.setDoOutput(true);
        OutputStream os = con.getOutputStream();
        os.write(postParams.getBytes());
        os.flush();
        os.close();
        // For POST only - END

        int responseCode = con.getResponseCode();

        String response = null;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuilder responseBuilder = new StringBuilder();

            while ((inputLine = in.readLine()) != null) {
                responseBuilder.append(inputLine);
            }
            in.close();

            response = responseBuilder.toString();
        }

        return response;
    }

}
