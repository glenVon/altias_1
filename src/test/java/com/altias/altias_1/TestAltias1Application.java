package com.altias.altias_1;

import org.springframework.boot.SpringApplication;

public class TestAltias1Application {

	public static void main(String[] args) {
		SpringApplication.from(Altias1Application::main).with(TestcontainersConfiguration.class).run(args);
	}

}
