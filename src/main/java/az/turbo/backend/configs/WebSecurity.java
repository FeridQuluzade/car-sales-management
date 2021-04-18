package az.turbo.backend.configs;

import az.turbo.backend.users.domain.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf()
                .disable()
                .authorizeRequests()

                //make swagger endpoints public
                .antMatchers("/v2/api-docs",
                        "/configuration/ui",
                        "/swagger-resources",
                        "/configuration/security",
                        "/swagger-ui.html",
                        "/webjars/**",
                        "/swagger-resources/configuration/ui",
                        "/swagger-ui.html",
                        "/swagger-resources/configuration/security").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/actuator/**").permitAll()

                //PUBLIC ENDPOINTS
                .antMatchers("/favicon.ico").permitAll()
                .antMatchers("/images/**").permitAll()

                //bodyTypes
                .antMatchers(HttpMethod.GET, "/body-types/retrieve-all").permitAll()
                .antMatchers(HttpMethod.GET, "/body-types/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.POST, "/body-types/create").permitAll()
                .antMatchers(HttpMethod.PUT, "/body-types/update").permitAll()
                .antMatchers(HttpMethod.DELETE, "/body-types/delete/**").permitAll()

                //colors
                .antMatchers(HttpMethod.GET, "/colors/retrieve-all").permitAll()
                .antMatchers(HttpMethod.GET, "/colors/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.GET, "/colors/create").permitAll()
                .antMatchers(HttpMethod.GET, "/colors/update").permitAll()
                .antMatchers(HttpMethod.GET, "/colors/delete/**").permitAll()
                //PRIVATE ENDPOINTS
                .anyRequest()
                .authenticated()
                .and()
                //.addFilter(jwtAuthenticationFilter())
                //.addFilter(new JWTAuthorizationFilter(authenticationManager()))
                //.addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

//    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
//        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager(), userService);
//        jwtAuthenticationFilter.setFilterProcessesUrl("/users/sign-in");
//        return jwtAuthenticationFilter;
//    }


    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

