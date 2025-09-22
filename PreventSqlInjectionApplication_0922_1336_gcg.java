// 代码生成时间: 2025-09-22 13:36:21
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
# NOTE: 重要实现细节

@SpringBootApplication
public class PreventSqlInjectionApplication {

    @PersistenceContext
    private EntityManager entityManager;

    public static void main(String[] args) {
        SpringApplication.run(PreventSqlInjectionApplication.class, args);
# 增强安全性
    }

    @RestController
    class UserController {

        @GetMapping("/searchUsers")
        public List<User> searchUsers(@RequestParam String username) {
# 添加错误处理
            // 防止SQL注入的查询
            String jpql = "SELECT u FROM User u WHERE u.username LIKE :username";
            Query query = entityManager.createQuery(jpql);
            query.setParameter("username", username + "%"); // 使用参数化查询防止SQL注入
            return query.getResultList();
# NOTE: 重要实现细节
        }
    }
}

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
class User {
    @Id
    private Long id;
    private String username;
    // Getters and setters
    public Long getId() {
        return id;
# NOTE: 重要实现细节
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getUsername() {
# 扩展功能模块
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
}
