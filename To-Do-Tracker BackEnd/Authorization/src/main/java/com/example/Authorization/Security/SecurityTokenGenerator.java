package com.example.Authorization.Security;

import com.example.Authorization.Domain.User;

public interface SecurityTokenGenerator{
	String createToken(User user);
}
