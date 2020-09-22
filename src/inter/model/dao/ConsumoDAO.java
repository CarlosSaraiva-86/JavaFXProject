package inter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import inter.model.database.Database;
import inter.model.domain.Consumo;

public class ConsumoDAO {
	private Connection conn;
	AluguelDAO aluguelDAO;
	ServicoDAO servicoDAO;
	private static ConsumoDAO consumoDAOSingleton = null;

	public ConsumoDAO() {
		if (conn == null)
			conn = Database.getConnection();
		aluguelDAO = AluguelDAO.getDAOConnected();
		servicoDAO = ServicoDAO.getDAOConnected();
	}

	public static ConsumoDAO getDAOConnected() {
		if (consumoDAOSingleton == null) {
			consumoDAOSingleton = new ConsumoDAO();
		}

		return consumoDAOSingleton;
	}
	
	public void create(int idAluguel, int idServico) throws SQLException {
		String sql = "INSERT INTO consumo (idAluguel, idServico) VALUES (?, ?)";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, idAluguel);
		stm.setInt(2, idServico);
		stm.execute();
	}
	
	public Consumo get(int idAluguel) throws SQLException {
		Consumo model = new Consumo();
		String sql = "SELECT * FROM consumo where idAluguel = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, idAluguel);
		ResultSet set = stm.executeQuery();
		while (set.next()) {
			model.setId(set.getInt("idConsumo"));
			model.setAluguel(aluguelDAO.get(set.getInt("idAluguel")));
			model.setServicos(servicoDAO.get(set.getInt("idServico")));
		}		
		return model;
	}
}
