package com.example.ToDoList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.ToDoList.web.UserDetailServiceImpl;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled= true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserDetailServiceImpl userDetailsService; // valinnainen
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        .authorizeRequests().antMatchers("/css/**","/h2-console/**").permitAll()
        .and()
        .authorizeRequests().anyRequest().authenticated()
        .and()
      .formLogin()
          .loginPage("/login")
          .defaultSuccessUrl("/ToDoList")
          .permitAll()
          .and()
      .logout()
          .permitAll();
    }
    /*
// only for testing purposes
    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        List<UserDetails> users = new ArrayList();
    	UserDetails user = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();

    	users.add(user);
    	
    	user = User.withDefaultPasswordEncoder()
                   .username("admin")
                   .password("admin")
                   .roles("USER", "ADMIN")
                   .build();
    	
    	users.add(user);
    	
        return new InMemoryUserDetailsManager(users);
    }
    */
    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }
    
}
