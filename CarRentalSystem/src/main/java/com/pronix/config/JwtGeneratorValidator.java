package com.pronix.config;

import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtGeneratorValidator {
	
	private String SECRET = "";
	public JwtGeneratorValidator() {

		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			SECRET = Base64.getEncoder().encodeToString(sk.getEncoded());
			System.out.println(SECRET);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}

	public Claims extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
	}

	private boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	
	public String generateToken(Authentication authentication) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, authentication);
    }
	
	
	@SuppressWarnings("deprecation")
	private String createToken(Map<String, Object> claims, Authentication authentication) {
    	String role =authentication.getAuthorities().stream()
  	     .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
        return Jwts
        		.builder()
        		.claim("role",role)
        		.subject(authentication.getName())
        		.issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15)))
                .signWith(SignatureAlgorithm.HS256, getKey())
                .compact();
    }

//	@SuppressWarnings("deprecation")
//	public String generateToken(Map<String, Object> claims, Authentication authentication ) {
//		String role =authentication.getAuthorities().stream()
//		  	     .map(r -> r.getAuthority()).collect(Collectors.toSet()).iterator().next();
////		return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis()))
////				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 30)).and().signWith(getKey())
////				.compact();
//		return Jwts.builder()
//	               .claim("role", role)
//	               .subject(authentication.getName())
//	               .issuedAt(new Date(System.currentTimeMillis()))
//	               .expiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(5)))
//	               .signWith(SignatureAlgorithm.HS256, SECRET.getBytes()) // Ensure SECRET is a byte array
//	               .compact();
//	}
	
	private SecretKey getKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET);
		return Keys.hmacShaKeyFor(keyBytes);
	}
	
	public boolean validateToken(String token, UserDetails userDetails) {
		final String userName = extractUsername(token);
		return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
