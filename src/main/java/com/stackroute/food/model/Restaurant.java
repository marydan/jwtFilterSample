package com.stackroute.food.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import io.swagger.annotations.ApiModelProperty;

@Entity
public class Restaurant {
	
	@ApiModelProperty(notes="Restaurant Id")
	@Id
	String restid;
	
	@ApiModelProperty(notes="Restaurant name")
	String name;
	
	
	
	String addr;


	
	public String getRestid() {
		return restid;
	}

	public void setRestid(String restid) {
		this.restid = restid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	

}
