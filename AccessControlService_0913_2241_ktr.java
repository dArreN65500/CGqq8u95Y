// 代码生成时间: 2025-09-13 22:41:40
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

// 访问权限控制服务
@Service
public class AccessControlService {
    
    // 检查用户是否具有相应的权限
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String accessAdmin() {
        return "Access granted for admin.";
    }
    
    // 检查用户是否具有相应的权限
    @PreAuthorize("hasAuthority('ROLE_USER')")
    public String accessUser() {
        return "Access granted for user.";
    }
    
    // 公共访问接口
    @GetMapping("/public")
    public String publicAccess() {
        return "Public access granted.";
    }
    
    // 需要用户身份验证的接口
    @GetMapping("/authenticated")
    public String authenticatedAccess(Authentication authentication) {
        return "Authenticated access granted. User: " + authentication.getName();
    }
}

// 控制器类
@RestController
public class AccessControlController {
    
    @Autowired
    private AccessControlService accessControlService;
    
    // 映射到/admin的GET请求
    @GetMapping("/admin")
    public String getAdminAccess() {
        return accessControlService.accessAdmin();
    }
    
    // 映射到/user的GET请求
    @GetMapping("/user")
    public String getUserAccess() {
        return accessControlService.accessUser();
    }
    
    // 映射到/public的GET请求
    @GetMapping("/public")
    public String getPublicAccess() {
        return accessControlService.publicAccess();
    }
    
    // 映射到/authenticated的GET请求
    @GetMapping("/authenticated")
    public String getAuthenticatedAccess() {
        return accessControlService.authenticatedAccess(SecurityContextHolder.getContext().getAuthentication());
    }
}