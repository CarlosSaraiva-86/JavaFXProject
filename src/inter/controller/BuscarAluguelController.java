package inter.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import inter.model.dao.AluguelDAO;
import inter.model.domain.Aluguel;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuscarAluguelController implements Initializable {
	@FXML
	TableColumn<Aluguel, String> clnHospede;
	@FXML
	TableColumn<Aluguel, String> clnQuarto;
	@FXML
	TableColumn<Aluguel, Date> clnEntrada;
	@FXML
	TableColumn<Aluguel, Date> clnSaida;
	@FXML
	TableView <Aluguel> tblAluguel;
	
	AluguelDAO dao;
	private Aluguel aluguel;
	private Stage dialogStage;
	private boolean selected = false;
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	    	this.aluguel = tblAluguel.getSelectionModel().getSelectedItem();
	    	selected = true;
	    	dialogStage.close();
	    }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = AluguelDAO.getDAOConnected();
		clnHospede.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getHospede().getNome()));
		clnQuarto.setCellValueFactory((param) -> new SimpleStringProperty(String.valueOf(param.getValue().getQuarto().getNmQuarto())));
		clnEntrada.setCellValueFactory(new PropertyValueFactory<Aluguel, Date>("dataEntrada"));
		clnSaida.setCellValueFactory(new PropertyValueFactory<Aluguel, Date>("dataSaida"));
		try {
			tblAluguel.getItems().addAll(dao.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public Aluguel getAluguel() {
		return aluguel;
	}
	public void setAluguel(Aluguel aluguel) {
		this.aluguel = aluguel;
	}
	public Stage getDialogStage() {
		return dialogStage;
	}
	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	public boolean isSelected() {
		return selected;
	}
	public void setSelected(boolean selected) {
		this.selected = selected;
	}
	
}
