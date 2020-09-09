package com.stackroute.food.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.stackroute.food.exception.RestaurantAlreadyExistException;
import com.stackroute.food.exception.RestaurantNotFoundException;
import com.stackroute.food.model.Restaurant;
import com.stackroute.food.service.RestaurantService;

import io.swagger.annotations.ApiOperation;

import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

 

@RestController
@CrossOrigin

@RequestMapping("/api/food")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantservice;
	
	
	@ApiOperation("used to add details of REstaurant in database")
	@PostMapping("/addRestaurant")
	public ResponseEntity<?> addRestnew(@RequestBody Restaurant restaurantnew)
	{
		try
		{
		Restaurant restresult=restaurantservice.addRestaurant(restaurantnew);
		return new ResponseEntity<Restaurant>(restresult,HttpStatus.OK);
		
		
		}
		catch(RestaurantAlreadyExistException excep)
		{
			
			return new ResponseEntity<String>("Restaurant Id already exist",HttpStatus.CONFLICT);
			
		}
		
	}
	
	@ApiOperation(value="delete",response=Iterable.class)
	@ApiResponses(value= {
			@ApiResponse(code=200,message="Successfully Deleted"),
			@ApiResponse(code=404,message="ID Mismatch")
	})
	
	@DeleteMapping("/deleteRestaurant/{restid}")
	public ResponseEntity<?> deleteRest(@PathVariable("restid") String restid)
	{
		
		try
		{
			restaurantservice.removeRestaurant(restid);
			return new ResponseEntity<String>("Record deleted successfully",HttpStatus.OK);
		}
		catch(RestaurantNotFoundException excep)
		{
			return new ResponseEntity<String>("Restaurant Id Not found",HttpStatus.NOT_FOUND);
		}
	}
 
	@GetMapping("/viewAllRestaurants")
	public ResponseEntity<?> viewAllDetails()
	{
		
		List<Restaurant> restaurants=restaurantservice.findAllRestaurant();
		
		return new ResponseEntity<List>(restaurants,HttpStatus.OK);
	}
	
	
	@PutMapping("/modifyRestuarant")
	public ResponseEntity<?> modiRestaurant(@RequestBody Restaurant restmodif )
	{
		try {
		Restaurant restresult=	restaurantservice.modifyRestaurant(restmodif);
			return new ResponseEntity<Restaurant>(restresult,HttpStatus.OK);
		} 
		catch (RestaurantNotFoundException e) {
			return new ResponseEntity<String>("ID not found",HttpStatus.NOT_MODIFIED);
		 
		}
	}
	
	@GetMapping("/findResturant/{restid}")
	public ResponseEntity<?> findRestaurant(@PathVariable("restid") String restid)
	{
	
		try {
			Restaurant restresutl=restaurantservice.findByRestaurantId(restid);
			return new ResponseEntity<Restaurant>(restresutl,HttpStatus.FOUND);
		} 
		catch (RestaurantNotFoundException e) {
			return new ResponseEntity<String>("Id not found",HttpStatus.NOT_FOUND);
		}
	
	}
	
	@GetMapping("/findbyaddr/{name}/{addr}")
	public ResponseEntity<?> findbyNameandAddr(@PathVariable("name") String name,@PathVariable("addr") String addr)
	{
		
		try {
		Restaurant restresult=restaurantservice.findByRestaurantNameandAdd(name, addr);
		return new ResponseEntity<Restaurant>(restresult,HttpStatus.FOUND);
		} 
		catch (RestaurantNotFoundException e) {
		 return new ResponseEntity<String>("Name and Addrees not correct",HttpStatus.NOT_FOUND);
		}
	}
}
