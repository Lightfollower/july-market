package com.geekbrains.july.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JulyMarketApplication {
	// Домашнее задание:
	// 1. Реализуйте ввод логина/пароля через форму AngularJS и
	// запоминание этой пары, чтобы AngularJS с каждый запросом их отправлял
	// для прохождения basic authentication. Реализовать log out.
	// 2. * Попробуйте перенести фильтры на AngularJS

	public static void main(String[] args) {
		SpringApplication.run(JulyMarketApplication.class, args);
	}
}