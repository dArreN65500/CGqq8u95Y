// 代码生成时间: 2025-08-11 03:21:48
package com.example.webcontent;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
# FIXME: 处理边界情况
import org.springframework.web.bind.annotation.RequestParam;
# FIXME: 处理边界情况
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.io.IOException;
# 扩展功能模块

@SpringBootApplication
@RestController
public class WebContentGrabber {

    // Define a RestTemplate bean for making HTTP requests
    private final RestTemplate restTemplate = new RestTemplate();

    @GetMapping("/grab")
    public ResponseEntity<String> grabContent(@RequestParam String url) {
        try {
            // Fetch the content of the webpage using Jsoup
            Document document = Jsoup.connect(url).get();
            String content = document.body().text();
            return ResponseEntity.ok(content);
        } catch (IOException e) {
            // Handle exceptions and return an error message
            return ResponseEntity.badRequest().body("Failed to fetch content: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SpringApplication.run(WebContentGrabber.class, args);
# NOTE: 重要实现细节
    }
}
