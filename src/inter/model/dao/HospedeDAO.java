package inter.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import inter.model.database.Database;
import inter.model.domain.Hospede;

public class HospedeDAO {

	private Connection conn;
	private static HospedeDAO hospedeDAOSingleton = null;
	
	public HospedeDAO() {
		if(conn == null)
			conn = Database.getConnection();
	}
	
	public static HospedeDAO getDAOConnected(){
		if(hospedeDAOSingleton == null) {
			hospedeDAOSingleton = new HospedeDAO();
		}
		
		return hospedeDAOSingleton;
	}
	
	
	public void create(Hospede h) {
		if(h != null) {
			String sql = "INSERT INTO hospede (nome, endereco, rg, telefone, email) VALUES (?, ?, ?, ?, ?);";
			try {
				PreparedStatement stm = conn.prepareStatement(sql);
				stm.setString(1, h.getNome());
				stm.setString(2, h.getEndereco());
				stm.setString(3, h.getRg());
				stm.setString(4, h.getTelefone());
				stm.setString(5, h.getEmail());
				stm.execute();
			} catch (SQLException e) {
				System.out.println("Erro ao criar um novo Model!");
				e.printStackTrace();
			}	
		}
	}
	
	public ArrayList<Hospede> getAll(){
		String sql = "SELECT * FROM hospede;";
		ArrayList<Hospede> result = new ArrayList<Hospede>();
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			ResultSet set = stm.executeQuery();
			
			while(set.next()) {
				Hospede model = new Hospede();
				model.setId(set.getInt("idHospede"));
				model.setNome(set.getString("nome"));
				model.setEndereco(set.getString("endereco"));
				model.setEmail(set.getString("email"));
				model.setRg(set.getString("rg"));
				model.setTelefone(set.getString("telefone"));
				result.add(model);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao criar um novo Hospede!");
			e.printStackTrace();
		}		
		return result;
	}
	
	public Hospede get(int id) throws SQLException {
		String sql = "SELECT * FROM hospede WHERE idHospede = ?;";
		Hospede model = new Hospede();

		PreparedStatement stm = conn.prepareStatement(sql);
		stm.setInt(1, id);
		ResultSet set = stm.executeQuery();
		while (set.next()) {
			model.setId(set.getInt("idHospede"));
			model.setNome(set.getString("nome"));
			model.setEndereco(set.getString("endereco"));
			model.setEmail(set.getString("email"));
			model.setRg(set.getString("rg"));
			model.setTelefone(set.getString("telefone"));
		}

		return model;
	}
	
	public void update(Hospede h) {
		String sql = "UPDATE hospede SET nome = ?, endereco = ?, rg = ?, telefone = ?, email = ? WHERE idHospede = ?";
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setString(1, h.getNome());
			stm.setString(2, h.getEndereco());
			stm.setString(3, h.getRg());
			stm.setString(4, h.getTelefone());
			stm.setString(5, h.getEmail());
			stm.setInt(6, h.getId());
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Hospede!");
			e.printStackTrace();
		}	
	}
	
	public void delete(int Id) {
		String sql = "DELETE FROM hospede WHERE idHospede = ?";
		try {
			PreparedStatement stm = conn.prepareStatement(sql);
			stm.setInt(1, Id);
			stm.execute();
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar Hospede!");
			e.printStackTrace();
		}	
	}
	
}
