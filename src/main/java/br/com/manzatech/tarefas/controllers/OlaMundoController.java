package br.com.manzatech.tarefas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OlaMundoController {

	@RequestMapping("/oi")
	public String ola() {
		System.out.println("Executando lógica com Spring MVC");
		return "oi";
	}
}
