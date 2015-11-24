package de.oth.hsp.common.utils;

import java.io.File;

public class FileOperations {
    /**
     * Returns the filename without the extensions (e.g. .xml, .jar etc.).
     * Returns null, if the file does not exist,
     *
     * @param file
     * @return filename without extension or null
     */
    public static String getNameWithoutExtension(File file) {
        String fileName = null;
        // Check whether file is not null,
        // is a normal file
        // the first sign is not a dot
        if (file != null && file.isFile() && !file.getName().startsWith(".")) {
            // if there are no dots in the string, just set the fileName
            if (file.getName().contains(".")) {
                fileName = file.getName().substring(0, file.getName().lastIndexOf("."));
            } else {
                fileName = file.getName();
            }
        }

        return fileName;
    }

}
