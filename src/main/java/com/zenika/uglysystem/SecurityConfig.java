package com.zenika.uglysystem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@EnableWebSecurity
public class SecurityConfig {

    @Value("${admin.user}")
    private String adminUser;

    @Value("${admin.pwd}")
    private String adminPwd;

    @Bean
    public UserDetailsService userDetailsService() {
        // ensure the passwords are encoded properly
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        manager.createUser(User.withUsername(adminUser).password(adminPwd).roles("USER", "ADMIN").build());
        //manager.createUser(User.withUsername("admin").password("M33KTczYfsmu9CWk").roles("USER", "ADMIN").build());
        return manager;
    }

    @Configuration
    @Order(1)
    public static class AdminSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    .mvcMatchers("/admin").authenticated()
                    .and()
                    .httpBasic();
        }

        @Autowired
        public void configureGlobal(AuthenticationManagerBuilder auth) {
            auth.eraseCredentials(false);
        }
    }

    @Configuration
    @Order(2)
    public static class OtherSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http
                    .authorizeRequests()
                    //.antMatchers("/**")
                    //.hasIpAddress("172.31.21.82")
                    .anyRequest().permitAll();
        }
    }
}
