package com.stb.sample.service;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.stb.sample.WebApplication;
import com.stb.sample.model.User;
import com.stb.sample.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserServiceImplIntegrationTest {
	 
	private String testUsername = "testUser";
	
    @Autowired
    private UserService userService;
 
    @MockBean
    private UserRepository userRepository;
    
	@Before
	public void setUp() {
		User user  = new User();
		user.setUsername(testUsername);
		 
		Mockito.when(userRepository.findByUsername(user.getUsername())).thenReturn(user);
	}

	@Test
	public void testRegisteredUser() {
	    User found = userService.findByUsername(testUsername);
	    assertEquals(found.getUsername(), testUsername);
	}

}
