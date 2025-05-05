package com.example.backend_bd;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("POJO – User")
class UserPojoTest {

    @Test
    void gettersAndSetters_work() {
        User u = new User();
        u.setId(7);
        u.setUsername("tester");
        u.setPassword("pw123");

        assertAll(
            () -> assertEquals(7, u.getId()),
            () -> assertEquals("tester", u.getUsername()),
            () -> assertEquals("pw123", u.getPassword())
        );
    }
}
