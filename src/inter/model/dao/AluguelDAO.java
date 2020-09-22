package inter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import inter.model.database.Database;
import inter.model.domain.Aluguel;

public class AluguelDAO {
	private Connection conn;
	private static AluguelDAO aluguelDAOSingleton = null;
	HospedeDAO hospedeDao;
	QuartoDAO quartoDao;

	public AluguelDAO() {
		if (conn == null)
			conn = Database.getConnection();
		hospedeDao = HospedeDAO.getDAOConnected();
		quartoDao = QuartoDAO.getDAOConnected();
	}

	public static AluguelDAO getDAOConnected() {
		if (aluguelDAOSingleton == null) {
			aluguelDAOSingleton = new AluguelDAO();
		}

		return aluguelDAOSingleton;
	}

	public void create(Aluguel model) throws SQLException {
		if (model != null) {
			String sql = "INSERT INTO aluguel (idQuarto, idHospede, dataIn, dataOut, nmPessoas) VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, model.getQuarto().getId());
			stm.setInt(2, model.getHospede().getId());
			stm.setDate(3, model.getDataEntrada());
			stm.setDate(4, model.getDataSaida());
			stm.setInt(5, model.getNmPessoas());
			stm.execute();
		}
	}

	public ArrayList<Aluguel> getAll() throws SQLException {
		String sql = "SELECT * FROM aluguel;";
		ArrayList<Aluguel> result = new ArrayList<Aluguel>();

		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet set = stm.executeQuery();

		while (set.next()) {
			Aluguel model = new Aluguel();
			model.setId(set.getInt("idAluguel"));
			model.setQuarto(quartoDao.get(set.getInt("idQuarto")));
			model.setHospede(hospedeDao.get(set.getInt("idHospede")));
			model.setDataEntrada(set.getDate("dataIn"));
			model.setDataSaida(set.getDate("dataOut"));
			model.setNmPessoas(set.getInt("nmPessoas"));
			result.add(model);
		}

		return result;
	}
	
	public Aluguel get(int id) throws SQLException {
		String sql = "SELECT * FROM aluguel WHERE idAluguel = ?;";
		Aluguel model = new Aluguel();

		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet set = stm.executeQuery();
		while (set.next()) {
			model.setId(set.getInt("idAluguel"));
			model.setQuarto(quartoDao.get(set.getInt("idQuarto")));
			model.setHospede(hospedeDao.get(set.getInt("idHospede")));
			model.setDataEntrada(set.getDate("dataIn"));
			model.setDataSaida(set.getDate("dataOut"));
			model.setNmPessoas(set.getInt("nmPessoas"));
		}

		return model;
	}

	public void delete(int Id) throws SQLException {		
		String sql = "DELETE FROM aluguel WHERE idAluguel = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, Id);
		stm.execute();
	}
}
