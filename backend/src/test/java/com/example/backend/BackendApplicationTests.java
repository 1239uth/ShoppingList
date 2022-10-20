package com.example.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles(value = "test")
@SpringBootTest(properties = {"job.autorun.enabled=false"})
class BackendApplicationTests {

    @Test
    void contextLoads() {
    }

}
