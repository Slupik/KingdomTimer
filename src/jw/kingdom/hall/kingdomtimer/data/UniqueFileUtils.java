package jw.kingdom.hall.kingdomtimer.data;

import java.io.File;

/**
 * This file is part of KingdomHallTimer which is released under "no licence".
 */
public abstract class UniqueFileUtils {

    public static void createPath(String path) {
        path = getAccommodatedPath(path);
        new File(path).mkdirs();
    }

    public static File buildUniqueFile(String rootPath, String name, String extension) {
        rootPath = getAccommodatedPath(rootPath);
        File file = new File(rootPath + name+ extension);
        int index = 1;
        while (file.exists()) {
            file = new File(rootPath + name + "[" + Integer.toString(index) + "]" + extension);
            index++;
        }
        return file;
    }

    private static String getAccommodatedPath(String path) {
        if(path==null || path.length()==0) {
            return "";
        } else if (path.endsWith(File.separator)) {
            return path;
        } else {
            return path + File.separator;
        }
    }

    private UniqueFileUtils(){}
}
