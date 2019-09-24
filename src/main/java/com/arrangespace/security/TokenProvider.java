package com.arrangespace.security;

import io.jsonwebtoken.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

	private static final Logger logger = LoggerFactory.getLogger(TokenProvider.class);

	@Autowired
	private Environment env;

	@Value("${security.jwt.token.expire-length:2592000000}")
	private long validityInMilliseconds = 2592000000L; // 30days

	public String createToken(String userId) {

		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + validityInMilliseconds);

		return Jwts.builder().setSubject(userId).setIssuedAt(new Date()).setExpiration(expiryDate)
				.signWith(SignatureAlgorithm.HS512, getSecretKey()).compact();
	}

	public String getUserIdFromToken(String token) {
		Claims claims = Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(token).getBody();

		return claims.getSubject();
	}

	public boolean validateToken(String authToken) {
		try {
			Jwts.parser().setSigningKey(getSecretKey()).parseClaimsJws(authToken);
			return true;
		} catch (SignatureException ex) {
			logger.error("Invalid JWT signature");
		} catch (MalformedJwtException ex) {
			logger.error("Invalid JWT token");
		} catch (ExpiredJwtException ex) {
			logger.error("Expired JWT token");
		} catch (UnsupportedJwtException ex) {
			logger.error("Unsupported JWT token");
		} catch (IllegalArgumentException ex) {
			logger.error("JWT claims string is empty.");
		}
		return false;
	}

	public String getSecretKey() {
		return env.getProperty("secret-key") != null ? env.getProperty("secret-key") : "G5hfAVnOAs";
	}

}
