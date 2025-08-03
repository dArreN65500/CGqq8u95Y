// 代码生成时间: 2025-08-03 22:15:55
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
# 扩展功能模块
import java.lang.management.OperatingSystemMXBean;
import java.util.HashMap;
import java.util.Map;

/**
# NOTE: 重要实现细节
 * Spring Boot Application that acts as a system performance monitor tool.
# 添加错误处理
 */
@SpringBootApplication
# 增强安全性
@RestController
public class PerformanceMonitorApplication {

    private final OperatingSystemMXBean osBean;

    public PerformanceMonitorApplication() {
        this.osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);
    }

    @GetMapping("/monitor")
    public Map<String, Object> monitor() {
# NOTE: 重要实现细节
        Map<String, Object> performanceData = new HashMap<>();
        try {
            performanceData.put("cpuLoad", osBean.getSystemCpuLoad());
            performanceData.put("processCpuLoad", osBean.getProcessCpuLoad());
            performanceData.put("freeMemory", osBean.getFreePhysicalMemorySize());
            performanceData.put("totalMemory", osBean.getTotalPhysicalMemorySize());
            performanceData.put("maxMemory", osBean.getTotalSwapSpaceSize());
            performanceData.put("usedMemory", osBean.getCommittedVirtualMemorySize());
            performanceData.put("freeSwapSpaceSize", osBean.getFreeSwapSpaceSize());
        } catch (Exception e) {
            // Handle the error appropriately. For simplicity, print the stack trace.
# 扩展功能模块
            e.printStackTrace();
        }
        return performanceData;
    }

    public static void main(String[] args) {
        SpringApplication.run(PerformanceMonitorApplication.class, args);
    }
# NOTE: 重要实现细节
}
