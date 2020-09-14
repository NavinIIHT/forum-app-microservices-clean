package com.iiht.forum.usermicro.boundary;

import static com.iiht.forum.usermicro.testutils.TestUtils.*;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Set;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forum.usermicro.controller.UserController;
import com.iiht.forum.usermicro.dto.LoginDto;
import com.iiht.forum.usermicro.service.UserServiceImpl;
import com.iiht.forum.usermicro.testutils.MasterData;
import com.iiht.forum.usermicro.testutils.TestUtils;


import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class BoundaryTest {
	
	
	private Validator validator;
	
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void user_TestPasswordLength() throws Exception {
		LoginDto loginDto = MasterData.getLoginDto();
		loginDto.setPassword("abc");
		Set<ConstraintViolation<LoginDto>> violations = validator.validate(loginDto);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}
	
	
}
