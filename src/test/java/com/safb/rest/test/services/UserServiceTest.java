package com.safb.rest.test.services;

import com.safb.rest.config.RestAppConfig;
import com.safb.rest.dao.UserDao;
import com.safb.rest.dto.UserCreateDto;
import com.safb.rest.entity.User;
import com.safb.rest.exceptions.UserServiceException;
import com.safb.rest.model.UserCreateModel;
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
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(classes = RestAppConfig.class)
@WebAppConfiguration
public class UserServiceTest
{

  @InjectMocks
  private UserServiceImpl userService;

  @Mock
  private UsersRepository usersRepository;

  @Mock
  private UserDao userDao;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private ModelMapper modelMapper;

  private MockMvc mockMvc;

  @Before
  public void setup()
  {
    MockitoAnnotations.initMocks(this);
  }

  // GET
  @Test
  public void test_get_user_by_public_id()
  {
    String publicId = "irfkrIfmsfoFoimFoiF90FokODDfjm";

    User user = new User();
    user.setUserId(1);
    user.setPublicId(publicId);
    user.setEmail("john.doe@gmail.com");

    Mockito.when(usersRepository.findByPublicId(publicId)).thenReturn(user);

    User returnedUser = userService.getUser(publicId);

    Assert.assertEquals(Integer.valueOf(1), returnedUser.getUserId());
    Assert.assertEquals("irfkrIfmsfoFoimFoiF90FokODDfjm", returnedUser.getPublicId());
    Assert.assertEquals("john.doe@gmail.com", returnedUser.getEmail());
  }

  @Test(expected = UserServiceException.class)
  public void test_get_user_pass_null_expect_exception()
  {
    userService.getUser(null);
  }

  // CREATE
  @Test(expected = UserServiceException.class)
  public void test_create_user_pass_null_expect_exception()
  {
    userService.createUser(null);
  }

  @Test
  public void test_create_user()
  {
    User user = new User();
    user.setPublicId("ufKiFmldOelwdspS39Soe9rRF3dvcd");
    user.setEmail("john.doe@test.com");
    user.setFirstName("John");
    user.setLastName("Doe");
    user.setPassword("Test-1234");

    UserCreateModel userCreateModel = new UserCreateModel();
    userCreateModel.setEmail("john.doe@test.com");
    userCreateModel.setPassword("Test-1234");

    UserCreateDto userCreateDto = userService.createUser(userCreateModel);

    Assert.assertNotNull(userCreateDto.getEmail());
    Assert.assertNotNull(userCreateDto.getPublicId());
    Assert.assertNotNull(user.getPassword());
  }

  // UPDATE
  @Test(expected = UserServiceException.class)
  public void test_update_user_pass_null_expect_exception()
  {
    Mockito.when(userDao.updateUser(null)).thenReturn(false);

    userService.updateUser(null);
  }

  // DELETE
  @Test(expected = UserServiceException.class)
  public void test_delete_pass_null_expect_exception()
  {
    userService.deleteUser(null);
  }

  // OTHER
  @Test
  public void test_is_user_exist_with_invalid_email_return_false()
  {
    String email = "test@gmail.com";

    Mockito.when(usersRepository.findByEmail(email)).thenReturn(null);

    Assert.assertFalse(userService.isUserExistWithEmail(email));
  }

  @Test
  public void test_is_user_exist_with_valid_email_return_true()
  {
    String email = "test@gmail.com";

    User returnedUser = new User();
    returnedUser.setEmail(email);

    Mockito.when(usersRepository.findByEmail(email)).thenReturn(returnedUser);

    Assert.assertTrue(userService.isUserExistWithEmail(email));
    Assert.assertEquals(email, returnedUser.getEmail());
  }

  @Test
  public void test_is_user_exist_with_invalid_public_id_return_false()
  {
    String publicId = "irfkrIfmsfoFoimFoiF90FokODDfjm";

    Mockito.when(usersRepository.findByPublicId(publicId)).thenReturn(null);

    Assert.assertFalse(userService.isUserExistWithPublicId(publicId));
  }

  @Test
  public void test_is_user_exist_with_valid_public_id_return_true()
  {
    String publicId = "irfkrIfmsfoFoimFoiF90FokODDfjm";

    User returnedUser = new User();
    returnedUser.setPublicId(publicId);

    Mockito.when(usersRepository.findByPublicId(publicId)).thenReturn(returnedUser);

    Assert.assertTrue(userService.isUserExistWithPublicId(publicId));
    Assert.assertEquals(publicId, returnedUser.getPublicId());

  }
}
