// 代码生成时间: 2025-08-17 02:48:36
package com.example.folderorganizer;

import org.springframework.stereotype.Service;
# FIXME: 处理边界情况
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
# NOTE: 重要实现细节
import java.nio.file.Paths;
# 增强安全性
import java.nio.file.StandardCopyOption;
# TODO: 优化性能
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FolderOrganizer {

    private static final String ORGANIZED_FOLDER = "organized";
    private static final String UNSORTED_FOLDER = "unsorted";

    public void organizeFolder(String directoryPath) {
        File directory = new File(directoryPath);
        try {
            File[] files = directory.listFiles();
            if (files != null) {
                Arrays.stream(files)
                        .filter(File::isFile)
                        .sorted(Comparator.comparing(File::getName))
                        .forEach(file -> moveFileToOrganizedFolder(file, directoryPath));
            }
            if (!directoryPath.endsWith(File.separator + ORGANIZED_FOLDER)) {
                // Create organized and unsorted folders if they do not exist
                createFolderIfNotExists(directoryPath + File.separator + ORGANIZED_FOLDER);
                createFolderIfNotExists(directoryPath + File.separator + UNSORTED_FOLDER);
            } else {
                throw new IllegalArgumentException("The directory is already organized.");
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while organizing the folder: " + e.getMessage(), e);
        }
    }

    private void moveFileToOrganizedFolder(File file, String directoryPath) throws IOException {
        Path sourcePath = file.toPath();
        Path targetPath = Paths.get(directoryPath, ORGANIZED_FOLDER, file.getName());
        Files.move(sourcePath, targetPath, StandardCopyOption.REPLACE_EXISTING);
    }

    private void createFolderIfNotExists(String folderPath) {
        File folder = new File(folderPath);
# 改进用户体验
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }
}
