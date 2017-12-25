package com.mondiamedia.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mondiamedia.exception.SubscriptionException;
import com.mondiamedia.model.JTW.JwtAuthenticationResponse;
import com.mondiamedia.model.user.User;
import com.mondiamedia.repositories.UserRepository;
import com.mondiamedia.security.JwtTokenUtil;

import io.swagger.annotations.Api;

@RestController
@RequestMapping("/user")
@Api(value="User EndPoint",description="User Endpoints")
public class UserController {

	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
    private JwtTokenUtil jwtTokenUtil;
    private UserDetailsService userDetailsService;
	
	
	public UserController(UserRepository endUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder,JwtTokenUtil jwtTokenUtil,UserDetailsService userDetailsService) {
			this.userRepository = endUserRepository;
			this.bCryptPasswordEncoder = bCryptPasswordEncoder;
			this.jwtTokenUtil=jwtTokenUtil;
			this.userDetailsService= userDetailsService;
	}
	
	@RequestMapping(value="/signup",method=RequestMethod.POST)
	public void signup(@RequestBody User user){
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		userRepository.save(user);
	}
	
	 @RequestMapping(value = "/login", method = RequestMethod.POST)
	 public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody User user) throws AuthenticationException {
		  UserDetails userDetails=null;
			 try {
				 userDetails = userDetailsService.loadUserByUsername(user.getUsername());
			 }catch(UsernameNotFoundException ex) {
				 throw new SubscriptionException(HttpStatus.NOT_FOUND,"there is no user with username : "+user.getUsername());
			 }
		      final String token = jwtTokenUtil.generateToken(userDetails);
		      return ResponseEntity.ok(new JwtAuthenticationResponse(token));
	    }
	
	
}
