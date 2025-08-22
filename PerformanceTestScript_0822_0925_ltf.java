// 代码生成时间: 2025-08-22 09:25:18
 * @Author: [Your Name]
 * @Date: [Date]
 * @Description: A Java program using Spring Cloud framework to perform performance testing.
 */

package com.example.performancetest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@RestController
public class PerformanceTestScript {

    private static final String SERVICE_URL = "http://localhost:8080/api/test"; // URL of the service to test
    private RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        SpringApplication.run(PerformanceTestScript.class, args);
    }

    @GetMapping("/performTest")
    public String performTest() {
        try {
            // Perform the performance test by sending a fixed number of requests
            int numberOfRequests = 100; // Number of requests to send
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < numberOfRequests; i++) {
                ResponseEntity<String> response = restTemplate.getForEntity(SERVICE_URL, String.class);
                if (!response.getStatusCode().is2xxSuccessful()) {
                    return "Test failed: " + response.getStatusCode().toString();
                }
            }
            long endTime = System.currentTimeMillis();
            double duration = (endTime - startTime) / 1000.0;
            return "Test completed. Duration: " + duration + " seconds.";
        } catch (Exception e) {
            // Log and handle exceptions
            e.printStackTrace();
            return "Test failed due to an exception: " + e.getMessage();
        }
    }
}
