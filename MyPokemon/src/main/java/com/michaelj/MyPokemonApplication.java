package com.michaelj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.boot.web.servlet.ServletComponentScan;

//@ServletComponentScan // 开启了对Servlet组件的支持
@SpringBootApplication
@EnableTransactionManagement
public class MyPokemonApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyPokemonApplication.class, args);
	}

}
