package inter.controller;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ResourceBundle;

import inter.model.dao.ReservaDAO;
import inter.model.domain.Reserva;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuscarReservaController implements Initializable{
	@FXML
	TableColumn<Reserva, String> clnHospede;
	@FXML
	TableColumn<Reserva, String> clnQuarto;
	@FXML
	TableColumn<Reserva, Date> clnEntrada;
	@FXML
	TableColumn<Reserva, Date> clnSaida;
	@FXML
	TableView <Reserva> tblReserva;
	
	ReservaDAO dao;
	private Reserva reserva;
	private Stage dialogStage;
	private boolean selected = false;
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	    	this.reserva = tblReserva.getSelectionModel().getSelectedItem();
	    	selected = true;
	    	dialogStage.close();
	    }
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = ReservaDAO.getDAOConnected();
		clnHospede.setCellValueFactory((param) -> new SimpleStringProperty(param.getValue().getHospede().getNome()));
		clnQuarto.setCellValueFactory((param) -> new SimpleStringProperty(String.valueOf(param.getValue().getQuarto().getNmQuarto())));
		clnEntrada.setCellValueFactory(new PropertyValueFactory<Reserva, Date>("dataEntrada"));
		clnSaida.setCellValueFactory(new PropertyValueFactory<Reserva, Date>("dataSaida"));
		try {
			tblReserva.getItems().addAll(dao.getAll());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Reserva getReserva() {
		return reserva;
	}
	public void setReserva(Reserva reserva) {
		this.reserva = reserva;
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
