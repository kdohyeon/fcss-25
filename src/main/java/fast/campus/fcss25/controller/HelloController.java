package fast.campus.fcss25.controller;

import fast.campus.fcss25.service.HelloService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HelloController {

    private final HelloService helloService;

    @GetMapping("/api/v1/hello")
    public String hello() {
        return helloService.getName();
    }
}
