package com.boostmytool.beststore;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors().and()
//            .csrf((csrf) -> csrf.disable())
            .authorizeRequests()
            .requestMatchers(HttpMethod.POST, "/products/create").hasRole("ADMIN") // Yêu cầu vai trò 'admin' để truy cập /products/create
            .requestMatchers("/products/delete").hasRole("ADMIN")
            .anyRequest().authenticated() // Tất cả các yêu cầu khác đều cần xác thực
            .and()
            .formLogin()
            .and()
            .csrf((csrf) -> csrf
    				.csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
    		)
            ;	
        return http.build();
    }
    
    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        auth.inMemoryAuthentication()
            .withUser("user").password(encoder.encode("thinh123")).roles("user")
            .and()
            .withUser("admin").password(encoder.encode("admin123")).roles("admin").authorities(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
