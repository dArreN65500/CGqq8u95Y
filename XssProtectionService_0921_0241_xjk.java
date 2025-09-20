// 代码生成时间: 2025-09-21 02:41:07
package com.example.xssprotection;

import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

@Service
public class XssProtectionService {

    /**
     * Sanitizes the input string to prevent XSS attacks.
     * 
     * @param input The input string to be sanitized.
     * @return The sanitized string.
     */
    public String sanitizeInput(String input) {
        if (input == null) {
            throw new IllegalArgumentException("Input cannot be null");
        }
        return HtmlUtils.htmlEscape(input);
    }
}
