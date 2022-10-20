package com.example.backend.cart;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@WebMvcTest
public class CartControllerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void contextLoads() {

    }
}