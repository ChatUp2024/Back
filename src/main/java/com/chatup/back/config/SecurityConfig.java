package com.chatup.back.config;

import com.chatup.back.oauth.Oauth2UserService;
import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.servlet.util.matcher.MvcRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.handler.HandlerMappingIntrospector;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final ConfigEnv env;
    private final Oauth2UserService oauth2UserService;

    @Bean
    public SecurityFilterChain securityFilterChain(
            final HttpSecurity httpSecurity,
            final HandlerMappingIntrospector introspector) throws Exception {

        final MvcRequestMatcher.Builder requestMatcher = new MvcRequestMatcher.Builder(introspector);

        httpSecurity
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
//                .formLogin(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers(requestMatcher.pattern(env.getTestApi()))
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .oauth2Login(oauth -> oauth
                        .userInfoEndpoint(user -> user.userService(oauth2UserService))
                        .defaultSuccessUrl(env.getFrontUrlDomain())
                )
        ;
        return httpSecurity.build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(env.getFrontUrlLocal(), env.getFrontUrlDomain()));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST"));
        configuration.setAllowedHeaders(Arrays.asList("*"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}
