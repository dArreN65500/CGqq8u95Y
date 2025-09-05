// 代码生成时间: 2025-09-05 17:18:06
import javax.sql.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConnectionPoolManager {

    /*
     * Bean configuration for HikariCP connection pool.
     * HikariCP is used as it is a high-performance JDBC connection pool.
     */
    @Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://localhost:3306/your_database");
        dataSource.setUsername("your_username");
        dataSource.setPassword("your_password");
        dataSource.setMaximumPoolSize(10); // Set the maximum number of connections in the pool
        dataSource.setMinimumIdle(2); // Set the minimum number of idle connections in the pool
        dataSource.setIdleTimeout(30000); // Set the timeout for idle connections
        dataSource.setConnectionTimeout(30000); // Set the timeout when waiting for a connection from the pool
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

    /*
     * Bean configuration for transaction manager.
     * This is used to manage transactions in the application.
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /*
     * Method to acquire a connection from the pool.
     * @return a connection from the pool
     * @throws Exception if an error occurs while acquiring the connection
     */
    public Connection acquireConnection() throws Exception {
        try {
            DataSource dataSource = this.dataSource();
            return dataSource.getConnection();
        } catch (Exception e) {
            throw new Exception("Error acquiring connection from the pool", e);
        }
    }

    /*
     * Method to release a connection back to the pool.
     * @param connection the connection to release
     */
    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                // Log the error
                System.err.println("Error releasing connection to the pool: " + e.getMessage());
            }
        }
    }
}
