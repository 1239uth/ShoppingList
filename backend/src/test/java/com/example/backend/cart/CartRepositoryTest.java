package com.example.backend.cart;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@ActiveProfiles(value = "test")
@DataJpaTest
public class CartRepositoryTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void contextLoads() {

    }
}