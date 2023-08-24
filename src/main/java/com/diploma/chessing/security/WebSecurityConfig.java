package com.diploma.chessing.security;

import com.diploma.chessing.enumeration.ApplicationUserPermission;
import com.diploma.chessing.enumeration.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final ApplicationUserService applicationUserService;

    @Autowired
    public WebSecurityConfig(PasswordEncoder passwordEncoder, ApplicationUserService applicationUserService) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and().csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/", "index", "/css/*", "/js/*")
                .permitAll()
                //.antMatchers("/owners/**").hasRole(ApplicationUserRole.OWNER.name())
                //.antMatchers("/customers/**").hasRole(ApplicationUserRole.CUSTOMER.name())
                .antMatchers(HttpMethod.DELETE, "/owners/**").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/owners/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.OWNER.name())
                .antMatchers(HttpMethod.PUT, "/owners/**").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/owners/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.OWNER.name(), ApplicationUserRole.CUSTOMER.name())
                .antMatchers(HttpMethod.DELETE, "/customers/**").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.POST, "/customers/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.OWNER.name())
                .antMatchers(HttpMethod.PUT, "/customers/**").hasAnyRole(ApplicationUserRole.ADMIN.name())
                .antMatchers(HttpMethod.GET, "/customers/**").hasAnyRole(ApplicationUserRole.ADMIN.name(), ApplicationUserRole.OWNER.name(), ApplicationUserRole.CUSTOMER.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }

        //return super.userDetailsService();
    /*private final JwtAuthFilter jwtAuthFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**auth/**")
                .permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authenticationProvider(authenticationProvider())
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    private AuthenticationProvider authenticationProvider() {

    }*/

    //@Autowired
    //private PlayerService playerService;

   /* @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        //provider.setUserDetailsService(playerService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }*/

    /*@Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/owner/*")
                .permitAll()
                .antMatchers("/customer/*")
                .permitAll();
                //.anyRequest();
                *//*.authenticated().and()
                .formLogin()*//*;

    }*/
}
