package com.stackroute.food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import com.stackroute.food.filter.FoodJWTFilter;

@SpringBootApplication
public class FoodAppJpaApplication {

	public static void main(String[] args) {
		SpringApplication.run(FoodAppJpaApplication.class, args);
	}
	
	@Bean
	public FilterRegistrationBean getFilterbean()
	{
		UrlBasedCorsConfigurationSource urlcorsconfig=new UrlBasedCorsConfigurationSource();
		
		CorsConfiguration corsconfig=new CorsConfiguration();
		corsconfig.setAllowCredentials(true);
		corsconfig.addAllowedHeader("*");
		corsconfig.addAllowedMethod("*");
		corsconfig.addAllowedOrigin("*");
		urlcorsconfig.registerCorsConfiguration("/**", corsconfig);
		FilterRegistrationBean filterbean=new FilterRegistrationBean(new CorsFilter(urlcorsconfig));
		filterbean.setFilter(new FoodJWTFilter());
		filterbean.addUrlPatterns("/api/food/addRestaurant","/api/food/deleteRestaurant");
		return filterbean;
	}

}
