// 代码生成时间: 2025-09-02 07:03:59
 * A utility class to organize a directory structure by sorting files into designated folders.
 *
 * @author Your Name
 * @version 1.0
 */

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Comparator;
import java.util.stream.Collectors;

public class FolderOrganizer {
    
    // Define the root directory to organize
    private static final String ROOT_DIRECTORY = "/path/to/root";

    public static void main(String[] args) {
        try {
            // Create an instance of FolderOrganizer and organize the directory
            FolderOrganizer organizer = new FolderOrganizer();
            organizer.organizeDirectory();
        } catch (IOException e) {
            System.err.println("An error occurred while organizing the directory: " + e.getMessage());
        }
    }

    /**
     * Organizes the directory by sorting files into designated folders.
     *
     * @throws IOException If an I/O error occurs.
     */
    public void organizeDirectory() throws IOException {
        File root = new File(ROOT_DIRECTORY);
        if (!root.exists() || !root.isDirectory()) {
            throw new IOException("The specified root directory does not exist or is not a directory.");
        }

        // Define the designated folders
        String[] designatedFolders = new String[] {"Documents", "Images", "Videos", "Audio"};
        Arrays.stream(designatedFolders).forEach(folder -> {
            File dir = new File(root, folder);
            if (!dir.exists()) {
                dir.mkdirs(); // Create the folder if it does not exist
            }
        });

        // Get all files in the root directory (excluding sub-directories)
        File[] files = root.listFiles(file -> !file.isDirectory());
        if (files != null) {
            Arrays.stream(files).forEach(file -> {
                // Sort files into designated folders based on file extension
                String fileExtension = getFileExtension(file.getName());
                String targetFolder = getTargetFolderByExtension(fileExtension);
                if (targetFolder != null) {
                    File targetDir = new File(root, targetFolder);
                    try {
                        Files.move(file.toPath(), Paths.get(targetDir.getAbsolutePath(), file.getName()), StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        System.err.println("Failed to move file: " + file.getName() + " Error: " + e.getMessage());
                    }
                }
            });
        }
    }

    /**
     * Gets the file extension from a file name.
     *
     * @param fileName The name of the file.
     * @return The file extension or an empty string if none found.
     */
    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf('.');
        return dotIndex > 0 ? fileName.substring(dotIndex + 1).toLowerCase() : "";
    }

    /**
     * Determines the target folder based on the file extension.
     *
     * @param fileExtension The file extension.
     * @return The target folder name or null if no matching folder found.
     */
    private String getTargetFolderByExtension(String fileExtension) {
        // Define a mapping between file extensions and folder names
        String[][] extensionToFolderMapping = new String[][]{
            {"docx", "Documents"},
            {"doc", "Documents"},
            {"pdf", "Documents"},
            {"jpg", "Images"},
            {"jpeg", "Images"},
            {"png", "Images"},
            {"mp4", "Videos"},
            {"avi", "Videos"},
            {"mkv", "Videos"},
            {"mp3", "Audio"},
            {"wav", "Audio"}
        };
        
        for (String[] mapping : extensionToFolderMapping) {
            if (mapping[0].equals(fileExtension)) {
                return mapping[1];
            }
        }
        return null; // No matching folder found for the file extension
    }
}
