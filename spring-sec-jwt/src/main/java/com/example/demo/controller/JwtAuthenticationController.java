package com.example.demo.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.JwtRequest;
import com.example.demo.dto.JwtResponse;
import com.example.demo.service.JwtUserDetailsService;
import com.example.demo.util.JwtTokenUtil;

import io.jsonwebtoken.ExpiredJwtException;

@RestController
@CrossOrigin
@RequestMapping(value="/auth")
public class JwtAuthenticationController {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	Log logger = LogFactory.getLog(JwtAuthenticationController.class);

	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) throws Exception {
	//	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		final String token = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/renew", method = RequestMethod.GET)
	public ResponseEntity<?> renew(@RequestHeader("Authorization") String auth) throws Exception {
		String username = "";
		try {
			username= jwtTokenUtil.getUsernameFromToken(auth.substring(7)) ;
		} catch (ExpiredJwtException e) {
			logger.warn("Token is expired");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		final String token = jwtTokenUtil.doGenerateToken(username);
		return ResponseEntity.ok(new JwtResponse(token));
	}
	
	@RequestMapping(value = "/isValidToken", method = RequestMethod.GET)
	public ResponseEntity<Boolean> validateToken(@RequestHeader("Authorization") String auth) throws Exception {
	//	authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
//		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());
		String jwtToken = "";
		String username= "";
		if (auth != null && auth.startsWith("Bearer ")) {
			jwtToken = auth.substring(7);
			try {
				username = jwtTokenUtil.getUsernameFromToken(jwtToken);
			} catch (IllegalArgumentException e) {
				System.out.println("Unable to get JWT Token");
			} 
		} else {
			logger.warn("JWT Token does not begin with Bearer String");
		}
		// Once we get the token validate it.
		if (username != null) {
			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
			// if token is valid configure Spring Security to manually set
			// authentication
			if (jwtTokenUtil.validateToken(jwtToken, userDetails)) {
				return ResponseEntity.ok().body(true);
			}else {
				logger.warn("Token Expired");
			}
		}
		return ResponseEntity.badRequest().body(false);
	}
	
	

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
}
