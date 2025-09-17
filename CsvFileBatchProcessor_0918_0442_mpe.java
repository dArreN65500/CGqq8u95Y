// 代码生成时间: 2025-09-18 04:42:07
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CsvFileBatchProcessor {

    private static final String UPLOAD_DIR = "uploads/"; // 定义文件上传目录

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("group1") MultipartFile[] files) {
        // 检查是否有文件上传
        if (files == null || files.length == 0) {
            return "No files were uploaded";
        }

        // 处理文件上传
        for (MultipartFile file : files) {
            try {
                if (file.isEmpty()) {
                    continue; // 如果文件为空，则跳过
                }

                // 获取文件名
                String fileName = file.getOriginalFilename();
                // 保存文件到指定目录
                Path destinationPath = Paths.get(UPLOAD_DIR + fileName);
                // 保存文件
                Files.copy(file.getInputStream(), destinationPath);

                // 处理CSV文件
                processCsvFile(destinationPath);
            } catch (IOException e) {
                e.printStackTrace();
                return "Failed to upload file: " + file.getOriginalFilename();
            }
        }

        return "Files uploaded successfully";
    }

    private void processCsvFile(Path filePath) {
        // 检查文件是否存在
        if (!Files.exists(filePath)) {
            throw new RuntimeException("File not found: " + filePath);
        }

        // 读取CSV文件内容
        List<String> lines = Files.readAllLines(filePath);
        // 遍历CSV文件的每一行
        for (String line : lines) {
            // 处理每行的数据（示例：打印每行内容）
            System.out.println(line);
            // 这里可以添加具体的业务逻辑处理
        }
    }

    // 辅助方法：获取文件资源
    private Resource getFileAsResource(String fileName) {
        try {
            Path fileTobeDownloaded = Paths.get(UPLOAD_DIR + fileName).toAbsolutePath().normalize();
            Resource resource = new UrlResource(fileTobeDownloaded.toUri());
            return resource;
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return null;
        }
    }
}