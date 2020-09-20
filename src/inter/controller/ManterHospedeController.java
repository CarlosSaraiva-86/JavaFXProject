package inter.controller;

import java.io.IOException;
import java.util.Optional;

import inter.Main;
import inter.model.dao.HospedeDAO;
import inter.model.domain.Hospede;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.scene.control.TextField;

public class ManterHospedeController {
	@FXML
	TextField txtNome;
	@FXML
	TextField txtEndereco;
	@FXML
	TextField txtRG;
	@FXML
	TextField txtTelefone;
	@FXML
	TextField txtEmail;
	@FXML
	TextField txtId;

	HospedeDAO dao;
	Hospede hospede;

	@FXML
	protected void salvarHospede(ActionEvent event) {
		Hospede hospede = new Hospede(txtNome.getText(), txtEndereco.getText(), txtRG.getText(), txtTelefone.getText(),
				txtEmail.getText());
		dao = HospedeDAO.getDAOConnected();
		if (txtId.getText() == "") {
			dao.create(hospede);
		} else {
			hospede.setId(Integer.parseInt(txtId.getText()));
			dao.update(hospede);
		}
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText("Salvo com sucesso!");
		alert.setContentText(null);
		alert.showAndWait();
		limparCampos();
	}

	@FXML
	protected void buscarHospede(ActionEvent event) throws IOException {
		hospede = new Hospede();
		boolean selectedHospede = showBuscaHospede(hospede);
		if (selectedHospede) {
			txtId.setText(String.valueOf(hospede.getId()));
			txtNome.setText(hospede.getNome());
			txtEmail.setText(hospede.getEmail());
			txtEndereco.setText(hospede.getEndereco());
			txtRG.setText(hospede.getRg());
			txtTelefone.setText(hospede.getTelefone());
		}
	}

	@FXML
	protected void excluirHospede(ActionEvent event) {
		if (!txtId.getText().isEmpty()) {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("Excluir");
			alert.setHeaderText("Deseja mesmo excluir o cadastro?");
			alert.setContentText(null);

			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ButtonType.OK) {
				dao = HospedeDAO.getDAOConnected();
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

	private boolean showBuscaHospede(Hospede hospede) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("views/BuscarHospedeView.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Busca Hospede");
		Scene scene = new Scene(root);
		dialogStage.setScene(scene);

		BuscarHospedeController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setHospede(hospede);

		dialogStage.showAndWait();
		this.hospede = controller.getHospede();
		return controller.isSelected();
	}

	protected void limparCampos() {
		txtNome.setText("");
		txtEndereco.setText("");
		txtRG.setText("");
		txtTelefone.setText("");
		txtEmail.setText("");
		txtId.setText("");
	}
}
