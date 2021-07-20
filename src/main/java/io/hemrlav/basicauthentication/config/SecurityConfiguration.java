package io.hemrlav.basicauthentication.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        // does nothing to the password
        return NoOpPasswordEncoder.getInstance();
    }

    /**
     * Configures in-memory users with roles for authentication.
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // set your configuration on the auth object
        auth.inMemoryAuthentication()
                .withUser("tonystark") // user 1
                .password("has_a_heart") // user 1's password
                .roles("Avenger") // user 1's roles
                .and()
                .withUser("nickfury") // user 2
                .password("theboss") // user 2's password
                .roles("Director"); // user 2's roles
    }

    /**
     * Configures Authorization in terms of Roles required to access an API.
     *
     * always authorize requests from most restrictive to least restrictive
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/greet/director").hasRole("Director")
                .and()
                .authorizeRequests()
                .antMatchers("/greet/avenger").hasAnyRole("Director", "Avenger")
                .and()
                .authorizeRequests()
                .antMatchers("/greet").permitAll()
                .and().httpBasic(); // to allow api calls from post mand with basic auth

        // so that it also allow login / logout via browser
        http.formLogin()
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll();
    }
}
