package com.stackroute.food.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.stackroute.food.model.Restaurant;

@Repository
public interface RestaurantRepo extends JpaRepository<Restaurant,String>{

	Restaurant findByName(String name);
	
	Restaurant findByNameAndAddr(String name,String address);
	
	
}
