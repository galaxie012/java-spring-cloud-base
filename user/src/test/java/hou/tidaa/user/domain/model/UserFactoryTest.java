package hou.tidaa.user.domain.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserFactoryTest {
    @Autowired
    private UserFactory userFactory;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void password_encoder_works() {
        User guest = userFactory.register("guest", "guest");
        assertNotEquals("guest", guest.getPassword());

        assertTrue(passwordEncoder.matches("guest", guest.getPassword()));
    }
}
