package com.usermanager.api.controller;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.usermanager.api.model.service.IUserService;
import com.usermanager.api.view.PhoneRequestView;
import com.usermanager.api.view.PhoneResponseView;
import com.usermanager.api.view.UserRequestView;
import com.usermanager.api.view.UserResponseView;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserControllerTest 
{
	@InjectMocks
	private UserController userController;
	
	@Mock
	private IUserService userService;
	
	private UserRequestView userRequestView()
	{
		UserRequestView myUserRequestView = new UserRequestView();
		
		PhoneRequestView myPhoneRequestView = new PhoneRequestView();
		
		PhoneRequestView myPhoneRequestView2 = new PhoneRequestView();
		
		List<PhoneRequestView> userPhonesRequestViews = new ArrayList<>();
		
		myPhoneRequestView.setId(UUID.randomUUID());
		myPhoneRequestView.setNumber((long) 311620239);
		myPhoneRequestView.setCitycode((long) 1);
		myPhoneRequestView.setContrycode((long) 57);
		
		myPhoneRequestView2.setId(UUID.randomUUID());
		myPhoneRequestView2.setNumber((long) 7293919);
		myPhoneRequestView2.setCitycode((long) 2);
		myPhoneRequestView2.setContrycode((long) 57);
		
		userPhonesRequestViews.add(myPhoneRequestView);
		userPhonesRequestViews.add(myPhoneRequestView2);
		
		myUserRequestView.setId(UUID.randomUUID());
		myUserRequestView.setName("Leandro Erazo");
		myUserRequestView.setEmail("leorb23@gmail.com");
		myUserRequestView.setPassword("abc123");
		myUserRequestView.setPhones(userPhonesRequestViews);
		
		return myUserRequestView;
		
	}	
	
	private UserResponseView userResponseView()
	{
		UserResponseView myUserResponseView = new UserResponseView();
		
		PhoneResponseView myPhoneResponseView = new PhoneResponseView();
		
		PhoneResponseView myPhoneResponseView2 = new PhoneResponseView();
		
		List<PhoneResponseView> userPhonesResponseView = new ArrayList<>();
		
		myPhoneResponseView.setId(UUID.randomUUID());
		myPhoneResponseView.setNumber((long) 311620239);
		myPhoneResponseView.setCityCode((long) 1);
		myPhoneResponseView.setContryCode((long) 57);
		
		myPhoneResponseView2.setId(UUID.randomUUID());
		myPhoneResponseView2.setNumber((long) 7293919);
		myPhoneResponseView2.setCityCode((long) 2);
		myPhoneResponseView2.setContryCode((long) 57);
		
		
		myUserResponseView.setId(UUID.randomUUID());
		myUserResponseView.setCreated(LocalDate.now());
		myUserResponseView.setActive(true);
		myUserResponseView.setLastLogin(LocalDate.now());
		myUserResponseView.setModified(LocalDate.now());
		myUserResponseView.setToken("TokenTest");
		
		myUserResponseView.setPhones(userPhonesResponseView);
		
		return myUserResponseView;
	}
	
	@Test
    public void testSave()
    {
        try {
			when(userService.save(userRequestView())).thenReturn(userResponseView());
			ResponseEntity<?> response = this.userController.save(userRequestView());
	        assertThat(response.getBody()).isEqualTo(userController.save(userRequestView()));
		} catch (Exception e) 
        {
			fail("No exception expected: " + e.getMessage());
		}
        
    }
}
