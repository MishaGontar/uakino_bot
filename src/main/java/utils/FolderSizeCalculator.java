package utils;

import java.io.File;

/**
 * The FolderSizeCalculator class provides utility methods for calculating and printing the size of a folder.
 */
public class FolderSizeCalculator {

    /**
     * Prints the size of the specified folder in a human-readable format.
     *
     * @param folder The folder for which to calculate and print the size.
     */
    public static void printFolderSize(File folder) {
        System.out.println("Folder Size: " + formatSize(calculateFolderSize(folder)));
    }

    /**
     * Calculates the size of the specified folder and its contents.
     *
     * @param folder The folder for which to calculate the size.
     * @return The size of the folder in bytes.
     */
    public static long calculateFolderSize(File folder) {
        long sizeInBytes = 0;

        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        sizeInBytes += file.length();
                    } else if (file.isDirectory()) {
                        sizeInBytes += calculateFolderSize(file);
                    }
                }
            }
        }

        return sizeInBytes;
    }

    /**
     * Formats the size in bytes into a human-readable format (e.g., bytes, KB, MB, GB).
     *
     * @param sizeInBytes The size in bytes to format.
     * @return The formatted size as a string.
     */
    public static String formatSize(long sizeInBytes) {
        if (sizeInBytes < 1024) {
            return sizeInBytes + " bytes";
        } else if (sizeInBytes < 1024 * 1024) {
            return sizeInBytes / 1024 + " KB";
        } else if (sizeInBytes < 1024 * 1024 * 1024) {
            return sizeInBytes / (1024 * 1024) + " MB";
        } else {
            return sizeInBytes / (1024 * 1024 * 1024) + " GB";
        }
    }
}

