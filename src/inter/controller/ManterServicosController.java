package inter.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import inter.Main;
import inter.model.dao.ConsumoDAO;
import inter.model.dao.ServicoDAO;
import inter.model.domain.Aluguel;
import inter.model.domain.Consumo;
import inter.model.domain.Servico;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class ManterServicosController implements Initializable {

	@FXML
	ComboBox<Servico> cbServico;
	@FXML
	Label lblQuarto;
	@FXML
	Label lblHospede;
	@FXML
	Label lblTotal;
	@FXML
	TableColumn<Servico, String> clnServico;
	@FXML
	TableColumn<Servico, Float> clnValor;
	@FXML
	TableView<Servico> tblServico;

	Aluguel aluguel;
	ServicoDAO servicoDAO;
	ConsumoDAO dao;
	Consumo consumo = new Consumo();

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		clnServico.setCellValueFactory(new PropertyValueFactory<Servico, String>("tipoServico"));
		clnValor.setCellValueFactory(new PropertyValueFactory<Servico, Float>("valor"));
		try {
			carregarCombo();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@FXML
	protected void buscarEstadia(ActionEvent event) throws IOException, SQLException {
		aluguel = new Aluguel();
		boolean selectedAluguel = showBuscaAluguel(aluguel);
		if (selectedAluguel) {
			lblHospede.setText(aluguel.getHospede().getNome());
			lblQuarto.setText(String.valueOf(aluguel.getQuarto().getNmQuarto()));
			carregarServicos(aluguel.getId());
		}
	}

	@FXML
	protected void inserirServico(ActionEvent event) throws SQLException {
		if (aluguel != null) {
			if (cbServico.getSelectionModel().getSelectedItem() != null) {
				dao = ConsumoDAO.getDAOConnected();
				dao.create(aluguel.getId(), cbServico.getSelectionModel().getSelectedItem().getId());
				carregarServicos(aluguel.getId());
			} else {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setHeaderText("Selecione um serviço antes de inserir!");
				alert.setContentText(null);
				alert.showAndWait();
			}
		} else {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setHeaderText("Selecione uma estadia para inserir um serviço!");
			alert.setContentText(null);
			alert.showAndWait();
		}
	}

	private void carregarServicos(int idAluguel) throws SQLException {
		tblServico.getItems().clear();
		dao = ConsumoDAO.getDAOConnected();
		consumo = dao.get(idAluguel);
		tblServico.getItems().addAll(consumo.getServicos());
		lblTotal.setText(String.valueOf(consumo.getTotal()));
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

	private void carregarCombo() throws SQLException {
		servicoDAO = ServicoDAO.getDAOConnected();
		ObservableList<Servico> obsList = FXCollections.observableArrayList(servicoDAO.getAll());
		cbServico.setItems(obsList);
	}
}
