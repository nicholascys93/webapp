package com.stb.sample.web;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.stb.sample.WebApplication;
import com.stb.sample.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=WebApplication.class)
@AutoConfigureMockMvc

public class UserControllerTest {
	
	@Autowired
	private MockMvc mock;
	
	@MockBean
    private UserService service;
	
	private String printMessage = "Null pointer exception caught!";
	private static final Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Test
	public void userTestLogin() {
	 
		try{
			mock.perform(get("/login").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
		} catch(Exception e) {
			logger.log(Level.INFO, printMessage + e);
		}
	      
	}
}
