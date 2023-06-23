package com.user.service.app.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig {

	 @Bean
	    public SecurityFilterChain filterChain(HttpSecurity security) throws Exception {

	        security.authorizeHttpRequests(authz -> authz
	        		.anyRequest().authenticated())
//	               	.and()
	        		.oauth2ResourceServer((oauth2) -> oauth2
	        				.jwt(Customizer.withDefaults())
	        		);
//	                .oauth2ResourceServer()
//	                .jwt();

	        return security.build();
	 }
}
