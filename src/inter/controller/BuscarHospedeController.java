package inter.controller;

import java.net.URL;
import java.util.ResourceBundle;

import inter.model.dao.HospedeDAO;
import inter.model.domain.Hospede;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuscarHospedeController implements Initializable{

	@FXML
	TableColumn<Hospede, String> clnNome;
	@FXML
	TableColumn<Hospede, String> clnRG;
	@FXML
	TableColumn<Hospede, String> clnTelefone;
	@FXML
	TableView<Hospede> tblHospede;
	HospedeDAO dao;
	private Hospede hospede;
	private Stage dialogStage;
	private boolean selected = false;
	
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	    	this.hospede = tblHospede.getSelectionModel().getSelectedItem();
	    	selected = true;
	    	dialogStage.close();
	    }
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		dao = HospedeDAO.getDAOConnected();
		clnNome.setCellValueFactory(new PropertyValueFactory<Hospede, String>("nome"));
		clnRG.setCellValueFactory(new PropertyValueFactory<Hospede, String>("rg"));
		clnTelefone.setCellValueFactory(new PropertyValueFactory<Hospede, String>("telefone"));
		tblHospede.getItems().addAll(dao.getAll());		
	}

	public Stage getDialogStage() {
		return dialogStage;
	}

	public void setDialogStage(Stage dialogStage) {
		this.dialogStage = dialogStage;
	}
	
	public Hospede getHospede() {
		return hospede;
	}

	public void setHospede(Hospede hospede) {
		this.hospede = hospede;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
