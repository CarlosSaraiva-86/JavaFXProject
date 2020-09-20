package inter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import inter.model.database.Database;
import inter.model.domain.Quarto;

public class QuartoDAO {
	private Connection conn;
	private static QuartoDAO quartoDAOSingleton = null;

	public QuartoDAO() {
		if (conn == null)
			conn = Database.getConnection();
	}

	public static QuartoDAO getDAOConnected() {
		if (quartoDAOSingleton == null) {
			quartoDAOSingleton = new QuartoDAO();
		}

		return quartoDAOSingleton;
	}

	public void create(Quarto model) throws SQLException {
		if (model != null) {
			String sql = "INSERT INTO quarto (nmQuarto, tipo, disponibilidade, valor) VALUES (?, ?, ?, ?);";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, model.getNmQuarto());
			stm.setString(2, model.getTipo());
			stm.setString(3, model.getDisponibilidade());
			stm.setFloat(4, model.getValor());
			stm.execute();
		}
	}

	public ArrayList<Quarto> getAll() throws SQLException {
		String sql = "SELECT * FROM quarto;";
		ArrayList<Quarto> result = new ArrayList<Quarto>();

		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet set = stm.executeQuery();

		while (set.next()) {
			Quarto model = new Quarto();
			model.setId(set.getInt("idQuarto"));
			model.setNmQuarto(set.getInt("nmQuarto"));
			model.setTipo(set.getString("tipo"));
			model.setDisponibilidade(set.getString("disponibilidade"));
			model.setValor(set.getFloat("valor"));
			result.add(model);
		}

		return result;
	}

	public void update(Quarto model) throws SQLException {
		String sql = "UPDATE quarto SET nmQuarto = ?, tipo = ?, disponibilidade = ?, valor = ? WHERE idQuarto = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, model.getNmQuarto());
		stm.setString(2, model.getTipo());
		stm.setString(3, model.getDisponibilidade());
		stm.setFloat(4, model.getValor());
		stm.setInt(5, model.getId());
		stm.execute();
	}

	public void delete(int Id) throws SQLException {
		String sql = "DELETE FROM hospede WHERE idQuarto = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, Id);
		stm.execute();
	}
}
