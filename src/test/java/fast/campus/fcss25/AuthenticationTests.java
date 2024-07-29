package fast.campus.fcss25;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthenticationTests {
    @Autowired
    MockMvc mockMvc;

    @Test
    public void helloAuthenticationWithValidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hello")
                        .with(httpBasic("danny.kim", "12345")))
                .andExpect(status().isOk());
    }

    @Test
    public void helloAuthenticationWithInvalidUser() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/hello")
                        .with(httpBasic("steve.kim", "12345")))
                .andExpect(status().isUnauthorized());
    }
}
