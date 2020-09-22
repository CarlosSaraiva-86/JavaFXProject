package inter.controller;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import inter.model.dao.AluguelDAO;
import inter.model.dao.ConsumoDAO;
import inter.model.dao.QuartoDAO;
import inter.model.domain.Aluguel;
import inter.model.domain.Consumo;
import inter.model.domain.Diaria;
import inter.model.domain.Pagamento;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class PagamentoEstadiaController implements Initializable {
	@FXML
	Label lblHospede;
	@FXML
	Label lblQuarto;
	@FXML
	Label lblTotal;
	@FXML
	Label lblTotalConsumo;
	@FXML
	Label lblTotalDiaria;
	@FXML
	DatePicker dataIn;
	@FXML
	DatePicker dataOut;
	@FXML
	ComboBox<Pagamento> cbPagamento;

	ConsumoDAO consumoDAO;
	Consumo consumo;
	AluguelDAO dao;
	QuartoDAO quartoDAO;
	float totalConsumo;
	private Aluguel aluguel;
	private Stage dialogStage;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		carregarCombo();
	}

	private void carregarCombo() {
		Pagamento pgto1 = new Pagamento("Cartão Débito");
		Pagamento pgto2 = new Pagamento("Cartão Crédito");
		Pagamento pgto3 = new Pagamento("Dinheiro");
		ArrayList<Pagamento> lista = new ArrayList<Pagamento>();
		lista.add(pgto1);
		lista.add(pgto2);
		lista.add(pgto3);
		ObservableList<Pagamento> obsList = FXCollections.observableArrayList(lista);
		cbPagamento.setItems(obsList);
	}

	@FXML
	protected void efetuarPagamento(ActionEvent event) throws SQLException {
		if (cbPagamento.getSelectionModel().getSelectedItem() != null) {
			quartoDAO = QuartoDAO.getDAOConnected();
			quartoDAO.updateDisponibilidade(aluguel.getQuarto(), "Disponível");
			dao = AluguelDAO.getDAOConnected();
			dao.delete(aluguel.getId());
			
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setHeaderText("Estadia paga com sucesso!");
			alert.setContentText(null);
			alert.showAndWait();
			dialogStage.close();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Selecione um pagamento antes de encerrar!");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}

	public Aluguel getAluguel() {
		return aluguel;
	}

	public void setAluguel(Aluguel aluguel) throws SQLException {
		this.aluguel = aluguel;
		Diaria diaria = new Diaria(aluguel);
		lblHospede.setText(aluguel.getHospede().getNome());
		lblQuarto.setText(String.valueOf(aluguel.getQuarto().getNmQuarto()));
		dataIn.setValue(LocalDate.parse(String.valueOf(aluguel.getDataEntrada())));
		dataOut.setValue(LocalDate.parse(String.valueOf(aluguel.getDataSaida())));
		lblTotalDiaria.setText(String.valueOf(diaria.getTotal()));
		consumoDAO = ConsumoDAO.getDAOConnected();
		consumo = consumoDAO.get(aluguel.getId());
		totalConsumo = consumo.getTotal();
		lblTotalConsumo.setText(String.valueOf(totalConsumo));
		lblTotal.setText(String.valueOf(totalConsumo + diaria.getTotal()));
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}

}
