package com.orionsolution.auth.utility;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class DecodeUtility {


    private static String processBas64Twice(String value) {
        final String decoded = getDecoded(value);
        if (!decoded.contains("=")) {
            return decoded;
        }
        return processBas64Twice(decoded);
    }

    public static String getDecoded(String value) {
        return new String(Base64.getDecoder().decode(value.getBytes(StandardCharsets.UTF_8)));
    }

    public static Claims getClaims(SecretKey secretKey, String authorizationHeader) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getEncoded()))
                .build()
                .parseClaimsJws(authorizationHeader)
                .getBody();
    }
}
