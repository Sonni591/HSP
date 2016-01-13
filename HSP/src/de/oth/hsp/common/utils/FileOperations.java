package de.oth.hsp.common.utils;

import java.io.File;
import java.nio.file.Path;

public class FileOperations {
    /**
     * Returns the filename without the extensions (e.g. .xml, .jar etc.).
     * Returns null, if the file does not exist,
     *
     * @param file
     * @return filename without extension or null
     */
    public static String getNameWithoutExtension(File file) {
        return getNameWithoutExtension(file.toPath());
    }

    public static String getNameWithoutExtension(Path path) {
        String filename = path.getFileName().toString();

        if (filename.contains(".")) {
            filename = filename.substring(0, filename.lastIndexOf("."));
        }

        return filename;
    }

    public static String canonicalize(Path path) {
        return path.normalize().toAbsolutePath().toString();
    }
}
