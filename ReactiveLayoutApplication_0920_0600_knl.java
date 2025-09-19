// 代码生成时间: 2025-09-20 06:00:52
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@RequestMapping("/api")
public class ReactiveLayoutApplication {

    @GetMapping("/hello")
    public Mono<String> hello() {
        // Simulating a reactive response
        return Mono.just("Hello, this is a response from a reactive layout!");
    }

    @GetMapping("/error")
    public Mono<String> error() {
        // Simulating an error scenario
        return Mono.error(new RuntimeException("Simulated error"));
    }

    public static void main(String[] args) {
        SpringApplication.run(ReactiveLayoutApplication.class, args);
    }
}
