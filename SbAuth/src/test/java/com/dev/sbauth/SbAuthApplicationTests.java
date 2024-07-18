package com.dev.sbauth;

import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class SbAuthApplicationTests {


    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() {
    }

    @Test
    void encodeAndVerifySecret() {
        String rawSecret = "mySecretCode";
        String encodedSecret = passwordEncoder.encode(rawSecret);

        // Output the encoded secret (for verification purposes)
        System.out.println("Encoded Secret: " + encodedSecret);


    }
}
