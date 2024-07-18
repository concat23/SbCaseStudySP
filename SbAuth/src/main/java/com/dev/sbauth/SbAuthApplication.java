package com.dev.sbauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import java.security.SecureRandom;
import java.util.Base64;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class, SecurityAutoConfiguration.class})
public class SbAuthApplication {
    private static final SecureRandom secureRandom = new SecureRandom();
    private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();


    public static void main(String[] args) {
        String secretCode = generateSecretCode(16);
        System.out.println("Generated Secret Code: " + secretCode);

        SpringApplication.run(SbAuthApplication.class, args);

    }



    public static String generateSecretCode(int byteSize) {
        byte[] randomBytes = new byte[byteSize];
        secureRandom.nextBytes(randomBytes);
        return base64Encoder.encodeToString(randomBytes);
    }

}
