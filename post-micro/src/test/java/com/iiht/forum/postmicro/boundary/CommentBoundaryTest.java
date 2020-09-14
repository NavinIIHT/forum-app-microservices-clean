package com.iiht.forum.postmicro.boundary;

import static com.iiht.forum.postmicro.testutils.TestUtils.boundaryTestFile;
import static com.iiht.forum.postmicro.testutils.TestUtils.currentTest;
import static com.iiht.forum.postmicro.testutils.TestUtils.yakshaAssert;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.testutils.MasterData;

public class CommentBoundaryTest {
private Validator validator;
	
	@BeforeEach
	public void setUp() {
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		validator = factory.getValidator();
	}

	@Test
	public void post_TestCommentDataBoundary() throws Exception {
		CommentDto commentDto =  MasterData.getCommentDto();
		commentDto.setComment("");
		Set<ConstraintViolation<CommentDto >> violations = validator.validate(commentDto);
		yakshaAssert(currentTest(),!violations.isEmpty()? true : false,boundaryTestFile);
	}

}
