// 代码生成时间: 2025-07-31 18:49:06
import org.springframework.validation.annotation.Validated;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import javax.validation.Valid;

@RestController
@Validated
public class FormValidator {
    
    @PostMapping("/validate")
    public String validateForm(@RequestBody @Valid UserFormData userFormData, BindingResult bindingResult) {
        // 检查表单数据是否有效
        if (bindingResult.hasErrors()) {
            // 如果表单数据有错误, 返回错误信息
            StringBuilder errors = new StringBuilder();
            bindingResult.getFieldErrors().forEach(error -> {
                errors.append(error.getField() + ": " + error.getDefaultMessage() + "
");
            });
            return "Error" + errors.toString();
        } else {
            // 如果表单数据有效, 执行业务逻辑
            return "Form data is valid";
        }
    }
    
    // 定义表单数据类
    public static class UserFormData {
        private String username;
        private String email;
        private String password;
        
        // 省略getter和setter方法
    }
    
    // 定义验证注解
    import javax.validation.constraints.Email;
    import javax.validation.constraints.NotNull;
    import javax.validation.constraints.Size;
    
    public static class UserFormData {
        @NotNull(message = "Username cannot be null")
        @Size(min = 3, max = 50, message = "Username length must be between 3 and 50")
        private String username;
        
        @Email(message = "Email should be valid")
        private String email;
        
        @NotNull(message = "Password cannot be null")
        @Size(min = 6, max = 50, message = "Password length must be between 6 and 50")
        private String password;
        
        // 省略getter和setter方法
    }
}