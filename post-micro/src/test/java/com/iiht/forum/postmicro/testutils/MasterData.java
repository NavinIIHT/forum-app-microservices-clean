package com.iiht.forum.postmicro.testutils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;


public class MasterData {

	public static PostDto getPostDto() {
		PostDto postDto = new PostDto("test", "Testing Post", "#test");
		return postDto;
	}
	
	public static PostDetailDto getPostDetailDto() {
		List<CommentDetailDto> comments = new ArrayList<CommentDetailDto>();
		PostDetailDto postDetailDto = new PostDetailDto("1", "test", "#test", "Testing Post", getUserDetailDto(), LocalDateTime.now(), 1, comments);
		
		return postDetailDto;
	}
	
	public static CommentDto getCommentDto() {
		CommentDto commentDto = new CommentDto("Test Comment", "#test");
		return commentDto;
	}
	
	public static CommentDetailDto getCommentDetailDto() {
		CommentDetailDto commentDetailDto = new CommentDetailDto("1", "Test Comment", "test", getUserDetailDto(), LocalDateTime.now(), 1);
		return commentDetailDto;
	}
	
	public static UserDetailDto getUserDetailDto() {
		UserDetailDto userDetailDto = new UserDetailDto("1", "first@mail.com", "First", "Last", "1234567890");
		return userDetailDto;
	}
	
	
	
	public static String asJsonString(final Object obj) {
	    try {
	        final ObjectMapper mapper = new ObjectMapper();
	        final String jsonContent = mapper.writeValueAsString(obj);
	        return jsonContent;
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}  
}
