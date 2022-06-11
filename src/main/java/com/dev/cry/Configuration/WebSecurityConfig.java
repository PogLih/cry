package com.dev.cry.Configuration;

import com.dev.cry.Filter.JwtAuthenticationFilter;
import com.dev.cry.Service.UserService;
import com.dev.cry.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean

    public UserService userService() {

        return new UserServiceImpl();

    }



    @Autowired

    private UserService userService;



    @Bean

    public JwtAuthenticationFilter jwtAuthenticationFilter() {

        return new JwtAuthenticationFilter();

    }



    @Bean(BeanIds.AUTHENTICATION_MANAGER)

    @Override

    public AuthenticationManager authenticationManager() throws Exception {

        return super.authenticationManager();

    }



    @Bean

    public PasswordEncoder passwordEncoder() {

        return new BCryptPasswordEncoder(10);

    }






    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {

        auth.userDetailsService(userService).passwordEncoder(passwordEncoder());

    }



    @Override

    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().ignoringAntMatchers("/**");

        http.authorizeRequests()

                .antMatchers("/",

                        "/login").permitAll()

                .anyRequest().authenticated()

                .and().csrf().disable()

                .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"));

        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        http.sessionManagement()

                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        http.cors();

    }

}