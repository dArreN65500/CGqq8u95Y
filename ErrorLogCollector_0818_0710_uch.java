// 代码生成时间: 2025-08-18 07:10:53
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@RestController
@RequestMapping("/error")
public class ErrorLogCollector {
    
    private static final Logger logger = LoggerFactory.getLogger(ErrorLogCollector.class);
    
    private final KafkaTemplate<String, String> kafkaTemplate;
    
    @Autowired
    public ErrorLogCollector(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }
    
    @GetMapping("/log")
    public String logError(String error) {
        try {
            String errorId = UUID.randomUUID().toString();
            Map<String, Object> logMap = new HashMap<>();
            logMap.put("errorId", errorId);
            logMap.put("errorDetails", error);
            logMap.put("timestamp", System.currentTimeMillis());
            
            String logMessage = logMap.toString();
            kafkaTemplate.send("error-log-topic", logMessage);
            return "Error logged with ID: " + errorId;
        } catch (Exception e) {
            logger.error("Failed to log error", e);
            return "Error logging failed";
        }
    }
    
    public static void main(String[] args) {
        SpringApplication.run(ErrorLogCollector.class, args);
    }
}
