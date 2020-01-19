package com.dd.kanban.security;

import java.util.Date;

import io.jsonwebtoken.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class JwtTokenProvider {
	@Value("${app.jwtSecret}")
	private String jwtSecret;

	@Value("${app.jwtExpirationInMs}")
	private int jwtExpirationInMs;

	public String generateToken(Authentication auth) {
		UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();

		Date now = new Date();
		Date expireDate = new Date(now.getTime() + jwtExpirationInMs);

		return Jwts.builder()
				.setSubject(Long.toString(userPrincipal.getId()))
				.setIssuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS512, jwtSecret)
				.compact();
	}

	public Long getUserIdFromJWT(String token) {
		Claims claims = Jwts.parser()
				.setSigningKey(jwtSecret)
				.parseClaimsJws(token)
				.getBody();
		return Long.parseLong(claims.getSubject());
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			log.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			log.error("Invalid JWT token");	
		} catch (UnsupportedJwtException ex) {
			log.error("Unsuported JWT token");
		} catch (IllegalArgumentException ex) {
			log.error("JWT claims is empty");
		} catch (JwtException ex) {
			log.error("********* Unknown error ");
		}

		return false;
	}
}
