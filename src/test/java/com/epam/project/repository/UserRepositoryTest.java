package com.epam.project.repository;


import com.epam.project.model.entity.User;
import com.epam.project.model.enums.Role;
import com.epam.project.model.enums.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

//@SpringBootTest
@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    public void itShouldSaveNewUser() {
        //given
        User user = User.builder().login("Olson")
                .firstName("Jecson")
                .lastName("Martin")
                .password("222")
                .phone("0555055522")
                .email("olson@gmail.com")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        underTest.save(user);
        //when
        boolean expectedId = underTest.existsById(user.getId());
        //then
        assertThat(expectedId).isTrue();
    }
    @Test
    public void itShouldFindByLogin() {
        //given
        String login1 = "Lee";
        User user = User.builder().login(login1)
                .firstName("Zhan")
                .lastName("Martin")
                .password("222")
                .phone("0555055522")
                .email("lee@gmail.com")
                .status(Status.ACTIVE)
                .role(Role.BUYER)
                .build();
        underTest.save(user);
        //when
        User login = underTest.findByLogin("Lee");
        //then
        assertThat(login).isNotNull();
    }
}