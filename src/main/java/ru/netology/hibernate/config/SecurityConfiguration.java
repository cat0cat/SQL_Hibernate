package ru.netology.hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfiguration {

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("Natalia")
                .password("{noop}pass")
                .authorities("read","write")
                .build();
        UserDetails user = User.builder()
                .username("Ivan")
                .password("{noop}1234")
                .authorities("write")
                .build();
        return new InMemoryUserDetailsManager(admin, user);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .authorizeRequests().antMatchers("/persons/welcome").permitAll()
                .and()
                .authorizeRequests().antMatchers("/persons/by-city").hasAuthority("read")
                .and()
                .authorizeRequests().antMatchers("/persons/by-age").hasAuthority("write")
                .and()
                .authorizeRequests().antMatchers("/persons/name-and-surname").hasAuthority("write")
                .and()
                .authorizeRequests().anyRequest().authenticated();
        return http.build();
    }

}
