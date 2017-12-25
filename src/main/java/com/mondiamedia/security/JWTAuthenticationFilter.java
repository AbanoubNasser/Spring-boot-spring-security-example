package com.mondiamedia.security;

import static com.mondiamedia.security.SecurityConstants.HEADER_STRING;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import io.jsonwebtoken.ExpiredJwtException;

public class JWTAuthenticationFilter extends BasicAuthenticationFilter {

	 private UserDetailsService userDetailsService;
	 
	 private JwtTokenUtil jwtTokenUtil;
	 
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager,UserDetailsService userDetailsService,JwtTokenUtil jwtTokenUtil) {
		super(authenticationManager);
		this.userDetailsService=userDetailsService;
		this.jwtTokenUtil=jwtTokenUtil;
	}
	
    @Override
    protected void doFilterInternal(HttpServletRequest req,
                                    HttpServletResponse res,
                                    FilterChain chain) throws IOException, ServletException {
    	String username = null;
        String authToken = null;
        String header = req.getHeader(HEADER_STRING);
        if (header != null && header.startsWith("Bearer ")) {
            authToken = header.substring(7);
            try {
                username = jwtTokenUtil.getUsernameFromToken(authToken);
            } catch (IllegalArgumentException e) {
               System.out.println("an error occured during getting username from token");
            } catch (ExpiredJwtException e) {
            	System.out.println("the token is expired and not valid anymore");
            }
        } 
        if (username != null &&SecurityContextHolder.getContext().getAuthentication() == null) {

        	UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            if (jwtTokenUtil.validateToken(authToken, userDetails)) {
            	UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        chain.doFilter(req, res);
    }
}
