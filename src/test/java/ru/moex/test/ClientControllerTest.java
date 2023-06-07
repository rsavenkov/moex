package ru.moex.test;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class ClientControllerTest {

	@Autowired
	private ClientController controller;

	@Test
	void contextLoads() {
		// успешное создание для всех x-Source
		// не успешное создание для всех x-Source не прошел валидацию
		// не передали header x-Source
		// передали не тот
		// поиск по id успешно
		// поиск по id не успешно
		// поиск по полям успешно
		// поиск по полям не успешно
	}

}
