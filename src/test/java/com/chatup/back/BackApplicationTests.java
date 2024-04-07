package com.chatup.back;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(properties = {
        "FRONT_URL_DOMAIN=/"
})
class BackApplicationTests {

    @Test
    void contextLoads() {
    }

}
