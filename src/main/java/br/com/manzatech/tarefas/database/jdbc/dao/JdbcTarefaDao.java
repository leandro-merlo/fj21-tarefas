package br.com.manzatech.tarefas.database.jdbc.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.mysql.cj.api.jdbc.Statement;

import br.com.manzatech.tarefas.models.Tarefa;

public class JdbcTarefaDao extends GenericDao<Tarefa> {

	@Override
	public Long adiciona(Tarefa tarefa) {
		String sql = "insert into tarefa " + "(descricao)" + " values (?)";
		try {
			// prepared statement para inser��o
			PreparedStatement stmt = this.conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			// seta os valores
			stmt.setString(1, tarefa.getDescricao());

			// executa
			stmt.executeUpdate();
			ResultSet rs = stmt.getGeneratedKeys();
			Long id = null;
			if (rs.next()) {
				id = rs.getLong(1);
			}
			stmt.close();
			rs.close();
			return id;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public List<Tarefa> getLista() {
		try {
			List<Tarefa> tarefas = new ArrayList<>();
			PreparedStatement stmt = this.conn.prepareStatement("select * from tarefa");
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				// criando o objeto tarefa
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				Date date = rs.getDate("dataFinalizacao");
				if (null != date) {
					Calendar df = Calendar.getInstance();
					df.setTime(date);
					tarefa.setDataFinalizacao(df);
				}

				// montando a data atrav�s do Calendar
				tarefas.add(tarefa);
			}
			rs.close();
			stmt.close();
			return tarefas;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void remove(Tarefa tarefa) {
		try {
			PreparedStatement stmt = conn.prepareStatement("delete " + "from tarefa where id=?");
			stmt.setLong(1, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void altera(Tarefa tarefa) {
		String sql = "update tarefa set descricao=?, finalizado=?, dataFinalizacao=? where id=?";
		try {
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, tarefa.getDescricao());
			stmt.setBoolean(2, tarefa.isFinalizado());
			stmt.setDate(3, null != tarefa.getDataFinalizacao() ? new java.sql.Date(tarefa.getDataFinalizacao().getTimeInMillis()) : null);
			stmt.setLong(4, tarefa.getId());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void finaliza(Tarefa tarefa) {
		tarefa.setFinalizado(true);
		Calendar c = Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
		tarefa.setDataFinalizacao(c);
		altera(tarefa);
	}

	@Override
	public Tarefa busca(Long id) {
		String sql = "select * from tarefa where id = ?";
		try {
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				Tarefa tarefa = new Tarefa();
				tarefa.setId(rs.getLong("id"));
				;
				tarefa.setDescricao(rs.getString("descricao"));
				tarefa.setFinalizado(rs.getBoolean("finalizado"));

				Date date = rs.getDate("dataFinalizacao");
				if (null != date) {
					Calendar df = Calendar.getInstance();
					df.setTime(date);

					tarefa.setDataFinalizacao(df);
				}
				return tarefa;
			}
		} catch (SQLException ex) {
			throw new RuntimeException(ex);
		}
		return null;

	}

}
