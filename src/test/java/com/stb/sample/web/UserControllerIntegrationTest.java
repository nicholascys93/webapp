package com.stb.sample.web;

import static org.junit.Assert.assertNotNull;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.stb.sample.WebApplication;
import com.stb.sample.repository.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {

	@MockBean
	private UserRepository userRepository;

	@Autowired
	UserController userController;

	@Autowired
	private MockMvc mockMvc;
	
	private String printMessage = "Null pointer exception caught!";
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

	@Test
	public void whenUserControllerInjectedThenNotNull() {
		try{
			assertNotNull(userController);
		} catch(NullPointerException e) {
			logger.log(Level.INFO, printMessage + e);
		}
	}

	@Test
	public void whenGetRequestToUsersThenCorrectResponse() {
		try{
			mockMvc.perform(MockMvcRequestBuilders.get("/login").contentType(MediaType.TEXT_PLAIN))
				.andExpect(MockMvcResultMatchers.status().isOk());
		} catch(Exception e) {
			logger.log(Level.INFO, printMessage + e);
		}
	}

}
