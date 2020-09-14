package com.iiht.forum.usermicro.testutils;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.iiht.forum.usermicro.document.UserDetail;
import com.iiht.forum.usermicro.dto.LoginDto;
import com.iiht.forum.usermicro.dto.RegisterDto;
import com.iiht.forum.usermicro.dto.UserDetailDto;

public class MasterData {

	public static RegisterDto getRegisterDto() {
		RegisterDto registerDto = new RegisterDto("dummy@mail.com", "abcdefg", "abcdefg", "Dummy", "Test", "1234567890");
		return registerDto;
	}
	
	public static UserDetail getUserDetail() {
		UserDetail userDetail = new UserDetail("1", "dummy@mail.com", "abcdefg", "Dummy", "Test", "1234567890");
		return userDetail;
	}
	
	public static UserDetailDto getUserDetailDto() {
		UserDetailDto userDetailDto = new UserDetailDto("1", "dummy@mail.com","Dummy", "Test", "1234567890");
		return userDetailDto;
	}
	
	public static List<UserDetailDto> getUserDetailDtos() {
		UserDetailDto userDetailDto1 = new UserDetailDto("1", "dummy1@mail.com", "Dummy1", "Test1", "1234567890");
		UserDetailDto userDetailDto2 = new UserDetailDto("2", "dummy2@mail.com", "Dummy2", "Test2", "1234567890");
		List<UserDetailDto> userDetailDtos = new ArrayList<UserDetailDto>();
		userDetailDtos.add(userDetailDto1);
		userDetailDtos.add(userDetailDto2);
		return userDetailDtos;
	}
	
	public static List<UserDetail> getUserDetails() {
		UserDetail userDetail1 = new UserDetail("1", "dummy1@mail.com", "abcdefg", "Dummy1", "Test1", "1234567890");
		UserDetail userDetail2 = new UserDetail("2", "dummy2@mail.com", "abcdefg", "Dummy2", "Test2", "1234567890");
		List<UserDetail> userDetails = new ArrayList<UserDetail>();
		userDetails.add(userDetail1);
		userDetails.add(userDetail2);
		return userDetails;
	}
	
	public static LoginDto getLoginDto() {
		LoginDto loginDto = new LoginDto("dummy@mail.com", "abcdefg");
		return loginDto;
	}
	
	public static LoginDto getBadLoginDto() {
		LoginDto loginDto = new LoginDto("dummy@mail.com", "123456");
		return loginDto;
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
