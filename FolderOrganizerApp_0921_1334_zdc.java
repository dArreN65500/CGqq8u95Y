// 代码生成时间: 2025-09-21 13:34:37
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FolderOrganizerApp implements CommandLineRunner {

    private static final String SOURCE_FOLDER = "/path/to/source/folder";
    private static final String DESTINATION_FOLDER = "/path/to/destination/folder";

    public static void main(String[] args) {
        SpringApplication.run(FolderOrganizerApp.class, args);
    }

    @Override
    public void run(String... args) {
        System.out.println("Starting folder organizer...");
        try {
            organizeFolders();
        } catch (IOException e) {
            System.err.println("Error occurred while organizing folders: " + e.getMessage());
        }
    }

    private void organizeFolders() throws IOException {
        File sourceDir = new File(SOURCE_FOLDER);
        File[] files = sourceDir.listFiles();
        if (files == null) {
            throw new IOException("Source directory does not exist or is not accessible");
        }

        for (File file : files) {
            Path sourcePath = Paths.get(file.getAbsolutePath());
            Path destinationPath = Paths.get(DESTINATION_FOLDER, file.getName());

            // Check if the destination file already exists
            if (Files.exists(destinationPath)) {
                System.out.println("File already exists in destination: " + destinationPath);
                continue;
            }

            // Copy file to destination
            Files.copy(sourcePath, destinationPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File organized: " + file.getName());
        }
    }
}
