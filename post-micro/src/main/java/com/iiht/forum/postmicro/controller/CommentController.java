package com.iiht.forum.postmicro.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.dto.exception.ExceptionDto;
import com.iiht.forum.postmicro.exception.CommentException;
import com.iiht.forum.postmicro.exception.CommentNotFoundException;
import com.iiht.forum.postmicro.exception.PostException;
import com.iiht.forum.postmicro.exception.PostNotFoundException;
import com.iiht.forum.postmicro.service.CommentService;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/comment")
public class CommentController {

	private CommentService commentService;
	public CommentController(CommentService commentService) {
		// TODO Auto-generated constructor stub
		this.commentService = commentService;
	}
	@PostMapping("/add/{postId}/{userId}")
	public ResponseEntity<CommentDetailDto> addComment(@PathVariable("postId") String postId, 
													   @PathVariable("userId") String userId,
													   @RequestBody CommentDto comment){
		return null;
	}
	
	@GetMapping("/like/{commentId}")
	public ResponseEntity<Integer> addLike(@PathVariable("commentId") String commentId){
		return null;
	}
	
	@ExceptionHandler(CommentNotFoundException.class)
	public ResponseEntity<ExceptionDto> commentNotFoundExceptionHandler(CommentNotFoundException ex){
		System.out.println("Inside handler");
		ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(), HttpStatus.NOT_FOUND.value(), System.currentTimeMillis());
		ResponseEntity<ExceptionDto> response = new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.NOT_FOUND);
		return response;
	}
	@ExceptionHandler(CommentException.class)
	public ResponseEntity<ExceptionDto> commentExceptionHandler(CommentException ex){
		System.out.println("Inside handler");
		ExceptionDto exceptionDto = new ExceptionDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value(), System.currentTimeMillis());
		ResponseEntity<ExceptionDto> response = new ResponseEntity<ExceptionDto>(exceptionDto, HttpStatus.BAD_REQUEST);
		return response;
	}
	
}
