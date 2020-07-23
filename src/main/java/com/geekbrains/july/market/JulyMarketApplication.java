package com.geekbrains.july.market;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class JulyMarketApplication {
	// Домашнее задание:
	// 1. Добавить кнопку сброса фильтра
	// 2. Реализовать поиск товара по части названия (может пригодиться оператор LIKE)
	// 3. Деактивируйте кнопки назад и вперед если находитесь на первой или последней странице соответственно

	public static void main(String[] args) {
		SpringApplication.run(JulyMarketApplication.class, args);

	}
}
