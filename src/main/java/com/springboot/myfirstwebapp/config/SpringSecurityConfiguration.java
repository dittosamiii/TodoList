package com.springboot.myfirstwebapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.springboot.myfirstwebapp.entity.User;
import com.springboot.myfirstwebapp.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

	private final UserRepository userRepository;

	public SpringSecurityConfiguration(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Bean
	UserDetailsService userDetailsService() {
		return username -> {
			User user = userRepository.findByUsername(username);
			if (user != null) {
				return org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
						.password(user.getPassword()).roles("USER").build();
			} else {
				throw new UsernameNotFoundException("User not found");
			}
		};
	}

	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.headers(headers -> headers.frameOptions(frameOptions -> frameOptions.sameOrigin()))
				.authorizeHttpRequests(auth -> auth.requestMatchers("/login", "/register", "/h2-console/**").permitAll()
						.requestMatchers("/**").permitAll().anyRequest().authenticated())
				.formLogin(form -> form.loginPage("/login").loginProcessingUrl("/perform_login")
						.defaultSuccessUrl("/welcome").permitAll())
				.logout(logout -> logout.permitAll());

		return http.build();
	}
}
