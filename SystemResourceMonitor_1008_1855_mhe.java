// 代码生成时间: 2025-10-08 18:55:53
package com.example.monitor;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.lang.management.ManagementFactory;
import java.lang.management.OperatingSystemMXBean;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@RestController
public class SystemResourceMonitor {

    @GetMapping("/monitor")
    public SystemResourceInfo monitorResources() {
        SystemResourceInfo info = new SystemResourceInfo();
        try {
            OperatingSystemMXBean osBean = ManagementFactory.getOperatingSystemMXBean();
            double cpuLoad = osBean.getSystemCpuLoad();
            long totalMemory = osBean.getTotalPhysicalMemorySize();
            long freeMemory = osBean.getFreePhysicalMemorySize();
            long usedMemory = totalMemory - freeMemory;
            long uptime = osBean.getSystemUptime();

            info.setCpuLoad(cpuLoad);
            info.setMemoryUsage(usedMemory);
            info.setTotalMemory(totalMemory);
            info.setUptime(uptime);

            // Disk space information
            long totalDiskSpace = getTotalDiskSpace();
            long freeDiskSpace = getFreeDiskSpace();
            info.setDiskSpace(totalDiskSpace, freeDiskSpace);

            return info;

        } catch (Exception e) {
            // Log and handle exceptions appropriately
            e.printStackTrace();
            info.setError("Error retrieving system resource information: " + e.getMessage());
            return info;
        }
    }

    private long getTotalDiskSpace() {
        // Code to get total disk space
        return 0L;
    }

    private long getFreeDiskSpace() {
        // Code to get free disk space
        return 0L;
    }

    // Define a class to hold system resource information
    public static class SystemResourceInfo {
        private double cpuLoad;
        private long memoryUsage;
        private long totalMemory;
        private long uptime;
        private List<Long> diskSpace;
        private String error;

        public double getCpuLoad() { return cpuLoad; }
        public void setCpuLoad(double cpuLoad) { this.cpuLoad = cpuLoad; }

        public long getMemoryUsage() { return memoryUsage; }
        public void setMemoryUsage(long memoryUsage) { this.memoryUsage = memoryUsage; }

        public long getTotalMemory() { return totalMemory; }
        public void setTotalMemory(long totalMemory) { this.totalMemory = totalMemory; }

        public long getUptime() { return uptime; }
        public void setUptime(long uptime) { this.uptime = uptime; }

        public List<Long> getDiskSpace() { return diskSpace; }
        public void setDiskSpace(long total, long free) {
            this.diskSpace = Arrays.asList(total, free);
        }

        public String getError() { return error; }
        public void setError(String error) { this.error = error; }
    }

    public static void main(String[] args) {
        SpringApplication.run(SystemResourceMonitor.class, args);
    }
}
