package ru.netology.hibernate.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true, jsr250Enabled = true)
public class SecurityConfiguration {

    @Bean
    public PasswordEncoder encoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public UserDetailsService users() {
        UserDetails admin = User.builder()
                .username("Natalia")
                .password(encoder().encode("pass"))
                .roles("READ","WRITE", "DELETE")
                .build();
        UserDetails user1 = User.builder()
                .username("Ivan")
                .password(encoder().encode("1234"))
                .roles("WRITE")
                .build();
        UserDetails user2 = User.builder()
                .username("Vasilii")
                .password(encoder().encode("5678"))
                .roles("READ")
                .build();
        return new InMemoryUserDetailsManager(admin, user1, user2);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.formLogin()
                .and()
                .csrf().disable();
        return http.build();
    }

}
