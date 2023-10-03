package com.alura.foro.security;

import com.alura.foro.service.implement.UsuarioService;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

   private final JwtService jwtService;
   private final UsuarioService userService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {

        final String token = getTokenFromRequest(request);
        final String username;

        try {
            if (token==null) {
                filterChain.doFilter(request, response);
                throw new NullPointerException("Token vacio");
            }
            username = jwtService.getUsernameFromToken(token);
            if (username!=null && SecurityContextHolder.getContext().getAuthentication() ==null){
                final var userDetails = userService.loadUserByUsername(username);

                if (jwtService.validateToken(token,userDetails)){
                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }else {
                    SecurityContextHolder.clearContext();
                }
                filterChain.doFilter(request,response);
            }
        } catch (ExpiredJwtException e) {
            logger.warn(e.getMessage());
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token expirado");
        } catch (MalformedJwtException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token no valido");
            logger.warn(e.getMessage());
        }  catch (NullPointerException e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Error en la autenticación");
            logger.warn(e.getMessage());
        }catch (IllegalArgumentException e) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("Token vacio");
            logger.warn(e.getMessage());
        }
        catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write("Error en la autenticación");
            logger.warn(e.getMessage());
        }
    }

    private String getTokenFromRequest(HttpServletRequest request) throws NullPointerException{

            final String authHeader = request.getHeader("Authorization");

            if (authHeader==null){
                return null;
            }

            if(StringUtils.hasText(authHeader) && authHeader.startsWith("Bearer ")){

                return authHeader.substring(7);
            }else {
                return null;
            }
    }
}
