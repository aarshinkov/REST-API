package com.safb.rest.test.services;

import com.safb.rest.config.RestAppConfig;
import com.safb.rest.dao.UserDao;
import com.safb.rest.entity.User;
import com.safb.rest.repository.UsersRepository;
import com.safb.rest.services.UserServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = RestAppConfig.class)
@WebAppConfiguration
public class UserServiceTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UsersRepository usersRepository;

    @Mock
    private UserDao userDao;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test_get_user_by_public_id() {
        User user = new User();
        user.setUserId(1);
        user.setPublicId("8ijErf");
        user.setEmail("john.doe@gmail.com");

        Mockito.when(usersRepository.findByPublicId("8ijErf")).thenReturn(user);

        User returnedUser = userService.getUser("8ijErf");

        Assert.assertEquals(Integer.valueOf(1), returnedUser.getUserId());
        Assert.assertEquals("8ijErf", returnedUser.getPublicId());
        Assert.assertEquals("john.doe@gmail.com", returnedUser.getEmail());
    }
}
