package com.elhilali.authentification.config;


import com.elhilali.authentification.filter.JwtFilter;
import com.elhilali.authentification.service.MyUserDetailsService;
import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class config {


    @Autowired
    UserDetailsService myUserDetailsService;

    @Autowired
    JwtFilter JwtFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{

        httpSecurity
                .csrf(off -> off.disable())
                .authorizeHttpRequests(req -> req
                        .requestMatchers("/signup","/landingPage","/login").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(JwtFilter, UsernamePasswordAuthenticationFilter.class);

        return httpSecurity.build();


    }



    @Bean
    public AuthenticationProvider authenticationProvider(){

            DaoAuthenticationProvider daoAuthenticationProviderprovider = new DaoAuthenticationProvider();
            daoAuthenticationProviderprovider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
            daoAuthenticationProviderprovider.setUserDetailsService(myUserDetailsService);
            return daoAuthenticationProviderprovider;

    }


}
