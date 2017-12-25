package com.mondiamedia.security;

import static com.mondiamedia.security.SecurityConstants.LOGIN_URL;
import static com.mondiamedia.security.SecurityConstants.SIGN_UP_URL;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final JwtTokenUtil jwtTokenUtil;
    public WebSecurity(UserDetailsService userDetailsService, BCryptPasswordEncoder bCryptPasswordEncoder,JwtTokenUtil jwtTokenUtil) {
        this.userDetailsService = userDetailsService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.jwtTokenUtil = jwtTokenUtil;
    }
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuthenticationFilter(authenticationManager(),userDetailsService(),jwtTokenUtil));
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }
    
    @Override
    public void configure(
    		org.springframework.security.config.annotation.web.builders.WebSecurity web)
    		throws Exception {
    	web.ignoring().antMatchers("/v2/api-docs", 
    			"/configuration/ui", "/swagger-resources", "/configuration/security",
    			"/swagger-ui.html", "/webjars/**", "/swagger-resources/configuration/ui",
    			"/swagger-resources/configuration/security",LOGIN_URL,SIGN_UP_URL);
    }
}
