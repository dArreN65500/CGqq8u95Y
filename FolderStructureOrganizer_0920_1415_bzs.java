// 代码生成时间: 2025-09-20 14:15:21
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.stereotype.Component;

/**
 * FolderStructureOrganizer is a utility class that organizes the structure of a given folder.
 * It moves files into respective subfolders based on file extensions.
 */
@Component
public class FolderStructureOrganizer {

    private static final String SOURCE_FOLDER = "path/to/source"; // Change to your source folder path
    private static final String DESTINATION_FOLDER = "path/to/destination"; // Change to your destination folder path

    /**
     * Organizes the folder structure by moving files into subfolders based on their extensions.
     */
    public void organizeFolderStructure() {
        File sourceDir = new File(SOURCE_FOLDER);
        File[] files = sourceDir.listFiles();

        if (files == null || files.length == 0) {
            System.out.println("Source directory is empty or does not exist.");
            return;
        }

        for (File file : files) {
            if (file.isFile()) {
                String fileName = file.getName();
                String fileExtension = getExtension(fileName);
                File destinationDir = new File(DESTINATION_FOLDER + "/" + fileExtension);

                if (!destinationDir.exists()) {
                    destinationDir.mkdirs(); // Create the subfolder if it does not exist
                }

                try {
                    Files.move(
                        Paths.get(file.getAbsolutePath()),
                        Paths.get(destinationDir.getAbsolutePath() + "/" + fileName),
                        StandardCopyOption.REPLACE_EXISTING
                    );
                } catch (IOException e) {
                    System.err.println("Error moving file: " + fileName);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Extracts the file extension from a given file name.
     *
     * @param fileName The name of the file
     * @return The file extension (e.g., "txt", "jpg") or an empty string if no extension is found.
     */
    private String getExtension(String fileName) {
        int lastIndexOfDot = fileName.lastIndexOf(".");
        if (lastIndexOfDot == -1 || lastIndexOfDot == fileName.length() - 1) {
            return "";
        } else {
            return fileName.substring(lastIndexOfDot + 1);
        }
    }

    /**
     * Main method to run the folder structure organizer.
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        FolderStructureOrganizer organizer = new FolderStructureOrganizer();
        organizer.organizeFolderStructure();
    }
}
