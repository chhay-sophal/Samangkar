package com.samangkar.Samangkar.security;

import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Base64;

public class SecureKeyGenerator {

    public static void main(String[] args) {
        // Generate a secure key for HMAC-SHA512 algorithm
        Key secureKey = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS512);

        // Convert the key to a base64-encoded string
        String secureKeyString = Base64.getEncoder().encodeToString(secureKey.getEncoded());

        // Print or store the secure key string
        System.out.println("Secure Key: " + secureKeyString);
    }
}
