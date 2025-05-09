package br.com.fiap.money_flow_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    @Autowired
    private AuthFilter authFilter;

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(auth -> auth
                    .requestMatchers(HttpMethod.DELETE, "users/**").hasRole("ADMIN") //para apagar outro user precisa ter papel de admin
                    .requestMatchers("transactions/**").hasRole("ADMIN")
                    .requestMatchers(HttpMethod.POST, "users/**").permitAll()
                    .requestMatchers(HttpMethod.POST, "login/**").permitAll()
                    .anyRequest().authenticated() //autenticado para realizar qualquer request
                )
                .httpBasic(Customizer.withDefaults())
                .addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //api rest stateless (usuario não fica logado)
                .build();
    }

//    @Bean
//    UserDetailsService userDetailsService() {
//        return new InMemoryUserDetailsManager(List.of(
//                User.withUsername("user").password("$2a$12$am.sOEnsyz86P6YAJ3vJzuO2864MGlVi.6TXM8PAWZ1aH2SC6Irky").roles("ADMIN").build(),
//                User.withUsername("user").password("$2a$12$am.sOEnsyz86P6YAJ3vJzuO2864MGlVi.6TXM8PAWZ1aH2SC6Irky").roles("USER").build()
//        ));
//    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
