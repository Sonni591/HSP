package de.oth.hsp.common.io;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.prefs.Preferences;

/**
 * Used to store and retrieve the latest workspace directory.
 * 
 * @author Thomas Butz
 *
 */
public final class WorkspaceManager {
    private static final String WS_KEY = "last_workspace";
    private static final Preferences USER_PREFS = Preferences.userNodeForPackage(WorkspaceManager.class);

    private WorkspaceManager() {
    }

    /**
     * @return the previously used workspace directory if it has been set and
     *         exists, otherwise <i>null</i>
     */
    public static Path getWorkspace() {
        String workspaceString = USER_PREFS.get(WS_KEY, null);
        if (workspaceString == null) {
            return null;
        }

        Path workspace = Paths.get(workspaceString);

        return Files.isDirectory(workspace) ? workspace : null;
    }

    /**
     * Sets the previously used workspace directory.
     * 
     * @param dir
     *            the workspace directory
     */
    public static void setWorkspace(Path dir) {
        Objects.requireNonNull(dir);
        Path absolutePath = dir.normalize().toAbsolutePath();

        if (!Files.isDirectory(absolutePath)) {
            throw new RuntimeException("Not a directory: \"" + absolutePath + "\"");
        }

        USER_PREFS.put(WS_KEY, absolutePath.toString());
    }
}
