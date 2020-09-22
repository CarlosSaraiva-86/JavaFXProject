package inter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ResourceBundle;

import inter.Main;
import inter.model.dao.ConsumoDAO;
import inter.model.domain.Aluguel;
import inter.model.domain.Consumo;
import inter.model.domain.Diaria;
import inter.model.domain.Servico;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class EncerrarEstadiaController implements Initializable {

	@FXML
	Label lblHospede;
	@FXML
	Label lblQuarto;
	@FXML
	Label lblTotal;
	@FXML
	Label lblTotalServico;
	@FXML
	Label lblTipo;
	@FXML
	Label lblDiaria;
	@FXML
	Label lblValor;
	@FXML
	Label lblValorDiaria;
	@FXML
	DatePicker dataIn;
	@FXML
	DatePicker dataOut;
	@FXML
	TableColumn<Servico, String> clnServico;
	@FXML
	TableColumn<Servico, Float> clnValor;
	@FXML
	TableView<Servico> tblServico;

	Aluguel aluguel;
	Consumo consumo;
	ConsumoDAO consumoDAO;
	private float totalConsumo;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clnServico.setCellValueFactory(new PropertyValueFactory<Servico, String>("tipoServico"));
		clnValor.setCellValueFactory(new PropertyValueFactory<Servico, Float>("valor"));
	}

	@FXML
	protected void buscarEstadia(ActionEvent event) throws IOException, SQLException {
		aluguel = new Aluguel();
		boolean selectedAluguel = showBuscaAluguel(aluguel);
		if (selectedAluguel) {
			Diaria diaria = new Diaria(aluguel);
			lblHospede.setText(aluguel.getHospede().getNome());
			lblQuarto.setText(String.valueOf(aluguel.getQuarto().getNmQuarto()));
			dataIn.setValue(LocalDate.parse(String.valueOf(aluguel.getDataEntrada())));
			dataOut.setValue(LocalDate.parse(String.valueOf(aluguel.getDataSaida())));
			lblTipo.setText(aluguel.getQuarto().getTipo());
			lblValorDiaria.setText(String.valueOf(aluguel.getQuarto().getValor()));
			lblDiaria.setText(String.valueOf(diaria.getDiaria()));
			lblValor.setText(String.valueOf(diaria.getTotal()));
			carregarServicos(aluguel.getId());
			lblTotal.setText(String.valueOf(totalConsumo + diaria.getTotal()));
		}
	}

	@FXML
	protected void encerrarEstadia(ActionEvent event) throws IOException, SQLException {
		if (aluguel != null) {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Main.class.getResource("views/PagamentoEstadiaView.fxml"));
			AnchorPane root = (AnchorPane) loader.load();
			
			Stage dialogStage = new Stage();
			dialogStage.setTitle("Encerrar Estadia");
			Scene scene = new Scene(root);
			dialogStage.setScene(scene);

			PagamentoEstadiaController controller = loader.getController();
			controller.setDialogStage(dialogStage);
			controller.setAluguel(aluguel);

			dialogStage.showAndWait();
			
			limparCampos();
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Selecione uma estadia antes de encerrar!");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}

	private void limparCampos() {
		lblDiaria.setText(null);
		lblHospede.setText(null);
		lblQuarto.setText(null);
		lblTipo.setText(null);
		lblTotal.setText(null);
		lblTotalServico.setText(null);
		lblValor.setText(null);
		lblValorDiaria.setText(null);
		consumo = null;
		aluguel = null;
		tblServico.getItems().clear();
	}

	private boolean showBuscaAluguel(Aluguel model) throws IOException {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("views/BuscarAluguelView.fxml"));
		AnchorPane root = (AnchorPane) loader.load();
		Stage dialogStage = new Stage();
		dialogStage.setTitle("Busca Estadia");
		Scene scene = new Scene(root);
		dialogStage.setScene(scene);

		BuscarAluguelController controller = loader.getController();
		controller.setDialogStage(dialogStage);
		controller.setAluguel(aluguel);

		dialogStage.showAndWait();
		this.aluguel = controller.getAluguel();
		return controller.isSelected();
	}

	private void carregarServicos(int idAluguel) throws SQLException {
		tblServico.getItems().clear();
		consumoDAO = ConsumoDAO.getDAOConnected();
		consumo = consumoDAO.get(idAluguel);
		tblServico.getItems().addAll(consumo.getServicos());
		totalConsumo = consumo.getTotal();
		lblTotalServico.setText(String.valueOf(totalConsumo));
	}
}
