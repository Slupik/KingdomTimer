package jw.kingdom.hall.kingdomtimer.webui.domain.utils;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * All rights reserved & copyright ©
 */
public class Resource {

    public static String getContent(String path) throws IOException {
        ClassLoader classLoader = Resource.class.getClassLoader();
        File file = new File(classLoader.getResource(path).getFile());
        return new String(Files.readAllBytes(file.toPath()));
    }

    public static List<String> getResourceFiles(String path) throws IOException {
        List<String> filenames = new ArrayList<>();

        try (
            InputStream in = getResourceAsStream(path);
            BufferedReader br = new BufferedReader(new InputStreamReader(in))) {
            String resource;

            while ((resource = br.readLine()) != null) {
                filenames.add(resource);
            }
        }

        return filenames;
    }

    private static InputStream getResourceAsStream(String resource) {
        final InputStream in = getContextClassLoader().getResourceAsStream(resource);
        return in == null ? Resource.class.getResourceAsStream(resource) : in;
    }

    private static ClassLoader getContextClassLoader() {
        return Thread.currentThread().getContextClassLoader();
    }
}
