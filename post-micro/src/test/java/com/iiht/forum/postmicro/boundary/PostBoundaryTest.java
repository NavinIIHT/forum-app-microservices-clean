package com.iiht.forum.postmicro.boundary;

import static com.iiht.forum.postmicro.testutils.TestUtils.*;

import java.io.IOException;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.testutils.MasterData;


public class PostBoundaryTest {
	
	private Validator validator;
	
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}
	
	@Test
	public void post_TestPostDataBoundary() throws Exception {
		PostDto postDto =  MasterData.getPostDto();
		postDto.setPost("");
		Set<ConstraintViolation<PostDto>> violations = validator.validate(postDto);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}

}
