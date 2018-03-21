package br.com.manzatech.tarefas.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.manzatech.tarefas.database.jdbc.dao.JdbcTarefaDao;
import br.com.manzatech.tarefas.models.Tarefa;

@Controller
public class TarefasController {

	@Autowired
	private JdbcTarefaDao dao;
	
	@RequestMapping("novaTarefa")
	public String novaTarefa() {
		return "tarefa/formulario";
	}

	@RequestMapping("adicionaTarefa")
	public String adicionaTarefa(@Valid Tarefa tarefa, BindingResult result) {
		if (result.hasFieldErrors("descricao")) {
			return "tarefa/formulario";
		}
		dao.adiciona(tarefa);
		return "tarefa/adicionada";
	}

	@RequestMapping("listaTarefas")
	public String lista(Model model) {
		model.addAttribute("tarefas", dao.getLista());
		return "tarefa/lista";
	}

	@RequestMapping("removeTarefa")
	public String remove(Tarefa tarefa) {
		dao.remove(tarefa);
		return "redirect:listaTarefas";

	}

	@RequestMapping("mostraTarefa")
	public String mostra(Long id, Model model) {
		model.addAttribute("tarefa", dao.busca(id));
		return "tarefa/mostra";
	}

	@RequestMapping("alteraTarefa")
	public String altera(Tarefa tarefa) {
		dao.altera(tarefa);
		return "redirect:listaTarefas";
	}

	@ResponseBody
	@RequestMapping("finalizaTarefa")
	public void finaliza(Long id) {		
		dao.finaliza(dao.busca(id));
	}

}
