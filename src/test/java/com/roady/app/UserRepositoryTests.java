package com.roady.app;

import com.roady.app.entities.User;
import com.roady.app.repositories.UserRepository;
import com.roady.app.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(value = false)
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TestEntityManager entityManager;

    @Test
    public void testCreateUser(){
        User user = new User();
        user.setEmail("rasa@email.com");
        user.setPassword("1234");
        user.setFirstName("Rasa");
        user.setLastName("Kaja");
        user.setPhoneNumber("123456789");

        User savedUser = userRepository.save(user);
        User existsUser = entityManager.find(User.class, savedUser.getId());

        assertThat(existsUser.getEmail()).isEqualTo(user.getEmail());
    }

    @Test
    public void testFindUserByEmail(){
        String email = "rasa@email.com";
        User user = userRepository.findUserByEmail(email);

        assertThat(user).isNotNull();
    }

    @Test
    public void testUpdateUser(){
        User user = userRepository.getReferenceById(1l);
        user.setEmail("rasa@email.com");
        user.setPassword("1111");
        user.setFirstName("Rasa");
        user.setLastName("Kaja");
        user.setPhoneNumber("111-555-111");

        User saverUser = userRepository.save(user);

        assertThat(saverUser.getLastName().equals(user));
    }


}
