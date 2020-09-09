package com.stackroute.food.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.stackroute.food.exception.RestaurantAlreadyExistException;
import com.stackroute.food.exception.RestaurantNotFoundException;
import com.stackroute.food.model.Restaurant;
import com.stackroute.food.repository.RestaurantRepo;

@Service
public class RestaurantServiceImpl implements RestaurantService {
	
	@Autowired
    RestaurantRepo restaurantrepo;
	
	@Override
	public Restaurant addRestaurant(Restaurant restobj) throws RestaurantAlreadyExistException {
	
Optional<Restaurant> restaurantresult= restaurantrepo.findById(restobj.getRestid());

   if(restaurantresult.isPresent())
	   
	   throw new RestaurantAlreadyExistException();
   else
	restaurantrepo.save(restobj);
	
   return restobj;
   
   
	 
	}

	@Override
	public boolean removeRestaurant(String restid) throws RestaurantNotFoundException {
		
		
  Restaurant restfind=findByRestaurantId(restid);
  
		restaurantrepo.delete(restfind);
		
		 
		return true;
		
		
	}

	@Override
	public Restaurant findByRestaurantId(String restid) throws RestaurantNotFoundException {

		Optional<Restaurant> restaurantresult= restaurantrepo.findById(restid);
		
		if(restaurantresult.isPresent())
		return restaurantresult.get();
		
		else
			throw new RestaurantNotFoundException();
		
	}

	@Override
	public List<Restaurant> findAllRestaurant() {
		
	return restaurantrepo.findAll();
	
	}

	@Override
	public Restaurant modifyRestaurant(Restaurant restmodified) throws RestaurantNotFoundException {

		Restaurant restaurantold=findByRestaurantId(restmodified.getRestid());
		
		restaurantold.setAddr(restmodified.getAddr());
		restaurantold.setName(restmodified.getName());
		
		restaurantrepo.save(restaurantold);
		
		return restaurantold;
	}

	@Override
	public Restaurant findByRestaurantNameandAdd(String name, String address) throws RestaurantNotFoundException{
		
	Restaurant restresult=	restaurantrepo.findByNameAndAddr(name, address);
	
	if(restresult==null)
		throw new RestaurantNotFoundException();
	else
		return restresult;
		
 	}

	
}
