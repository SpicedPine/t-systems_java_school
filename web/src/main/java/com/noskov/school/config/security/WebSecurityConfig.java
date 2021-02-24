package com.noskov.school.config.security;

import com.noskov.school.security.AuthProvider;
import com.noskov.school.service.api.MedicalStaffService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@ComponentScan("com.noskov.school")
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOGGER = LoggerFactory.getLogger(WebSecurityConfig.class);

    @Autowired
    MedicalStaffService medicalStaffService;

    @Autowired
    AuthProvider authProvider;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        auth.authenticationProvider(authProvider);
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        LOGGER.info("Security config are applying...");

        httpSecurity
                .authorizeRequests()
                .antMatchers("/schedule/**").permitAll()
                .antMatchers("/exceptions/**").permitAll()
                .antMatchers("/physician/registration").not().fullyAuthenticated()
                .antMatchers("/nurse/registration").not().fullyAuthenticated()
                .antMatchers("/patient/**").hasRole("PHYSICIAN")
                .antMatchers("/event/**").hasRole("NURSE")
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/authenticating")
                .failureUrl("/login?fail=true")
                .usernameParameter("email")
                .permitAll()
                .and()
                .logout()
                .permitAll()
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling().accessDeniedPage("/exceptions/forbidden_page");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(medicalStaffService)
                .passwordEncoder(bCryptPasswordEncoder());
        LOGGER.info("bCryptEncoder was added");
    }
}
