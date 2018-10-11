package com.security.browser;

import com.security.core.properities.SecurityProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;


@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Autowired
    private SecurityProperties securityProperties;

    @Autowired
    private AuthenticationSuccessHandler demoAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler demoAuthenticationFailedHandler;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
            .loginPage("/authentication/require")
            .loginProcessingUrl("/authentication/form") // when accessing that url, spring knows to use usernameAndPassword filter
                                                         // to handle the request
            .successHandler(demoAuthenticationSuccessHandler)
            .failureHandler(demoAuthenticationFailedHandler)
//      http.httpBasic()
            .and()
            .authorizeRequests()
            .antMatchers("/authentication/require",
                                       securityProperties.getBrowser().getLoginPage()).permitAll()
            .anyRequest()
            .authenticated()
            .and()
            .csrf().disable();
    }

//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return new MyUserDetailServices();
//    }
//
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(userDetailsServiceBean());
//    }

}
