package hou.tidaa.user.adapter.db;

import hou.tidaa.user.domain.model.User;
import hou.tidaa.user.domain.model.UserFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserFactory userFactory;

    //@Test
    public void users_crud_works() {
        User guest = userFactory.register("guest", "guest");
        String uid = guest.getId();

        // Create
        userRepository.save(guest);
        assertEquals(userRepository.count(), 3);

        // Retrieve
        User guest1 = userRepository.findById(uid)
                .orElseThrow(() -> new AssertionError("No user found with uid:" + uid));
        assertEquals(guest1.getName(), "guest");

        // Update
        guest.setPassword("pass");
        userRepository.save(guest);

        User guest2 = userRepository.findById(uid)
                .orElseThrow(() -> new AssertionError("No user found with uid:" + uid));

        assertEquals(guest2.getPassword(), "pass");
        assertEquals(guest2.getId(), guest.getId());

        // Delete
        userRepository.deleteByName(guest.getName());
        assertEquals(userRepository.count(), 2);
        assertFalse(userRepository.findById(uid).isPresent());
    }
}
