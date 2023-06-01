package com.user.service.app;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.user.service.app.entity.User;
import com.user.service.app.exception.UserException;
import com.user.service.app.repository.UserRepository;
import com.user.service.app.service.UserService;

@SpringBootTest
@RunWith(SpringRunner.class)
class UserServiceApplicationTests {

	@Autowired
	private UserService userService;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	void createUserTest() throws UserException {
		
		User user = new User();
		user.setUserId("1234");
		user.setName("Demo");
		user.setEmail("demo@gmail.com");
		user.setAge(12);
		user.setAbout("Demo");
		
		when(userRepository.save(user)).thenReturn(user);
		assertEquals(user, userService.createUser(user));
	}
	
	@Test
	void createUserWithExceptionTest() {
		
		User user = new User();
		user.setUserId("1234");
		user.setName("Demo");
		user.setEmail("demo@gmail.com");
		user.setAge(12);
		user.setAbout("Demo");
		
		when(userRepository.save(user)).thenReturn(null);
		assertThrows(UserException.class, 
				() ->{
					userService.createUser(user);
				});
	}
	@Test
	void getAllUsersTest() throws UserException {
		
		User user1 = new User();
		user1.setUserId("1234");
		user1.setName("Demo");
		user1.setEmail("demo@gmail.com");
		user1.setAge(12);
		user1.setAbout("Demo");
		
		User user2 = new User();
		user2.setUserId("12345");
		user2.setName("Demo2");
		user2.setEmail("demo2@gmail.com");
		user2.setAge(13);
		user2.setAbout("Demo2");
		
		when(userRepository.findAll()).thenReturn(Stream
				.of(user1,user2).collect(Collectors.toList()));
		assertEquals(2, userService.getAllUsers().size());
	}
	
	@Test
	void getAllUsersWithExceptionTest() {
		
		User user1 = new User();
		user1.setUserId("1234");
		user1.setName("Demo");
		user1.setEmail("demo@gmail.com");
		user1.setAge(12);
		user1.setAbout("Demo");
		
		User user2 = new User();
		user2.setUserId("12345");
		user2.setName("Demo2");
		user2.setEmail("demo2@gmail.com");
		user2.setAge(13);
		user2.setAbout("Demo2");
		
		when(userRepository.findAll()).thenReturn(Collections.emptyList());
		assertThrows(UserException.class,
				() ->{
					userService.getAllUsers();
				});
	}
	
	@Test
	void getUserByIdTest() throws UserException {
		
		User user = new User();
		user.setUserId("1234");
		user.setName("Demo");
		user.setEmail("demo@gmail.com");
		user.setAge(12);
		user.setAbout("Demo");
		
		when(userRepository.findById(anyString())).thenReturn(Optional.of(user));
		assertEquals(Optional.of(user).get(), userService.getUserById(anyString()));
	}
	
	@Test
	void getUserByIdWithExceptionTest() {
	
		User user = new User();
		user.setUserId("1234");
		user.setName("Demo");
		user.setEmail("demo@gmail.com");
		user.setAge(12);
		user.setAbout("Demo");
		
		when(userRepository.findById(anyString())).thenReturn(null);
		assertThrows(UserException.class, 
				() ->{
					userService.getUserById(anyString());
				});
	}
	
	
	

}
