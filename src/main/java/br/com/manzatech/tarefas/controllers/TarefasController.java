package br.com.manzatech.tarefas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.manzatech.tarefas.database.jdbc.dao.JdbcTarefaDao;
import br.com.manzatech.tarefas.models.Tarefa;

@Controller
public class TarefasController {

	@RequestMapping("novaTarefa")
	public String novaTarefa() {
		return "tarefa/formulario";
	}
	
	@RequestMapping("adicionaTarefa")
	public String adicionaTarefa(Tarefa tarefa) {
		JdbcTarefaDao dao = new JdbcTarefaDao();
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}
}
