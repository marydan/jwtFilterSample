package com.stackroute.food.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.swagger.models.HttpMethod;

public class FoodJWTFilter extends GenericFilterBean{

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	 
		HttpServletRequest httprequest=(HttpServletRequest)request;
		HttpServletResponse httpresponse=(HttpServletResponse)response;
		
		httpresponse.setHeader("Access-Control-Allow-Origin", httprequest.getHeader("origin"));
		httpresponse.setHeader("Access-Control-Allow-Credential", "true");
		httpresponse.setHeader("Access-Control-Allow-Methods","POST,GET,PUT,DELETE,OPTIONS");
		
		if(httprequest.getMethod().equalsIgnoreCase(HttpMethod.OPTIONS.name()))
		{
			chain.doFilter(httprequest, httpresponse);
		}
		
		
		String authkey=httprequest.getHeader("Authorization");
		System.out.println(authkey);
		if( (authkey==null) || (!authkey.startsWith("Bearer")))
		{
			throw new ServletException("JWT key is missing");
		}
				
		String mytoken=authkey.substring(7);
				
		try
		{
			JwtParser parser=Jwts.parser().setSigningKey("jwtsecret");
			
			Jwt jwtobj=parser.parse(mytoken);
			Claims claim=(Claims) jwtobj.getBody();
			String username=claim.getSubject();
		HttpSession session=httprequest.getSession();
		session.setAttribute("username", username);
		
		}
		catch(SignatureException sig)
		{
			throw new ServletException("signature mis match");
		}
		catch(MalformedJwtException exce)
		{
			throw new ServletException("token is modified by unauthorized");
		}
		
		chain.doFilter(httprequest, httpresponse);
		
	}

}


















