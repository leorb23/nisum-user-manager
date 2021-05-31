package com.usermanager.api.model.entity;

import java.io.Serializable;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "phones")
public class Phone implements Serializable
{
	
	private static final long serialVersionUID = 6029576896947591751L;
	
	@Id	
	private UUID id = UUID.randomUUID();	
	private Long number;
	private Long cityCode;
	private Long contryCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")	
	private User user;

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
		return cityCode;
	}

	public void setCityCode(Long citycode) 
	{
		this.cityCode = citycode;
	}

	public Long getContryCode() 
	{
		return contryCode;
	}

	public void setContryCode(Long contrycode) 
	{
		this.contryCode = contrycode;
	}

	public User getUser() 
	{
		return user;
	}

	public void setUser(User user) 
	{
		this.user = user;
	}
	
	
}
