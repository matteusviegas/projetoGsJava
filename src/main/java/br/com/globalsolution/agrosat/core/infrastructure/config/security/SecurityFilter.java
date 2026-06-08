package br.com.globalsolution.agrosat.core.infrastructure.config.security;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.util.Strings;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class SecurityFilter extends OncePerRequestFilter {

    private final TokenConfig tokenConfig;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain)
            throws ServletException, IOException {

        String authorizedHeader = request.getHeader("Authorization");

        if (Strings.isNotEmpty(authorizedHeader) && authorizedHeader.startsWith("Bearer ")) {
            String token = authorizedHeader.substring("Bearer ".length());

            Optional<JwtUserData> optUser = tokenConfig.validateToken(token);

            if (optUser.isPresent()) {
                JwtUserData userData = optUser.get();

                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userData, null, List.of(new SimpleGrantedAuthority("ROLE_USER")));

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        filterChain.doFilter(request, response);
    }

}
