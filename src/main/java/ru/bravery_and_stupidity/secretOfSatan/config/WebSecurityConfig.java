package ru.bravery_and_stupidity.secretOfSatan.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import com.allanditzel.springframework.security.web.csrf.CsrfTokenResponseHeaderBindingFilter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.csrf.CsrfFilter;
import ru.bravery_and_stupidity.secretOfSatan.security.SecurityUserDetailsService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private SecurityUserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        CsrfTokenResponseHeaderBindingFilter csrfTokenFilter = new CsrfTokenResponseHeaderBindingFilter();
        http.addFilterAfter(csrfTokenFilter, CsrfFilter.class);
        http
            .authorizeRequests()
            .antMatchers("/resources/public/**").permitAll()
            .antMatchers(HttpMethod.POST, "/users/addUser").permitAll()
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .defaultSuccessUrl("/resources/account.html")
            .loginProcessingUrl("/authenticate")
            .usernameParameter("username")
            .passwordParameter("password")
            //.loginPage("/resources/public/index.html")
            .and()
            .httpBasic()
            .and()
            .logout()
            .logoutUrl("/logout")
            .logoutSuccessUrl("/resources/public/login.html")
            .permitAll();
    }
}
