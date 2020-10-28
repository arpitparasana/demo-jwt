package com.example.demojwt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demojwt.config.JwtTokenUtil;
import com.example.demojwt.mapper.IndividualMapper;
import com.example.demojwt.model.AuthenticationRequest;
import com.example.demojwt.model.AuthenticationResponse;
import com.example.demojwt.service.MyUserDetailsService;

@RestController
public class DashboardController {

	Logger logger = LoggerFactory.getLogger(DashboardController.class);
	
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	MyUserDetailsService userDetailsService;

	@Autowired
	JwtTokenUtil jwtUtil;
	
	@Autowired
	IndividualMapper individualMapper;

	@GetMapping("/home")
	public String getDashboard() {
		return "Welcome to Admin Dashboard!!";
	}
	
	@GetMapping("/public")
	public String getPublicDashboard() {
		//logger.error("Error happened!!");
		String name = individualMapper.getIndividualId(1L);
		return "Welcome to Public Dashboard " + name;
	}

	@PostMapping("/authenticate")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {

		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
		} catch (BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());

		final String jwt = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
}
