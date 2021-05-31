package com.usermanager.api.controller;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.usermanager.api.model.service.IUserService;
import com.usermanager.api.view.UserRequestView;
import com.usermanager.api.view.UserResponseView;

@RestController
@RequestMapping("user-manager/users/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController 
{
	@Autowired
	private IUserService userService;
		
	@PostMapping("/save")	
	public ResponseEntity<?> save(@RequestBody UserRequestView userRequestView)
	{
		Map<String, Object> response = new HashMap<>();
		try 
		{
			UserResponseView newUser = userService.save(userRequestView);
			newUser.getPhones();
			return new ResponseEntity<UserResponseView>(newUser, HttpStatus.OK);
		}		
		catch (Exception e) 
		{			
			response.put("mensaje", e.getMessage());
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
	}
}
