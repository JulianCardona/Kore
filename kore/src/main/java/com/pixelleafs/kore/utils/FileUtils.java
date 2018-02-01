package com.pixelleafs.kore.utils;

import java.io.File;
import java.net.URLConnection;

/**
 * @author Julian Cardona on 9/5/17.
 */

public class FileUtils {

    public static boolean createDir(String path) {
        boolean isDirCreated = true;
        File f = new File(path);
        if (!f.exists()) {
            isDirCreated = f.mkdirs();
        }
        return isDirCreated;
    }

    public static boolean checkExist(String path) {
        File temp = new File(path);
        return temp.exists();
    }

    public static String getMimeType(String fileName) {
        return URLConnection.guessContentTypeFromName(fileName);
    }

}
