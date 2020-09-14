package com.iiht.forum.postmicro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iiht.forum.postmicro.dao.CommentRepository;
import com.iiht.forum.postmicro.document.Comment;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.CommentDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;
import com.iiht.forum.postmicro.feignproxy.UserProxy;

@Service
public class CommentServiceImpl implements CommentService {

	private static String USER_URL = "http://localhost:9091/api/user";
	
	// private RestTemplate restTemplate;
	private CommentRepository repository;
	private UserProxy proxy;
	
	public CommentServiceImpl(CommentRepository repository, 
							  UserProxy proxy) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		this.proxy = proxy;
		
	}
	
	@Override
	public CommentDetailDto addComment(CommentDto comment, String postId, String userId) {
		return null;
	}

	@Override
	public CommentDetailDto getComment(String commentId) {
		return null;
	}

	@Override
	public Integer addLike(String commentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<CommentDetailDto> getComments(String postId) {
		return null;
	}

}
