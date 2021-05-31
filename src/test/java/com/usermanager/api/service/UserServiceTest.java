package com.usermanager.api.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.usermanager.api.model.service.impl.UserService;
import com.usermanager.api.view.PhoneRequestView;
import com.usermanager.api.view.PhoneResponseView;
import com.usermanager.api.view.UserRequestView;
import com.usermanager.api.view.UserResponseView;

import static org.assertj.core.api.Assertions.fail;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class UserServiceTest 
{
	@InjectMocks
	private UserService userService;
	
	private UUID userId = UUID.randomUUID();
	
	private UUID phone1Id = UUID.randomUUID();
	
	private UUID phone2Id = UUID.randomUUID();
	
	@Before
	private UserRequestView userRequestView()
	{
		UserRequestView myUserRequestView = new UserRequestView();
		
		PhoneRequestView myPhoneRequestView = new PhoneRequestView();
		
		PhoneRequestView myPhoneRequestView2 = new PhoneRequestView();
		
		List<PhoneRequestView> userPhonesRequestViews = new ArrayList<>();
		
		myPhoneRequestView.setId(phone1Id);
		myPhoneRequestView.setNumber((long) 311620239);
		myPhoneRequestView.setCitycode((long) 1);
		myPhoneRequestView.setContrycode((long) 57);
		
		myPhoneRequestView2.setId(phone2Id);
		myPhoneRequestView2.setNumber((long) 7293919);
		myPhoneRequestView2.setCitycode((long) 2);
		myPhoneRequestView2.setContrycode((long) 57);
		
		userPhonesRequestViews.add(myPhoneRequestView);
		userPhonesRequestViews.add(myPhoneRequestView2);
		
		myUserRequestView.setId(userId);
		myUserRequestView.setName("Leandro Erazo");
		myUserRequestView.setEmail("leorb23@gmail.com");
		myUserRequestView.setPassword("abc123");
		myUserRequestView.setPhones(userPhonesRequestViews);
		
		return myUserRequestView;
		
	}
	
	@Before
	private UserResponseView userResponseView()
	{
		UserResponseView myUserResponseView = new UserResponseView();
		
		PhoneResponseView myPhoneResponseView = new PhoneResponseView();
		
		PhoneResponseView myPhoneResponseView2 = new PhoneResponseView();
		
		List<PhoneResponseView> userPhonesResponseView = new ArrayList<>();
		
		myPhoneResponseView.setId(phone1Id);
		myPhoneResponseView.setNumber((long) 311620239);
		myPhoneResponseView.setCityCode((long) 1);
		myPhoneResponseView.setContryCode((long) 57);
		
		myPhoneResponseView2.setId(phone2Id);
		myPhoneResponseView2.setNumber((long) 7293919);
		myPhoneResponseView2.setCityCode((long) 2);
		myPhoneResponseView2.setContryCode((long) 57);
		
		
		myUserResponseView.setId(userId);
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
		try 
		{
			UserResponseView generated = userService.save(userRequestView());
			UserResponseView expected = userResponseView();			
			Assert.assertEquals(generated.getCreated(),expected.getCreated());					
		} 
		catch (Exception e) 
		{			
			fail("No exception expected: " + e.getMessage());
		}
	}
}
