// 代码生成时间: 2025-08-15 04:37:05
package com.example.notification;

import org.springframework.stereotype.Service;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * 消息通知服务，负责发送通知消息
 */
@Service
public class NotificationService {

    private final ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * 发送通知消息
     * 
     * @param message 要发送的消息
     */
    public void sendNotification(String message) {
        try {
            executorService.submit(() -> {
                System.out.println("Sending notification: " + message);
                // 模拟消息发送过程
                try {
                    TimeUnit.SECONDS.sleep(2); // 模拟发送耗时
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new RuntimeException("Notification sending interrupted", e);
                }
                System.out.println("Notification sent: " + message);
            });
        } catch (Exception e) {
            System.err.println("Error sending notification: " + e.getMessage());
        }
    }

    /**
     * 关闭ExecutorService以释放资源
     */
    public void shutdown() {
        executorService.shutdown();
        try {
            if (!executorService.awaitTermination(60, TimeUnit.SECONDS)) {
                executorService.shutdownNow();
            }
        } catch (InterruptedException e) {
            executorService.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
}
