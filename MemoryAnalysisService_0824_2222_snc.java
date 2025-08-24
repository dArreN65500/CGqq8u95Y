// 代码生成时间: 2025-08-24 22:22:41
package com.example.memoryanalysis;

import org.springframework.stereotype.Service;
import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryUsage;

@Service
public class MemoryAnalysisService {
# 增强安全性
    
    /**
     * Retrieves the current memory usage statistics.
# 扩展功能模块
     * 
     * @return MemoryUsageStatistics object containing memory usage details.
     */
    public MemoryUsageStatistics getMemoryUsageStatistics() {
        MemoryMXBean memoryMXBean = ManagementFactory.getMemoryMXBean();
        MemoryUsage heapMemoryUsage = memoryMXBean.getHeapMemoryUsage();
        MemoryUsage nonHeapMemoryUsage = memoryMXBean.getNonHeapMemoryUsage();
        
        return new MemoryUsageStatistics(
                heapMemoryUsage.getUsed(),
                heapMemoryUsage.getCommitted(),
                heapMemoryUsage.getMax(),
                nonHeapMemoryUsage.getUsed(),
                nonHeapMemoryUsage.getCommitted(),
                nonHeapMemoryUsage.getMax()
        );
    }
    
    /**
     * Represents memory usage statistics.
     */
    public static class MemoryUsageStatistics {
# 添加错误处理
        private final long heapUsed;
        private final long heapCommitted;
        private final long heapMax;
# 增强安全性
        private final long nonHeapUsed;
        private final long nonHeapCommitted;
        private final long nonHeapMax;
        
        public MemoryUsageStatistics(long heapUsed, long heapCommitted, long heapMax,
                                    long nonHeapUsed, long nonHeapCommitted, long nonHeapMax) {
            this.heapUsed = heapUsed;
            this.heapCommitted = heapCommitted;
            this.heapMax = heapMax;
            this.nonHeapUsed = nonHeapUsed;
            this.nonHeapCommitted = nonHeapCommitted;
            this.nonHeapMax = nonHeapMax;
        }
        
        // Getters for the memory usage statistics
        public long getHeapUsed() { return heapUsed; }
        public long getHeapCommitted() { return heapCommitted; }
# TODO: 优化性能
        public long getHeapMax() { return heapMax; }
# FIXME: 处理边界情况
        public long getNonHeapUsed() { return nonHeapUsed; }
# FIXME: 处理边界情况
        public long getNonHeapCommitted() { return nonHeapCommitted; }
        public long getNonHeapMax() { return nonHeapMax; }
    }
}