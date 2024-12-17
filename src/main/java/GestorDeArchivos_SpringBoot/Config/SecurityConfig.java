/*package GestorDeArchivos_SpringBoot.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .authorizeHttpRequests(auth -> {
                        auth.requestMatchers("/Pagina_Principal", "/Modulo_InicioDeSesion", "/Modulo_Registro").permitAll();
                        auth.anyRequest().authenticated();
                })
                .formLogin(Customizer.withDefaults())
                .build();
    }
}*/
