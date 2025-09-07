// 代码生成时间: 2025-09-07 12:04:21
package com.example.security;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AntiSqlInjectionService {

    // JPA EntityManager for database operations
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * Finds all users whose name contains the provided substring.
     * This method uses JPA Criteria API to prevent SQL injection.
     *
     * @param nameSubstring The substring to search for in user names.
     * @return A list of users whose names contain the provided substring.
     */
    public List<User> findUsersByNameSubstring(String nameSubstring) {
        // Initialize the CriteriaBuilder
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        
        // Create a CriteriaQuery to find all users with name containing the substring
        CriteriaQuery<User> criteriaQuery = cb.createQuery(User.class);
        Root<User> userRoot = criteriaQuery.from(User.class);
        criteriaQuery.select(userRoot)
                     .where(cb.like(userRoot.get("name"), "%" + nameSubstring + "%"));

        // Execute the query safely, without拼接 raw SQL strings
        TypedQuery<User> query = entityManager.createQuery(criteriaQuery);
        return query.getResultList();
    }

    // Add more methods as needed to handle different types of queries and operations
    // Remember to always use parameterized queries or JPA Criteria API to prevent SQL injection
}

/**
 * User.java
 * Represents a user entity.
 */
package com.example.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {
    @Id
    private Long id;
    private String name;
    
    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}