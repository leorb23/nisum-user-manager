package com.usermanager.api.view;

import java.util.UUID;

public class PhoneRequestView 
{
	private UUID id = UUID.randomUUID();	
	private Long number;
	private Long citycode;
	private Long contrycode;
	
	public UUID getId() 
	{
		return id;
	}

	public void setId(UUID id) 
	{
		this.id = id;
	}

	public Long getNumber() 
	{
		return number;
	}

	public void setNumber(Long number) 
	{
		this.number = number;
	}

	public Long getCityCode() 
	{
		return citycode;
	}

	public void setCitycode(Long citycode) 
	{
		this.citycode = citycode;
	}

	public Long getContryCode() 
	{
		return contrycode;
	}

	public void setContrycode(Long contrycode) 
	{
		this.contrycode = contrycode;
	}
}
