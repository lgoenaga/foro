package com.alura.foro.security;

import com.alura.foro.model.Usuario;
import com.alura.foro.util.ConstantService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.Logger;
@Service
public class JwtService{

    Logger logger  = Logger.getLogger(JwtService.class.getName());

    @Value("${jwt.expiration}")
    private Long expirationTime;

    @Value("${jwt.secret}")
    private String secret;

    public String generateToken(Usuario user) {
        return getToken(new HashMap<>(), user);
    }

    private String getToken(Map<String, Object> extraClaims, Usuario user) {

        extraClaims.put("Roles", user.getRoles());

        return Jwts.builder()
                .setClaims(extraClaims)
                .setIssuer(user.getNombre())
                .setSubject(user.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
                .signWith(getKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getKey() {

        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
    public String getUsernameFromToken(String token)  {

            String getToken = getClaimFromToken(token, Claims::getSubject);
            return getToken;

    }

    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) throws IOException {

        final String username = getUsernameFromToken(token);

        boolean tokenValido = (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
        if (tokenValido) {
            logger.info(ConstantService.TOKEN_VALIDO);
        } else {
            logger.info(ConstantService.TOKEN_INVALIDO);

        }

        return tokenValido;
    }

    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }

    private Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public boolean isTokenExpired(String token) {
        return getExpirationDateFromToken(token).before(new Date());
    }
}
