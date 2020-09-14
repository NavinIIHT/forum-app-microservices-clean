package com.iiht.forum.usermicro.functional;

import static org.mockito.Mockito.*;
import static com.iiht.forum.usermicro.testutils.TestUtils.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static com.iiht.forum.usermicro.testutils.MasterData.*;



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


import com.iiht.forum.usermicro.controller.UserController;
import com.iiht.forum.usermicro.dto.LoginDto;
import com.iiht.forum.usermicro.dto.RegisterDto;
import com.iiht.forum.usermicro.dto.UserDetailDto;
import com.iiht.forum.usermicro.service.UserServiceImpl;
import com.iiht.forum.usermicro.testutils.MasterData;



@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private UserServiceImpl userService;
	
	@Test
	public void user_TestUserRegistration() throws Exception {
		
		RegisterDto registerDto = MasterData.getRegisterDto();
		UserDetailDto userDetailDto = MasterData.getUserDetailDto();
		when(this.userService.register(registerDto)).thenReturn(userDetailDto);
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
				.content(MasterData.asJsonString(registerDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(userDetailDto))? "true" : "false"),	businessTestFile);
		
	}
	
	@Test
	public void user_TestUserRegistrationBDD() throws Exception {
		final int count[] = new int[1];
		RegisterDto registerDto = MasterData.getRegisterDto();
		UserDetailDto userDetailDto = MasterData.getUserDetailDto();
		when(this.userService.register(registerDto)).then(new Answer<UserDetailDto>() {

			@Override
			public UserDetailDto answer(InvocationOnMock invocation) throws Throwable {
				count[0]++;
				return userDetailDto;
			}
		});
				RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/api/user/register")
				.content(MasterData.asJsonString(registerDto))
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON);
				
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		 
		yakshaAssert(currentTest(), 
				count[0] == 1? true : false,	businessTestFile);
		
	}
	
	@Test
	public void user_TestLoginSuccess() throws Exception {
		
		when(userService.login("dummy@mail.com", "abcdefg"))
		.thenReturn(MasterData.getUserDetailDto());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/login")
										.content(MasterData.asJsonString(MasterData.getLoginDto()))
										.contentType(MediaType.APPLICATION_JSON)
										.accept(MediaType.APPLICATION_JSON);
										
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		
					
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(MasterData.getUserDetailDto()))? "true" : "false"), 
					businessTestFile);
	}
	
	
	
	@Test
	public void user_TestGetUserDetailsSuccess() throws Exception {
		when(userService.getUserDetails("1"))
		.thenReturn(MasterData.getUserDetailDto());
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/1")
										.accept(MediaType.APPLICATION_JSON);
										
		MvcResult result = mockMvc.perform(requestBuilder).andReturn();
					
		yakshaAssert(currentTest(), 
				(result.getResponse().getContentAsString().contentEquals(asJsonString(MasterData.getUserDetailDto()))? "true" : "false"), 
					businessTestFile);
	}
	
	
}
