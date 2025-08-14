// 代码生成时间: 2025-08-14 22:06:38
package com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Service
public class PreventSqlInjectionService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public PreventSqlInjectionService(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /**
     * Inserts a new user into the database using prepared statements to prevent SQL injection.
     * 
     * @param username The username of the user to be inserted.
     * @return The id of the inserted user.
     */
    @Transactional
    public int insertUser(String username) {
        String sql = "INSERT INTO users (username) VALUES (?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, new String[] { "id" });
                ps.setString(1, username);
                return ps;
            }
        }, keyHolder);

        return keyHolder.getKey().intValue();
    }

    /**
     * Retrieves a list of all users from the database.
     * 
     * @return A list of user names.
     */
    public List<String> getAllUsers() {
        String sql = "SELECT username FROM users";
        return jdbcTemplate.queryForList(sql, String.class);
    }

    /**
     * Handles exceptions that may occur during database operations.
     * 
     * @param ex The SQLException that was thrown.
     */
    private void handleSQLException(SQLException ex) {
        // Log and handle the exception appropriately
        // For simplicity, we're just printing the stack trace here
        ex.printStackTrace();
    }
}
