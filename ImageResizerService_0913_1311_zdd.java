// 代码生成时间: 2025-09-13 13:11:40
 * documentation, and maintainability.
 */
package com.example.imageresizer;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImageResizerService {

    private final int maxWidth;
    private final int maxHeight;

    // Constructor to set max width and height
    public ImageResizerService(int maxWidth, int maxHeight) {
        this.maxWidth = maxWidth;
        this.maxHeight = maxHeight;
    }

    // Method to resize a single image
    private BufferedImage resizeImage(BufferedImage originalImage, int maxWidth, int maxHeight) throws IOException {
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();

        // Calculate the new dimensions
        double ratio = Math.min(maxWidth / (double) width, maxHeight / (double) height);
        int newWidth = (int) (width * ratio);
        int newHeight = (int) (height * ratio);

        // Create the scaled image
        BufferedImage scaledImage = new BufferedImage(newWidth, newHeight, originalImage.getType());
        scaledImage.getGraphics().drawImage(originalImage.getScaledInstance(newWidth, newHeight, BufferedImage.SCALE_SMOOTH), 0, 0, null);

        return scaledImage;
    }

    // Method to resize multiple images
    public List<File> resizeImages(List<MultipartFile> files) throws IOException {
        List<File> resizedFiles = new ArrayList<>();
        for (MultipartFile file : files) {
            try {
                BufferedImage originalImage = ImageIO.read(file.getInputStream());
                BufferedImage resizedImage = resizeImage(originalImage, maxWidth, maxHeight);

                // Save the resized image
                File resizedFile = new File("resized_" + file.getOriginalFilename());
                ImageIO.write(resizedImage, "jpeg", resizedFile);
                resizedFiles.add(resizedFile);
            } catch (IOException e) {
                // Handle exceptions for individual files
                System.err.println("Error resizing image: " + file.getOriginalFilename() + " - " + e.getMessage());
            }
        }
        return resizedFiles;
    }
}
