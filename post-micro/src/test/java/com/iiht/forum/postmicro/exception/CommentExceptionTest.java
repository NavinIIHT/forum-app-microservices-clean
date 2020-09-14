package com.iiht.forum.postmicro.exception;

import static com.iiht.forum.postmicro.testutils.TestUtils.*;
import static com.iiht.forum.postmicro.testutils.MasterData.asJsonString;
import static com.iiht.forum.postmicro.testutils.TestUtils.businessTestFile;
import static com.iiht.forum.postmicro.testutils.TestUtils.currentTest;
import static com.iiht.forum.postmicro.testutils.TestUtils.yakshaAssert;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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

import com.iiht.forum.postmicro.controller.CommentController;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.service.CommentService;
import com.iiht.forum.postmicro.testutils.MasterData;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
public class CommentExceptionTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentService commentService;
	
	@Test
	public void post_TestAddCommentException() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		commentDto.setComment("");
		CommentDetailDto commentDetailDto = MasterData.getCommentDetailDto();
		when(this.commentService.addComment(commentDto, "1",commentDetailDto.getCommentedByUser().getId())).thenReturn(commentDetailDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/comment/add/1" + commentDetailDto.getCommentedByUser().getId())
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),	
				exceptionTestFile);
		
	}
}
