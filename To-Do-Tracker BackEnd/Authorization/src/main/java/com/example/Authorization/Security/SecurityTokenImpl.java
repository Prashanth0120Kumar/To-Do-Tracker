package com.example.Authorization.Security;

import com.example.Authorization.Domain.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Component
public class SecurityTokenImpl implements SecurityTokenGenerator
{
	public String createToken(User user){
		Map<String,Object> obj=new HashMap<>();
		obj.put("UserID",user.getUserEmail ());
		return generateToken(obj, user.getUserEmail ());
	}
	
	public String generateToken(Map<String,Object> claims,String subject) {
		String jwtToken;
		jwtToken = Jwts.builder().setIssuer("Products")
									.setClaims(claims)
									.setSubject(subject)
									.setIssuedAt(new Date())
									.signWith(SignatureAlgorithm.HS256,"mysecret")
									.compact();
		return jwtToken;
	}
}
