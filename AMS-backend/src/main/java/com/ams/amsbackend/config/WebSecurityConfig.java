package com.ams.amsbackend.config;

import com.ams.amsbackend.security.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.filter.CorsFilter;

@Slf4j
@RequiredArgsConstructor
@Configuration
public class WebSecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.cors()
                .and()
                .csrf()
                    .disable()
                .httpBasic()
                    .disable()
                .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                /**
                 스프링부트 2.6버전부터 responsebody를 비우고 status만 반환함
                 responseBody에 error 메시지를 포함하려면 "/error" 추가
                 https://github.com/spring-projects/spring-boot/issues/28953
                 */
                .antMatchers("oath2/**","/users/sign-up","/users/log-in","/error").permitAll()
                .anyRequest()
                .authenticated();
//                .and()
//                .oauth2Login()
//                    .redirectionEndpoint()
//                .baseUri("/oauth2/callback/*")
//                .and()
//                    .userInfoEndpoint()
//                        .userService(oAuthUserService)
//                .and()
//                    .successHandler(oAuthSuccessHandler);

        //cors필터 이후 실행 설정
        http.addFilterAfter(jwtAuthenticationFilter, CorsFilter.class);
        return http.build();
    }
}
