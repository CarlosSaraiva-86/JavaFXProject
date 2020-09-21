package inter.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import inter.Main;
import inter.model.dao.QuartoDAO;
import inter.model.domain.Quarto;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManterQuartosController{

	@FXML
	TextField txtId;
	@FXML
	TextField txtNmQuarto;
	@FXML
	TextField txtTipo;
	@FXML
	TextField txtValor;
	
	QuartoDAO dao;
	Quarto quarto;
	
	@FXML
	protected void salvarQuartos(ActionEvent event) throws SQLException {
		int nmQuarto = 0;
		float valor = 0;
		try {
			nmQuarto = Integer.parseInt(txtNmQuarto.getText());
			valor = Float.parseFloat(txtValor.getText());
		}
		catch (Exception e) {
		}
		Quarto quarto = new Quarto(nmQuarto, txtTipo.getText(), valor);
		dao = QuartoDAO.getDAOConnected();
		if (txtId.getText().isEmpty()) {
			quarto.setDisponibilidade("Disponível");
			dao.create(quarto);
		} else {
			quarto.setId(Integer.parseInt(txtId.getText()));
			dao.update(quarto);
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Salvo com sucesso!");
		alert.setContentText(null);
		alert.showAndWait();
		limparCampos();
	}

	@FXML
	protected void buscarQuarto(ActionEvent event) throws IOException {
		quarto = new Quarto();
		boolean selectedQuarto = showBuscaQuarto(quarto);
		if (selectedQuarto) {
			txtId.setText(String.valueOf(quarto.getId()));
			txtNmQuarto.setText(String.valueOf(quarto.getNmQuarto()));
			txtTipo.setText(quarto.getTipo());
			txtValor.setText(String.valueOf(quarto.getValor()));
		}
	}

	@FXML
	protected void excluirQuarto(ActionEvent event) throws NumberFormatException, SQLException {
		if (!txtId.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Excluir");
			alert.setHeaderText("Deseja mesmo excluir o cadastro?");
			alert.setContentText(null);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				dao = QuartoDAO.getDAOConnected();
				dao.delete(Integer.parseInt(txtId.getText()));
				alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Excluido com sucesso!");
				alert.setContentText(null);
				alert.showAndWait();
				limparCampos();
			}
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Selecione um cliente para excluir!");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}

	private boolean showBuscaQuarto(Quarto model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("views/BuscarQuartoView.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Busca Hospede");
		Scene scene = new Scene(root);
		dialogStage.setScene(scene);

		BuscarQuartoController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setQuarto(model);

		dialogStage.showAndWait();
		this.quarto = controller.getQuarto();
		return controller.isSelected();
	}

	protected void limparCampos() {
		txtNmQuarto.setText("");
		txtTipo.setText("");
		txtValor.setText("");
		txtId.setText("");
	}
	
}
