package inter.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;

import inter.Main;
import inter.model.dao.QuartoDAO;
import inter.model.dao.ReservaDAO;
import inter.model.domain.Hospede;
import inter.model.domain.Quarto;
import inter.model.domain.Reserva;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ReservarQuartoController {
	@FXML
	Label lblHospede;
	@FXML
	Label lblNmQuarto;
	@FXML
	Label lblTipo;
	@FXML
	Label lblDisponibilidade;
	@FXML
	Label lblValor;
	@FXML
	DatePicker dataIn;
	@FXML
	DatePicker dataOut;
	@FXML
	TextField txtQtde;

	QuartoDAO quartoDao;
	ReservaDAO dao;
	Hospede hospede;
	Quarto quarto;

	@FXML
	protected void buscarHospede(ActionEvent event) throws IOException {
		hospede = new Hospede();
		boolean selectedHospede = showBuscaHospede(hospede);
		if (selectedHospede) {
			lblHospede.setText(hospede.getNome());
		}
	}

	@FXML
	protected void buscarQuarto(ActionEvent event) throws IOException {
		quarto = new Quarto();
		boolean selectedQuarto = showBuscaQuarto(quarto);
		if (selectedQuarto) {
			lblDisponibilidade.setText(quarto.getDisponibilidade());
			lblNmQuarto.setText(String.valueOf(quarto.getNmQuarto()));
			lblTipo.setText(quarto.getTipo());
			lblValor.setText(String.valueOf(quarto.getValor()));
		}
	}

	@FXML
	protected void concluirReserva(ActionEvent event) throws SQLException {
		if (quarto != null && hospede != null) {
			if (quarto.getDisponibilidade().equals("Disponível")) {
				int qtde = 0;
				try {
					qtde = Integer.parseInt(txtQtde.getText());
				} catch (Exception e) {
				}
				Reserva reserva = new Reserva(Date.valueOf(dataIn.getValue()), Date.valueOf(dataOut.getValue()), qtde,
						quarto, hospede);
				dao = ReservaDAO.getDAOConnected();
				dao.create(reserva);
				quartoDao = QuartoDAO.getDAOConnected();
				quartoDao.updateDisponibilidade(quarto, "Reservado");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Reserva salva com sucesso!");
				alert.setContentText(null);
				alert.showAndWait();
				limparCampos();
			}else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("O quarto deve estar disponível para reservar.");
				alert.setContentText(null);
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("É preciso ter um hóspede e um quarto selecionados para confirmar.");
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

	private void limparCampos() {
		lblDisponibilidade.setText("");
		lblHospede.setText("");
		lblNmQuarto.setText("");
		lblTipo.setText("");
		lblValor.setText("");
		txtQtde.setText("");
		dataIn.setValue(null);
		dataOut.setValue(null);
		quarto = null;
		hospede = null;
	}
}
