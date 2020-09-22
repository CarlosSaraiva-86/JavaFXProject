package inter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import inter.model.database.Database;
import inter.model.domain.Servico;

public class ServicoDAO {
	private Connection conn;
	private static ServicoDAO servicoDAOSingleton = null;

	public ServicoDAO() {
		if (conn == null)
			conn = Database.getConnection();
	}

	public static ServicoDAO getDAOConnected() {
		if (servicoDAOSingleton == null) {
			servicoDAOSingleton = new ServicoDAO();
		}

		return servicoDAOSingleton;
	}

	public void create(Servico model) throws SQLException {
		if (model != null) {
			String sql = "INSERT INTO servico (tipoServico, valor) VALUES (?, ?);";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, model.getTipoServico());
			stm.setFloat(2, model.getValor());
			stm.execute();
		}
	}

	public ArrayList<Servico> getAll() throws SQLException {
		String sql = "SELECT * FROM servico;";
		ArrayList<Servico> result = new ArrayList<Servico>();

		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet set = stm.executeQuery();

		while (set.next()) {
			Servico model = new Servico();
			model.setId(set.getInt("idServico"));
			model.setTipoServico(set.getString("tipoServico"));
			model.setValor(set.getFloat("valor"));
			result.add(model);
		}

		return result;
	}	
	
	public Servico get (int id) throws SQLException {
		String sql = "SELECT * FROM servico WHERE idServico = ?";
		Servico model = new Servico();
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet set = stm.executeQuery();
		while (set.next()) {
			model.setId(set.getInt("idServico"));
			model.setTipoServico(set.getString("tipoServico"));
			model.setValor(set.getFloat("valor"));
		}
		return model;
	}

	public void update(Servico model) throws SQLException {
		String sql = "UPDATE quarto SET tipoServico = ?, valor = ? WHERE idServico = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setString(1, model.getTipoServico());
		stm.setFloat(2, model.getValor());
		stm.setInt(3, model.getId());
		stm.execute();
	}
}
