package az.turbo.backend.configs;

import az.turbo.backend.users.application.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
class WebSecurity extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

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
                //.antMatchers(HttpMethod.GET, "/users/password").permitAll()
                .antMatchers(HttpMethod.GET, "/body-types/retrieve-all").permitAll()
                .antMatchers(HttpMethod.GET, "/body-types/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.POST, "/body-types/create").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT, "/body-types/update").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.DELETE, "/body-types/delete/**").hasAuthority("ADMINISTRATOR")

                //colors
                .antMatchers(HttpMethod.GET, "/colors/retrieve-all").permitAll()
                .antMatchers(HttpMethod.GET, "/colors/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.GET, "/colors/create").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/colors/update").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.GET, "/colors/delete/**").hasAuthority("ADMINISTRATOR")
                //cities
                .antMatchers(HttpMethod.GET, "/cities/retrieve-all").permitAll()
                .antMatchers(HttpMethod.GET, "/cities/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.POST, "/cities/create").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT, "/cities/update").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.DELETE, "/cities/delete/**").hasAuthority("ADMINISTRATOR")

                //customers
                .antMatchers(HttpMethod.GET, "/customers/retrieve-all").permitAll()
                .antMatchers(HttpMethod.GET, "/customers/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.POST, "/customers/create").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.PUT, "/customers/update").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.DELETE, "/customers/delete/**").hasAuthority("ADMINISTRATOR")

                //engineVolumes
                .antMatchers(HttpMethod.POST, "/engine-volumes/retrieve-all").permitAll()
                .antMatchers(HttpMethod.POST, "/engine-volumes/retrieve-by-id/**").permitAll()
                .antMatchers(HttpMethod.POST, "/engine-volumes/create").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/engine-volumes/update").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/engine-volumes/delete/**").hasAuthority("ADMINISTRATOR")

                //users
                .antMatchers(HttpMethod.POST, "/users/retrieve-all").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/users/retrieve-by-id/**").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/users/create").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/users/update").hasAuthority("ADMINISTRATOR")
                .antMatchers(HttpMethod.POST, "/users/delete/**").hasAuthority("ADMINISTRATOR")

                //PRIVATE ENDPOINTS
                .anyRequest()
                .authenticated()
                .and()
                .addFilter(jwtAuthenticationFilter())
                .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                .addFilterBefore(new CorsFilter(), ChannelProcessingFilter.class)
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    private JWTAuthenticationFilter jwtAuthenticationFilter() throws Exception {
        JWTAuthenticationFilter jwtAuthenticationFilter = new JWTAuthenticationFilter(authenticationManager(), userService);
        jwtAuthenticationFilter.setFilterProcessesUrl("/users/sign-in");
        return jwtAuthenticationFilter;
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}