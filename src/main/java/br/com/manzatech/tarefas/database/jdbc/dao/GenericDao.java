package br.com.manzatech.tarefas.database.jdbc.dao;

import java.sql.Connection;
import java.util.List;

import br.com.manzatech.tarefas.database.jdbc.ConnectionFactory;

public abstract class GenericDao<E> {


		protected Connection conn;
		
		public GenericDao() {
			this.conn = ConnectionFactory.getConnection();
		}
		
		public abstract Long adiciona(E entidade) ;
		public abstract List<E> getLista();
		public abstract void remove(E entidade) ;
		public abstract void altera(E entidade) ;
		public abstract E busca(Long id) ;

}
