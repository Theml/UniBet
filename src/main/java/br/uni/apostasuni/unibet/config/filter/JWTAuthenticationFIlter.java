package br.uni.apostasuni.unibet.config.filter;

import br.uni.apostasuni.unibet.config.model.UserLogged;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import com.auth0.jwt.JWT;

public class JWTAuthenticationFIlter extends UsernamePasswordAuthenticationFilter {

    public static final int TIME_EXPIRATION_MS = 360000;
    public static final String TOKEN_KEY = "cG3tBE3E3GXV9fwjqyeXAXhm9z5SO4JT4i";

    AuthenticationManager authenticationManager;

    public JWTAuthenticationFIlter (AuthenticationManager authenticationManager) {
        super(authenticationManager);
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {

        try {
            UserLogged uLogin = new ObjectMapper().readValue(
                    request.getInputStream(), UserLogged.class
            );
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    uLogin.getUsername(),
                    uLogin.getPassword(),
                    uLogin.getAuthorities()
            ));
        } catch (IOException e) {
            throw new RuntimeException("Erro de autenticação", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain chain, Authentication authResult)
            throws IOException, ServletException {

        UserLogged user = (UserLogged) authResult.getPrincipal();

        long dt = new Date().getTime() + TIME_EXPIRATION_MS;
        String token = JWT.create()
                .withExpiresAt(new Date(dt))
                .withSubject( user.getUser().getNome())
                .withClaim("email", user.getUser().getEmail())
                .withClaim("saldo", user.getUser().getSaldo())
                .withClaim("ROLES",
                        new ObjectMapper().writeValueAsString(user.getAuthorities()))
                .sign(Algorithm.HMAC512( TOKEN_KEY ));

        System.out.println(" TOKEN :: "+token);
        response.getWriter().print(token);
        response.getWriter().flush();
    }

}
