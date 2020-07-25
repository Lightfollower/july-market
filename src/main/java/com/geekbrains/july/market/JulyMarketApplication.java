package com.geekbrains.july.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JulyMarketApplication {
	// Домашнее задание:
	// 1. Добавьте страницу профиля пользователя с отображением информации о нем (ФИО, email, телефон)
	// 2. * Доделайте корзины на основе сессионных бинов
	// 3. * Дать админам возможность блокировать пользователей

	public static void main(String[] args) {
		SpringApplication.run(JulyMarketApplication.class, args);

	}
}
