package com.stb.sample.validator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.Errors;

import com.stb.sample.WebApplication;
import com.stb.sample.model.User;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = WebApplication.class)
public class UserValidatorTest {
	
	private String testUsername;
	private String testUser;
	private String testAuthentication;
	private String testAuth;
	private String printMessage = "Null pointer exception caught!";
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private UserValidator validator;
	private User user;
	private Errors errors;
	
	@Before
	public void setUp() {

		testUsername = "testUser";
		testUser = "test";
		testAuthentication = "testingAuth123";
		testAuth = "testing";
		
		validator = new UserValidator();
		user = new User();
		errors = new BeanPropertyBindingResult(user, "");
	}
	
	@Test
	public void testUsernameAndPasswordValidLength() {
		user.setUsername(testUsername);
		user.setPass(testAuthentication);
		user.setPassConfirm(testAuthentication);

		try {
			validator.validate(user, errors);
		} catch (NullPointerException e) {
			logger.log(Level.INFO, printMessage + e);
		} finally {
			assertFalse(errors.hasErrors());
		}
	}

	@Test
	public void testUsernameInvalidLength() {
		user.setUsername(testUser);
		user.setPass(testAuthentication);
		user.setPassConfirm(testAuthentication);

		try {
			validator.validate(user, errors);
		} catch (NullPointerException e) {
			logger.log(Level.INFO, printMessage + e);
		} finally {
			assertTrue(errors.hasErrors());
		}
	}

	@Test
	public void testPasswordInvalidLength() {
		user.setUsername(testUsername);
		user.setPass(testAuth);
		user.setPassConfirm(testAuth);

		try {
			validator.validate(user, errors);
		} catch (NullPointerException e) {
			logger.log(Level.INFO, printMessage + e);
		} finally {
			assertTrue(errors.hasErrors());
		}
	}

	@Test
	public void testPasswordNotMatching() {
		user.setUsername(testUsername);
		user.setPass(testAuthentication);
		user.setPassConfirm(testAuth);

		try {
			validator.validate(user, errors);
		} catch (NullPointerException e) {
			logger.log(Level.INFO, printMessage + e);
		} finally {
			assertTrue(errors.hasErrors());
		}

	}

}
