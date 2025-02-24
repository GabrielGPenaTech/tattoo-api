package com.tattoo.api_tattoo.secuirty;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class PasswordEncryptionTest {

    @Test
    public void testPasswordEncryption() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "mypassword";
        String encodedPassword = encoder.encode(rawPassword);

        // Verificar se a senha criptografada corresponde à senha original
        assertTrue(encoder.matches(rawPassword, encodedPassword));
    }
}
