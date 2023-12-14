package com.microservice.ged.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.microservice.ged.beans.LoginUser;

@ContextConfiguration(classes = LoginController.class)
@ExtendWith(SpringExtension.class)
public class LoginUserUnitTest {

	@Autowired
	private LoginController loginController;
	
	@Test
	@DisplayName("Test Login function")
	void loginUserTest(String userName, String pwd) {
		//loginController.logUser(userName, pwd);
	}

}
