package com.zzpj.security;

import com.zzpj.model.entities.AccessLevel;
import com.zzpj.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AccountService accountService;

    private final JWTRequestFilter jwtRequestFilter;

    @Autowired
    public SecurityConfig(AccountService accountService, JWTRequestFilter jwtRequestFilter) {
        this.accountService = accountService;
        this.jwtRequestFilter = jwtRequestFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/auth", "/register")
                    .permitAll()
                .antMatchers(HttpMethod.POST, "/accounts/**")
                    .hasAuthority(AccessLevel.ADMIN)
                .antMatchers(HttpMethod.GET, "/accounts/**")
                    .authenticated()
                .antMatchers(HttpMethod.PUT, "/accounts/activate/*", "/accounts/deactivate/*")
                    .hasAuthority(AccessLevel.ADMIN)
                .antMatchers(HttpMethod.PUT, "/accounts/**")
                    .authenticated()
                .antMatchers(HttpMethod.GET, "/ingredients/**")
                    .hasAnyAuthority(AccessLevel.USER, AccessLevel.MODERATOR)
                .antMatchers(HttpMethod.POST, "/recipes")
                    .hasAuthority(AccessLevel.USER)
                .antMatchers(HttpMethod.POST, "/recipes/recommendation")
                    .hasAuthority(AccessLevel.USER)
                .antMatchers(HttpMethod.GET, "/recipes/favourite")
                    .hasAuthority(AccessLevel.USER)
                .antMatchers(HttpMethod.GET, "/recipes/**")
                    .hasAnyAuthority(AccessLevel.MODERATOR, AccessLevel.USER)
                .antMatchers(HttpMethod.DELETE, "/recipes/**")
                    .hasAnyAuthority(AccessLevel.USER, AccessLevel.MODERATOR)
                .antMatchers(HttpMethod.PUT, "/recipes/ratings/**")
                    .hasAuthority(AccessLevel.USER)
                .antMatchers(HttpMethod.PUT, "/recipes/favourite/add/**")
                    .hasAuthority(AccessLevel.USER)
                .antMatchers(HttpMethod.PUT, "/recipes/**")
                    .hasAnyAuthority(AccessLevel.USER, AccessLevel.MODERATOR)
                .antMatchers(HttpMethod.POST, "/recipes/ingredients/**")
                    .hasAnyAuthority(AccessLevel.USER, AccessLevel.MODERATOR)
                .antMatchers(HttpMethod.DELETE, "/recipes/ingredients/**")
                    .hasAnyAuthority(AccessLevel.USER, AccessLevel.MODERATOR)
                .antMatchers(HttpMethod.GET, "/recipes/save/**")
                    .hasAuthority(AccessLevel.USER)
                .antMatchers(HttpMethod.GET, "/recipes/shopping_list")
                    .hasAuthority(AccessLevel.USER)
                .anyRequest().authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(accountService);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }
}
