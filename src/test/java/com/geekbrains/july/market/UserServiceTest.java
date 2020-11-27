package com.geekbrains.july.market;


import com.geekbrains.july.market.entities.User;
import com.geekbrains.july.market.repositories.UserRepository;
import com.geekbrains.july.market.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

@SpringBootTest
public class UserServiceTest {
    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    public void findOneUserTest() {
        User userFromDB = new User();
        userFromDB.setFirstName("John");
        userFromDB.setEmail("john@mail.ru");

        Mockito.doReturn(Optional.of(userFromDB))
                .when(userRepository)
                .findByFirstName("John");

        User userJohn = userService.findByFirstName("John").get();
        Assertions.assertNotNull(userJohn);
        Mockito.verify(userRepository, Mockito.times(1)).findByFirstName(ArgumentMatchers.eq("John"));
//        Mockito.verify(userRepository, Mockito.times(1)).findOneByPhone(ArgumentMatchers.any(String.class));
    }
}
