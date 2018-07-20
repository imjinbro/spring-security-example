package jinbro.security.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class APiUserController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
