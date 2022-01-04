package com.example.swing_app_for_test;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;


@SpringBootApplication
public class SwingAppForTestApplication {

	public static void main(String[] args) {
		System.setProperty("java.awt.headless", "false");
		var ctx = new SpringApplicationBuilder(SwingAppForTestApplication.class)
				.run(args);
	}
}
