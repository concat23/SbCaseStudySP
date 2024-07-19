package com.dev.sbauth.generate;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

public class Test {

    // Encode a byte array to a base64url encoded string
    public static String encode(byte[] input) {
        return Base64.getUrlEncoder().withoutPadding().encodeToString(input);
    }

    // Decode a base64url encoded string to a byte array
    public static byte[] decode(String input) {
        return Base64.getUrlDecoder().decode(input);
    }

    public static void main(String[] args) {
        // Generate a secure key for HS256
        SecretKey key = Keys.secretKeyFor(SignatureAlgorithm.HS256);

        // Encode the key to base64url
        String encodedKey = Base64.getUrlEncoder().withoutPadding().encodeToString(key.getEncoded());
        System.out.println("Generated Key (Base64url Encoded): " + encodedKey);

        // Decode the key from base64url
        byte[] decodedKey = Base64.getUrlDecoder().decode(encodedKey);
        SecretKey originalKey = Keys.hmacShaKeyFor(decodedKey);

        // Example usage: create a JWT token
        String token = Jwts.builder()
                .setSubject("user123")
                .signWith(originalKey)
                .compact();

        System.out.println("Generated JWT Token: " + token);

        // Decode and verify the JWT token
        String subject = Jwts.parser()
                .setSigningKey(originalKey)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();

        System.out.println("Decoded JWT Subject: " + subject);
    }
}
