package br.com.manzatech.tarefas.database.jdbc.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

public abstract class GenericDao<E> {


		protected Connection conn;
		
		public GenericDao(DataSource dataSource) {
			try {
				this.conn = dataSource.getConnection();
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
		
		public abstract Long adiciona(E entidade) ;
		public abstract List<E> getLista();
		public abstract void remove(E entidade) ;
		public abstract void altera(E entidade) ;
		public abstract E busca(Long id) ;

}
