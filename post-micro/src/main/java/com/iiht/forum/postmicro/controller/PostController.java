package com.iiht.forum.postmicro.controller;



import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailListDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;
import com.iiht.forum.postmicro.dto.exception.ExceptionDto;
import com.iiht.forum.postmicro.exception.PostException;
import com.iiht.forum.postmicro.exception.PostNotFoundException;
import com.iiht.forum.postmicro.service.PostService;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/post")
public class PostController {

	private PostService postService;
	public PostController(PostService postService) {
		// TODO Auto-generated constructor stub
		this.postService = postService;
	}
	
	@PostMapping("/add/{userId}")
	@HystrixCommand(fallbackMethod = "defaultAdd")
	public ResponseEntity<PostDetailDto> addPost(@PathVariable("userId") String userId, @Valid @RequestBody PostDto post, BindingResult result){
		return null;
	}
	public ResponseEntity<PostDetailDto> defaultAdd(String userId,  PostDto post) {
		return null;
		
	}
	
	@GetMapping("/get/{postId}")
	public ResponseEntity<PostDetailDto> getPost(@PathVariable("postId") String postId){
		return null;
	}
	
	@GetMapping("/get-all/{userId}")
	public ResponseEntity<PostDetailListDto> getAllPostsForUser(@PathVariable("userId") String userId){
		return null;
	}
	
	@GetMapping("/like/{postId}")
	public ResponseEntity<Integer> addLike(@PathVariable("postId") String postId){
		return null;
	}
	
	@ExceptionHandler(PostNotFoundException.class)
	public ResponseEntity<ExceptionDto> postNotFoundExceptionHandler(PostNotFoundException ex){
		System.out.println("Inside handler");
		ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		ResponseEntity<ExceptionDto> response = new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
		return response;
	}
	@ExceptionHandler(PostException.class)
	public ResponseEntity<ExceptionDto> postExceptionHandler(PostException ex){
		System.out.println("Inside handler");
		ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<ExceptionDto> response = new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
}














