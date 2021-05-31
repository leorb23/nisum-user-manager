package com.usermanager.api.model.service.impl;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.usermanager.api.model.dao.IPhoneDao;
import com.usermanager.api.model.dao.IUserDao;
import com.usermanager.api.model.entity.Phone;
import com.usermanager.api.model.entity.User;
import com.usermanager.api.model.service.IUserService;
import com.usermanager.api.view.PhoneRequestView;
import com.usermanager.api.view.PhoneResponseView;
import com.usermanager.api.view.UserRequestView;
import com.usermanager.api.view.UserResponseView;

@Service
public class UserService implements IUserService 
{
	@Autowired
	private IUserDao userDao;
	
	@Autowired
	private IPhoneDao phoneDao;	

	@Override
	@Transactional
	public UserResponseView save(UserRequestView userRequestView) throws Exception 
	{	
		if(isValidEmail(userRequestView.getEmail()))
		{			
			if(!emailExists(userRequestView.getEmail()))	
			{
				if(isValidPassword(userRequestView.getPassword()))
				{
					User newUser = new User();				
					
					UserResponseView userResponseView = new UserResponseView();				
					
					newUser.setId(UUID.randomUUID());
					newUser.setName(userRequestView.getName());
					newUser.setEmail(userRequestView.getEmail());
					newUser.setPassword(encrypt(userRequestView.getPassword()));
					newUser.setActive(true);
					newUser.setCreated(LocalDate.now());
					newUser.setModified(LocalDate.now());
					newUser.setLastLogin(LocalDate.now());
					newUser.setToken(createJwt());
					userDao.save(newUser);				
					
					List <PhoneRequestView> userPhonesRequestView = userRequestView.getPhones();
					
					List <PhoneResponseView> userPhonesResponseView = new ArrayList<>();
					
					List <Phone> userPhones = new ArrayList<>();
					
					for (int i = 0; i < userPhonesRequestView.size(); i++) 
					{
						Phone userPhone = new Phone();
						userPhone.setNumber(userPhonesRequestView.get(i).getNumber());
						userPhone.setCityCode(userPhonesRequestView.get(i).getCityCode());
						userPhone.setContryCode(userPhonesRequestView.get(i).getContryCode());
						userPhone.setUser(newUser);					
						phoneDao.save(userPhone);
						
						userPhones.add(userPhone);
						
						PhoneResponseView userPhoneResponseView = new PhoneResponseView();
						userPhoneResponseView.setId(userPhone.getId());
						userPhoneResponseView.setNumber(userPhone.getNumber());
						userPhoneResponseView.setCityCode(userPhone.getCityCode());
						userPhoneResponseView.setContryCode(userPhone.getContryCode());
						
						userPhonesResponseView.add(userPhoneResponseView);
					}				
					
					userResponseView.setId(newUser.getId());
					userResponseView.setCreated(newUser.getCreated());
					userResponseView.setModified(newUser.getModified());
					userResponseView.setLastLogin(newUser.getLastLogin());
					userResponseView.setPhones(userPhonesResponseView);
					userResponseView.setToken(newUser.getToken());
					
					return userResponseView;
				}
				else
				{
					throw new Exception("La contraseña no es válida, debe contener al menos una letra mayúscula, letras minúsculas y mínimo dos dígitos");
				}
			}	
			else
			{				
				throw new Exception("Correo ya registrado");
			}
		}
		else
		{			
			throw new Exception("Formato incorrecto para email");
		}
	}	
	
	private String encrypt(String password)
	{
		MessageDigest md = null;
		try 
		{
			md = MessageDigest.getInstance("SHA-256");
		} 
		catch (NoSuchAlgorithmException e) 
		{		
			e.printStackTrace();
			return null;
		}
		    
		byte[] hash = md.digest(password.getBytes());
		StringBuffer sb = new StringBuffer();
		    
		for(byte b : hash) 
		{        
			sb.append(String.format("%02x", b));
		}
		    
		return sb.toString();
	}
	
	private boolean isValidEmail(String email)
	{		
        Pattern pattern = Pattern.compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"); 
        Matcher mather = pattern.matcher(email);
        return mather.find();
	}
	
	private boolean emailExists(String email)
	{		
		return userDao.findByEmail(email)!=null;
	}
	
	private boolean isValidPassword(String password)
	{
		Pattern pattern = Pattern.compile("[A-Z]{1}[a-z]+\\d{2}");
		Matcher matcher = pattern.matcher(password);
		return matcher.find();
	}
	
	public static String createJwt() throws IllegalArgumentException, UnsupportedEncodingException
	{
        Algorithm al = Algorithm.HMAC256("secretkey");
        String token = JWT.create()
                                 .withIssuer ("Emisor")
                                 .withSubject ("Usuario")
                .withClaim("userid", 1234)
                .withExpiresAt(new Date(System.currentTimeMillis()+360000))
                .sign(al);
        
        return token;        
    }

}
