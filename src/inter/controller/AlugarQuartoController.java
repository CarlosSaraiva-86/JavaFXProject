package inter.controller;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

import inter.Main;
import inter.model.dao.AluguelDAO;
import inter.model.dao.QuartoDAO;
import inter.model.domain.*;
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

public class AlugarQuartoController {
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
	AluguelDAO dao;
	Hospede hospede;
	Quarto quarto;
	Reserva reserva;

	@FXML
	protected void buscarHospede(ActionEvent event) throws IOException {
		hospede = new Hospede();
		boolean selectedHospede = showBuscaHospede(hospede);
		if (selectedHospede) {
			lblHospede.setText(hospede.getNome());
		}
	}

	@FXML
	protected void concluirAluguel(ActionEvent event) throws SQLException {
		if (quarto != null && hospede != null) {
			if (quarto.getDisponibilidade().equals("Disponível") || quarto.getDisponibilidade().equals("Reservado")) {
				int qtde = 0;
				try {
					qtde = Integer.parseInt(txtQtde.getText());
				} catch (Exception e) {
				}
				Aluguel aluguel = new Aluguel(Date.valueOf(dataIn.getValue()), Date.valueOf(dataOut.getValue()), qtde,
						quarto, hospede);
				dao = AluguelDAO.getDAOConnected();
				dao.create(aluguel);
				quartoDao = QuartoDAO.getDAOConnected();
				quartoDao.updateDisponibilidade(quarto, "Alugado");
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setHeaderText("Aluguel do quarto salvo com sucesso!");
				alert.setContentText(null);
				alert.showAndWait();
				limparCampos();
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("O quarto deve estar disponível para alugar.");
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
	protected void buscarReserva(ActionEvent event) throws IOException {
		reserva = new Reserva();
		boolean selectedReserva = showBuscaReserva(reserva);
		if (selectedReserva) {
			lblDisponibilidade.setText(reserva.getQuarto().getDisponibilidade());
			lblNmQuarto.setText(String.valueOf(reserva.getQuarto().getNmQuarto()));
			lblTipo.setText(reserva.getQuarto().getTipo());
			lblValor.setText(String.valueOf(reserva.getQuarto().getValor()));
			lblHospede.setText(reserva.getHospede().getNome());
			dataIn.setValue(LocalDate.parse(String.valueOf(reserva.getDataEntrada())));
			dataOut.setValue(LocalDate.parse(String.valueOf(reserva.getDataSaida())));
			txtQtde.setText(String.valueOf(reserva.getNmPessoas()));
			quarto = reserva.getQuarto();
			hospede = reserva.getHospede();
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

	private boolean showBuscaReserva(Reserva model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("views/BuscarReservaView.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Busca Reserva");
		Scene scene = new Scene(root);
		dialogStage.setScene(scene);

		BuscarReservaController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setReserva(model);

		dialogStage.showAndWait();
		this.reserva = controller.getReserva();
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
		reserva = null;
	}
}
