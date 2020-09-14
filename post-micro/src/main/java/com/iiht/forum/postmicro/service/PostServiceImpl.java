package com.iiht.forum.postmicro.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.websocket.server.ServerEndpoint;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.iiht.forum.postmicro.dao.PostRepository;
import com.iiht.forum.postmicro.document.Post;
import com.iiht.forum.postmicro.dto.CommentDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailDto;
import com.iiht.forum.postmicro.dto.PostDetailListDto;
import com.iiht.forum.postmicro.dto.PostDto;
import com.iiht.forum.postmicro.dto.UserDetailDto;
import com.iiht.forum.postmicro.feignproxy.UserProxy;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Service
public class PostServiceImpl implements PostService {

	
	
	// private static String USER_URL = "http://localhost:9091/api/user";
	// private RestTemplate restTemplate;
	private CommentService commentService;
	private PostRepository repository;
	private UserProxy proxy;
	public PostServiceImpl(PostRepository repository, 
						   CommentService commentService,
						   UserProxy proxy) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		this.commentService = commentService;
		this.proxy = proxy;
	}
	
	@Override
	
	public PostDetailDto addPost(PostDto post, String userId) {
		return null;
	}
	
	

	@Override
	public PostDetailDto getPost(String postId) {
		return null;
	}

	@Override
	public Integer addLike(String postId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PostDetailListDto getPosts(String userId) {
		return null;
	}

}
