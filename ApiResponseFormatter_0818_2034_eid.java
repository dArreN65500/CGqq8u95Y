// 代码生成时间: 2025-08-18 20:34:21
package com.example.apiresponseformatter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

@RestController
public class ApiResponseFormatter {

    // API endpoint to get formatted response
    @GetMapping("/format-response")
    public ResponseEntity<Object> getFormattedResponse() {
        try {
            // Simulate an API response
            String apiResponse = "{"data":"Hello, world!"}";
            
            // Simulate a successful response with custom status code
            return ResponseEntity.status(HttpStatus.OK)
                              .body(new ApiResponse(200, "Success", apiResponse));
        } catch (Exception e) {
            // Handle exceptions and return error response
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ApiResponse(500, "Internal Server Error", e.getMessage()));
        }
    }

    // Inner class representing API response
    static class ApiResponse {
        private int status;
        private String message;
        private Object data;

        public ApiResponse(int status, String message, Object data) {
            this.status = status;
            this.message = message;
            this.data = data;
        }

        // Getters for status, message, and data
        public int getStatus() {
            return status;
        }
        public String getMessage() {
            return message;
        }
        public Object getData() {
            return data;
        }
    }
}
