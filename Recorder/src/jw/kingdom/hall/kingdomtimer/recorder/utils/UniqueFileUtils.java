package jw.kingdom.hall.kingdomtimer.recorder.utils;

import java.io.File;

/**
 * All rights reserved & copyright ©
 */
public abstract class UniqueFileUtils {

    public static void createPath(String path) {
        path = getAccommodatedRootPath(path);
        new File(UniqueFileUtils.getAccommodatedRootPath(path)).mkdirs();
    }

    public static File buildFile(String rootPath, String name, String extension) {
        rootPath = getAccommodatedRootPath(rootPath);
        File file = new File(rootPath + name+ extension);
        int index = 1;
        while (file.exists()) {
            file = new File(rootPath + name + "[" + Integer.toString(index) + "]" + extension);
            index++;
        }
        return file;
    }

    private static String getAccommodatedRootPath(String path) {
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
