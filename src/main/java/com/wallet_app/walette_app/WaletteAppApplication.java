package com.wallet_app.walette_app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
//@EnableSwagger2
public class WaletteAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WaletteAppApplication.class, args);
	}

}
