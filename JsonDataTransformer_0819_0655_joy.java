// 代码生成时间: 2025-08-19 06:55:21
package com.example.jsontransformer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import java.io.IOException;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class JsonDataTransformer {

    private final ObjectMapper objectMapper;

    public JsonDataTransformer(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @PostMapping("/transform")
    public String transformJson(@RequestBody String jsonInput) {
        try {
            // 尝试将输入的JSON字符串反序列化为Map
            Object deserializedMap = objectMapper.readValue(jsonInput, Object.class);
            // 将Map重新序列化回JSON字符串
            String transformedJson = objectMapper.writeValueAsString(deserializedMap);
            return transformedJson;
        } catch (JsonProcessingException e) {
            // 处理JSON处理异常
            return createErrorResponse("Invalid JSON format", e.getMessage());
        } catch (IOException e) {
            // 处理IO异常
            return createErrorResponse("Error processing JSON", e.getMessage());
        }
    }

    private String createErrorResponse(String error, String message) {
        // 构建错误响应的JSON字符串
        String jsonResponse = "{"error":"" + error + "","message":"" + message + ""}";
        return jsonResponse;
    }

    public static void main(String[] args) {
        SpringApplication.run(JsonDataTransformer.class, args);
    }
}
