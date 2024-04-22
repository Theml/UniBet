package br.uni.apostasuni.unibet.config;

import org.apache.tomcat.util.net.openssl.ciphers.Authentication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception{
        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder().encode("123")).roles("APOSTADOR")
                .and()
                .withUser("admin").password(passwordEncoder().encode("234")).roles("ADMIN");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        //time jogo user dev aposta
        http.sessionManagement( sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf().disable()
                .authorizeHttpRequests()
                .antMatchers("/dev/**").permitAll()
                .antMatchers("/aposta/**").hasRole("APOSTADOR")
                .antMatchers("/aposta/user/**").hasAnyRole("ADMIN", "APOSTADOR")
                .anyRequest().authenticated()
                .and()
                .httpBasic();
    }
}
