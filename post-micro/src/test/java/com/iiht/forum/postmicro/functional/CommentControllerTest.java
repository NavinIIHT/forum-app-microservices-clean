package com.iiht.forum.postmicro.functional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.iiht.forum.postmicro.controller.CommentController;
import com.iiht.forum.postmicro.controller.PostController;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.service.CommentService;
import com.iiht.forum.postmicro.service.PostService;
import com.iiht.forum.postmicro.testutils.MasterData;

import static com.iiht.forum.postmicro.testutils.MasterData.asJsonString;
import static com.iiht.forum.postmicro.testutils.TestUtils.*;
import static org.mockito.Mockito.when;

import java.io.IOException;

@RunWith(SpringRunner.class)
@WebMvcTest(CommentController.class)
public class CommentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private CommentService commentService;
	
	@Test
	public void post_TestAddComment() throws Exception {
		CommentDto commentDto = MasterData.getCommentDto();
		CommentDetailDto commentDetailDto = MasterData.getCommentDetailDto();
		when(this.commentService.addComment(commentDto, "1",commentDetailDto.getCommentedByUser().getId())).thenReturn(commentDetailDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/comment/add/1" + commentDetailDto.getCommentedByUser().getId())
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(commentDetailDto))? "true" : "false"),	
				businessTestFile);
		
	}
	@Test
	public void post_TestAddCommentBDD() throws Exception {
		final int count[] = new int[1];
		CommentDto commentDto = MasterData.getCommentDto();
		CommentDetailDto commentDetailDto = MasterData.getCommentDetailDto();
		when(this.commentService.addComment(commentDto, "1",commentDetailDto.getCommentedByUser().getId())).then(new Answer<CommentDetailDto>() {

			@Override
			public CommentDetailDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return commentDetailDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/comment/add/1" + commentDetailDto.getCommentedByUser().getId())
				.content(MasterData.asJsonString(commentDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	
	}
	
}
