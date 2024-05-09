package br.uni.apostasuni.unibet.config.security.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

public class JWTValidationFilter extends BasicAuthenticationFilter {

    public static final String HEADER_ATRIBUTE = "Authorization";

    public static final String ATRIBUTE_PREFIX = "Bearer";

    public JWTValidationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain)
            throws IOException, ServletException {
        String atributo = request.getHeader(HEADER_ATRIBUTE);
        if (atributo == null){
            chain.doFilter(request,response);
            return;
        }
        if (!atributo.startsWith(ATRIBUTE_PREFIX) ){
            chain.doFilter(request,response);
            return;
        }
        String token = atributo.replace(ATRIBUTE_PREFIX,"");
        //System.out.println(token);
        UsernamePasswordAuthenticationToken user = getAutentication(token);

        SecurityContextHolder.getContext().setAuthentication(user);
        chain.doFilter(request,response);
    }

    private UsernamePasswordAuthenticationToken getAutentication(String token) {
        Map<String, Claim> mapa =  JWT.require(Algorithm.HMAC512( JWTAuthenticationFIlter.TOKEN_KEY ))
                .build().verify(token).getClaims();
        String login = JWT.require(Algorithm.HMAC512( JWTAuthenticationFIlter.TOKEN_KEY ))
                .build().verify(token).getClaims().get("nome").asString();
        //.getSubject();

        String[] roles1 = (String[] ) JWT.require(Algorithm.HMAC512( JWTAuthenticationFIlter.TOKEN_KEY )).build().verify(token).getClaims().get("ROLES").toString().replace("[","").replace("]","").split(",");
        ArrayList<String> roles = new ArrayList<>();
        for (String r: roles1){
            roles.add(r);
        }

        return new UsernamePasswordAuthenticationToken(login, "", getRoles(roles) );
    }

    private Collection<? extends GrantedAuthority> getRoles(ArrayList<String> roles) {
        ArrayList<SimpleGrantedAuthority> rRoles = new ArrayList<>();
        for (String r: roles) {
            rRoles.add( new SimpleGrantedAuthority(r));
        }
        return rRoles;
    }
}
