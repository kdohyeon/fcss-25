package fast.campus.fcss25;

import fast.campus.fcss25.service.HelloService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.security.test.context.support.WithMockUser;

import javax.naming.AuthenticationException;
import java.nio.file.AccessDeniedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class MainTests {
    @Autowired
    private HelloService helloService;

    @Test
    void testHelloServiceWithNoUser() {
        assertThrows(AuthenticationCredentialsNotFoundException.class, () -> helloService.getName());
    }

    @Test
    @WithMockUser(authorities = "read")
    void testHelloServiceWithUserButWrongAuthority() {
        assertThrows(AuthorizationDeniedException.class, () -> helloService.getName());
    }

    @Test
    @WithMockUser(authorities = "write")
    void testNameServiceWithUserButCorrectAuthority() {
        String result = helloService.getName();
        assertEquals("hello", result);
    }
}
