package com.example.bankingsystem.security;

import com.example.bankingsystem.service.impl.UserDetailsServiceImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Hasan DOĞAN
 * @Project IntelliJ IDEA
 * @Date 20.05.2022
 */


public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    private JsonWTokenProvider jsonWTokenProvider;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);
            if(StringUtils.hasText(jwtToken) && jsonWTokenProvider.validateToken(jwtToken) ){
            Long id = jsonWTokenProvider.getUserIdFromJWToken(jwtToken);
            UserDetails userDetails = userDetailsService.loadUserById(id);
            if(userDetails != null ){
               UsernamePasswordAuthenticationToken authanticationToken =
                       new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
               authanticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authanticationToken);
            }}
        }catch (Exception e){
            return ;
        }
        filterChain.doFilter(request,response);
    }

    private String extractJwtFromRequest(HttpServletRequest request) {

        String bearer = request.getHeader("Authorization");
        if (StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
            return bearer.substring("Bearer".length() + 1);

        return null;
    }
}
