package com.stadium.app.security;

import com.stadium.app.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@SuppressWarnings("deprecation")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private MyUserDetailsService myUserDetailsService;

	@Bean
	PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}


	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
                auth
                    .userDetailsService(myUserDetailsService)
                    .passwordEncoder(passwordEncoder());
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/static/**", "/static/css/**", "/templates/**", "/h2-console/**", "/auth/**",
						"/swagger-ui.html/**", "/api-docs/**").permitAll()
				.antMatchers("/user/**").hasAuthority("ROLE_USER")
				.antMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
				.anyRequest().authenticated()
				.and().formLogin()
				.loginPage("/auth/login").failureUrl("/auth/login?error=true")
				.defaultSuccessUrl("/auth/dashboard")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

}
