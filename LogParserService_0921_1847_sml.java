// 代码生成时间: 2025-09-21 18:47:08
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * A service class for parsing log files.
 */
@Service
public class LogParserService {

    private static final String LOG_FILE_PATH = "logs/application.log";
    private static final Pattern LOG_ENTRY_PATTERN = Pattern.compile("(\d{4}-\d{2}-\d{2} \d{2}:\d{2}:\d{2},\d{3}) (\w+) (\S+) (\S+)");

    public List<LogEntry> parseLogFile() throws IOException {
        List<LogEntry> logEntries = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(Paths.get(LOG_FILE_PATH).toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Matcher matcher = LOG_ENTRY_PATTERN.matcher(line);
                if (matcher.find()) {
                    LogEntry logEntry = new LogEntry(
                            matcher.group(1), // Timestamp
                            matcher.group(2), // LogLevel
                            matcher.group(3), // LoggerName
                            matcher.group(4)  // LogMessage
                    );
                    logEntries.add(logEntry);
                }
            }
        } catch (IOException e) {
            throw new IOException("Error reading log file: " + LOG_FILE_PATH, e);
        }
        return logEntries;
    }

    /**
     * A class representing a log entry.
     */
    public class LogEntry {

        private String timestamp;
        private String logLevel;
        private String loggerName;
        private String logMessage;

        public LogEntry(String timestamp, String logLevel, String loggerName, String logMessage) {
            this.timestamp = timestamp;
            this.logLevel = logLevel;
            this.loggerName = loggerName;
            this.logMessage = logMessage;
        }

        // Getters and setters
        public String getTimestamp() { return timestamp; }
        public void setTimestamp(String timestamp) { this.timestamp = timestamp; }
        public String getLogLevel() { return logLevel; }
        public void setLogLevel(String logLevel) { this.logLevel = logLevel; }
        public String getLoggerName() { return loggerName; }
        public void setLoggerName(String loggerName) { this.loggerName = loggerName; }
        public String getLogMessage() { return logMessage; }
        public void setLogMessage(String logMessage) { this.logMessage = logMessage; }
    }
}