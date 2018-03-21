package br.com.manzatech.tarefas.database.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import br.com.manzatech.tarefas.models.Usuario;

@Repository
public class JdbcUsuarioDao extends GenericDao<Usuario> {

	@Autowired
	public JdbcUsuarioDao(DataSource dataSource) {
		super(dataSource);
	}

	@Override
	public Long adiciona(Usuario entidade) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Usuario> getLista() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void remove(Usuario entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void altera(Usuario entidade) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Usuario busca(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean existeUsuario(Usuario usuario) {
		String select = "SELECT * from usuarios WHERE login = ? and senha = ?";
		try {
			PreparedStatement stmt = conn.prepareStatement(select);
			stmt.setString(1, usuario.getLogin());
			stmt.setString(2, usuario.getSenha());
			ResultSet rs = stmt.executeQuery();
			return rs.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
