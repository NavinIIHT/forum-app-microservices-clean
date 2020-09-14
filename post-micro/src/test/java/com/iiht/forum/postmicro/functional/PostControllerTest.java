package com.iiht.forum.postmicro.functional;

import static com.iiht.forum.postmicro.testutils.TestUtils.*;

import static org.mockito.Mockito.when;
import static com.iiht.forum.postmicro.testutils.MasterData.*;

import java.io.IOException;

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

import com.iiht.forum.postmicro.controller.PostController;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.service.PostService;
import com.iiht.forum.postmicro.testutils.MasterData;


@RunWith(SpringRunner.class)
@WebMvcTest(PostController.class)
public class PostControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private PostService postService;
	
	@Test
	public void post_TestAddPost() throws Exception {
		PostDto postDto = MasterData.getPostDto();
		PostDetailDto postDetailDto = MasterData.getPostDetailDto();
		when(this.postService.addPost(postDto, "1")).thenReturn(postDetailDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/add/1")
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(postDetailDto))? "true" : "false"),	
				businessTestFile);
		
	}
	@Test
	public void post_TestAddPostBDD() throws Exception {
		final int count[] = new int[1];
		PostDto postDto = MasterData.getPostDto();
		PostDetailDto postDetailDto = MasterData.getPostDetailDto();
		when(this.postService.addPost(postDto, "1")).then(new Answer<PostDetailDto>() {

			@Override
			public PostDetailDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return postDetailDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/post/add/1")
				.content(MasterData.asJsonString(postDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
	}
	
	@Test
	public void post_TestGetPostById() throws Exception {
		
		PostDetailDto postDetailDto = MasterData.getPostDetailDto();
		String postId = postDetailDto.getPostId();
		when(this.postService.getPost(postId)).thenReturn(postDetailDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/post/get/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(postDetailDto))? "true" : "false"),	
				businessTestFile);
		
	}
	@Test
	public void post_TestGetPostByIdBDD() throws Exception {
		final int count[] = new int[1];
		PostDetailDto postDetailDto = MasterData.getPostDetailDto();
		String postId = postDetailDto.getPostId();
		when(this.postService.getPost(postId)).then(new Answer<PostDetailDto>() {

			@Override
			public PostDetailDto answer(InvocationOnMock invocation) throws Throwable {
				// TODO Auto-generated method stub
				count[0]++;
				return postDetailDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/api/post/get/" + postId)
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	
				businessTestFile);
		
		
	}
}
