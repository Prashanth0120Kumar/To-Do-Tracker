package com.example.TaskService.Filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtFilter extends GenericFilter {
	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest request=(HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		ServletOutputStream pw=response.getOutputStream ();
		String authHeader=request.getHeader ("Authorization");
		if(request.getMethod ().equals ("OPTIONS"))
		{
			response.setStatus (HttpServletResponse.SC_OK);
			filterChain.doFilter (request,response);
		}
		else
		{
			String token =authHeader.substring (7);
			Claims claims= Jwts.parser ().setSigningKey ("mysecret").parseClaimsJws (token).getBody ();
			request.setAttribute ("claims",claims);
			filterChain.doFilter (request,response);
		}
	}
}
