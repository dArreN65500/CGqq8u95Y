// 代码生成时间: 2025-09-17 06:49:15
import org.springframework.stereotype.Service;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Service class for handling file backup and synchronization.
 */
@Service
public class FileBackupAndSyncTool {

    private static final String BACKUP_DIR = "./backup"; // Define backup directory

    /**
     * Method to backup a file to the backup directory.
     *
     * @param sourcePath The path of the source file.
     * @return boolean indicating success or failure.
     */
    public boolean backupFile(String sourcePath) {
        try {
            Path source = Paths.get(sourcePath);
            Path backup = Paths.get(BACKUP_DIR, source.getFileName().toString());

            // Ensure backup directory exists
            Files.createDirectories(Paths.get(BACKUP_DIR));

            // Copy file to backup directory
            Files.copy(source, backup, StandardCopyOption.REPLACE_EXISTING);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Method to synchronize files between two directories.
     *
     * @param sourceDir The source directory path.
     * @param targetDir The target directory path.
     * @return boolean indicating success or failure.
     */
    public boolean syncDirectories(String sourceDir, String targetDir) {
        try {
            Files.walk(Paths.get(sourceDir))
                .forEach(sourcePath -> {
                    try {
                        Path targetPath = Paths.get(targetDir, sourcePath.toString().replace(sourceDir, ""));
                        Files.createDirectories(targetPath.getParent());
                        Files.copy(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
