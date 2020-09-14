package com.iiht.forum.postmicro.exception;

import static com.iiht.forum.postmicro.testutils.MasterData.asJsonString;
import static com.iiht.forum.postmicro.testutils.TestUtils.*;
import static org.mockito.Mockito.when;

import java.io.IOException;

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

import com.iiht.forum.postmicro.controller.PostController;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.service.PostService;
import com.iiht.forum.postmicro.testutils.MasterData;

@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostExceptionTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostService postService;
	
	@Test
	public void post_TestAddPostException() throws Exception {
		PostDto postDto = MasterData.getPostDto();
		postDto.setPost("");
		PostDetailDto postDetailDto = MasterData.getPostDetailDto();
		when(this.postService.addPost(postDto, "1")).thenReturn(postDetailDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/add/1")
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
					(result.getResponse().getStatus() == HttpStatus.BAD_REQUEST.value() ? "true" : "false"),	
					 exceptionTestFile);
		
	}
	
	
	@Test
	public void post_TestGetPostByIdException() throws Exception {
		
		
		when(this.postService.getPost("2")).thenReturn(null);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/post/get/2")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getStatus() == HttpStatus.NOT_FOUND.value() ? "true" : "false"),	
				exceptionTestFile);
		
	}
	
}
