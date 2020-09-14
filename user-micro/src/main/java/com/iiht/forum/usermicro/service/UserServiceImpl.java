package com.iiht.forum.usermicro.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;


import com.iiht.forum.usermicro.dao.UserDetailsRepository;
import com.iiht.forum.usermicro.document.UserDetail;
import com.iiht.forum.usermicro.dto.RegisterDto;
import com.iiht.forum.usermicro.dto.UserDetailDto;


@Service
public class UserServiceImpl implements UserService {

	private UserDetailsRepository repository;
	
		
	public UserServiceImpl(UserDetailsRepository repository) {
		// TODO Auto-generated constructor stub
		this.repository = repository;
		
	}
	
	@Override
	public UserDetailDto login(String emailId, String password) {
		return null;
	}

	@Override
	public UserDetailDto register(RegisterDto registerDto) {
		return null;
	}

	@Override
	public boolean checkAlreadyInUse(String emailId) {
		return false;
		
	}

	@Override
	public UserDetailDto getUserDetails(String id) {
		return null;
	}

	@Override
	public List<UserDetailDto> getRegisteredUserList() {
		return null;
	}

}
