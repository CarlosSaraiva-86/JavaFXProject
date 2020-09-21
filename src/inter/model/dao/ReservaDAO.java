package inter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import inter.model.database.Database;
import inter.model.domain.*;

public class ReservaDAO {
	private Connection conn;
	private static ReservaDAO reservaDAOSingleton = null;
	HospedeDAO hospedeDao;
	QuartoDAO quartoDao;

	public ReservaDAO() {
		if (conn == null)
			conn = Database.getConnection();
		hospedeDao = HospedeDAO.getDAOConnected();
		quartoDao = QuartoDAO.getDAOConnected();
	}

	public static ReservaDAO getDAOConnected() {
		if (reservaDAOSingleton == null) {
			reservaDAOSingleton = new ReservaDAO();
		}

		return reservaDAOSingleton;
	}

	public void create(Reserva model) throws SQLException {
		if (model != null) {
			String sql = "INSERT INTO reserva (idQuarto, idHospede, dataIn, dataOut, nmPessoas) VALUES (?, ?, ?, ?, ?);";

			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, model.getQuarto().getId());
			stm.setInt(2, model.getHospede().getId());
			stm.setDate(3, model.getDataEntrada());
			stm.setDate(4, model.getDataSaida());
			stm.setInt(5, model.getNmPessoas());
			stm.execute();
		}
	}

	public ArrayList<Reserva> getAll() throws SQLException {
		String sql = "SELECT * FROM reserva;";
		ArrayList<Reserva> result = new ArrayList<Reserva>();

		PreparedStatement stm = conn.prepareStatement(sql);
		ResultSet set = stm.executeQuery();

		while (set.next()) {
			Reserva model = new Reserva();
			model.setId(set.getInt("idReserva"));
			model.setQuarto(quartoDao.get(set.getInt("idQuarto")));
			model.setHospede(hospedeDao.get(set.getInt("idHospede")));
			model.setDataEntrada(set.getDate("dataIn"));
			model.setDataSaida(set.getDate("dataOut"));
			model.setNmPessoas(set.getInt("nmPessoas"));
			result.add(model);
		}

		return result;
	}

	public void delete(int Id) throws SQLException {
		String sql = "DELETE FROM reserva WHERE idReserva = ?";
		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, Id);
		stm.execute();
	}
}
