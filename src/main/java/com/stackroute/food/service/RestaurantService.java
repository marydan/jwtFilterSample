package com.stackroute.food.service;

import java.util.List;

import com.stackroute.food.exception.RestaurantAlreadyExistException;
import com.stackroute.food.exception.RestaurantNotFoundException;
import com.stackroute.food.model.Restaurant;

public interface RestaurantService {

	Restaurant addRestaurant(Restaurant restobj) throws RestaurantAlreadyExistException;
	boolean removeRestaurant(String restid) throws RestaurantNotFoundException;
	Restaurant findByRestaurantId(String restid) throws RestaurantNotFoundException;
	
	List<Restaurant> findAllRestaurant();
	
	Restaurant modifyRestaurant(Restaurant restmodified) throws RestaurantNotFoundException;
	
	Restaurant findByRestaurantNameandAdd(String name,String address) throws RestaurantNotFoundException;
	
	
}
