package com.desafio2.literarura;

import com.desafio2.literarura.controller.MenuController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class LiteraruraApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(LiteraruraApplication.class, args);
		MenuController menuController = context.getBean(MenuController.class);
		menuController.mostrarMenu();
	}

}
