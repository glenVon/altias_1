package com.altias.altias_1;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

@Import(TestcontainersConfiguration.class)
@SpringBootTest
class Altias1ApplicationTests {

	@Test
	void contextLoads() {
	}

}
