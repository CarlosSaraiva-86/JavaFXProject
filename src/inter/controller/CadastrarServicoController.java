package inter.controller;

import java.sql.SQLException;

import inter.model.dao.ServicoDAO;
import inter.model.domain.Servico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;

public class CadastrarServicoController {
	
	@FXML
	TextField txtTipoServico;
	@FXML
	TextField txtValor;
	
	ServicoDAO dao;

	@FXML
	protected void salvarServico(ActionEvent event) throws SQLException {
		float valor = 0;
		try {
			valor = Float.parseFloat(txtValor.getText());
		}catch (Exception e) {
			// TODO: handle exception
		}
		Servico servico = new Servico(txtTipoServico.getText(), valor);
		dao = ServicoDAO.getDAOConnected();
		dao.create(servico);
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Salvo com sucesso!");
		alert.setContentText(null);
		alert.showAndWait();
		txtTipoServico.setText(null);
		txtValor.setText(null);
	}
}
