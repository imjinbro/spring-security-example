package jinbro.security.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import jinbro.security.security.filters.FormLoginFilter;
import jinbro.security.security.filters.JwtAuthenticationFilter;
import jinbro.security.security.handlers.FormLoginAuthenticationFailureHandler;
import jinbro.security.security.handlers.FormLoginAuthenticationSuccessHandler;
import jinbro.security.security.handlers.JwtAuthenticationFailureHandler;
import jinbro.security.security.provider.FormLoginAuthenticationProvider;
import jinbro.security.security.provider.JwtAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.Collections;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // 메소드에 씨큐리티 설정가능하다함
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /*** form authentication ***/
    @Autowired
    private FormLoginAuthenticationSuccessHandler formLoginAuthenticationSuccessHandler;

    @Autowired
    private FormLoginAuthenticationFailureHandler formLoginAuthenticationFailureHandler;

    @Autowired
    private FormLoginAuthenticationProvider formLoginAuthenticationProvider;


    /*** jwt authentication ***/
    @Autowired
    private HeaderTokenParser headerTokenParser;

    @Autowired
    private JwtAuthenticationFailureHandler jwtAuthenticationFailureHandler;

    @Autowired
    private JwtAuthenticationProvider jwtAuthenticationProvider;

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ObjectMapper getObjectMapper() {
        return new ObjectMapper();
    }

    @Bean
    public AuthenticationManager getAuthenticationManager() throws Exception {
        return super.authenticationManagerBean();
    }

    private FormLoginFilter formLoginFilter() throws Exception {
        FormLoginFilter formLoginFilter = new FormLoginFilter("/login", formLoginAuthenticationSuccessHandler, formLoginAuthenticationFailureHandler);
        formLoginFilter.setAuthenticationManager(super.authenticationManagerBean());
        return formLoginFilter;
    }

    private JwtAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        FilterMatcher filterMatcher = new FilterMatcher("/api/**", Collections.singletonList("/login"));
        JwtAuthenticationFilter jwtAuthenticationFilter = new JwtAuthenticationFilter(filterMatcher, headerTokenParser, jwtAuthenticationFailureHandler);
        jwtAuthenticationFilter.setAuthenticationManager(super.authenticationManagerBean());
        return jwtAuthenticationFilter;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(formLoginAuthenticationProvider);
        auth.authenticationProvider(jwtAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable();
        http.headers().frameOptions().disable();
        http.addFilterBefore(formLoginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}
