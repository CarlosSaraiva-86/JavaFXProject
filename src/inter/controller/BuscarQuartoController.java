package inter.controller;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import inter.model.dao.QuartoDAO;
import inter.model.domain.Quarto;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class BuscarQuartoController implements Initializable {

	@FXML
	TableColumn<Quarto, Integer> clnNumero;
	@FXML
	TableColumn<Quarto, String> clnTipo;
	@FXML
	TableColumn<Quarto, Float> clnValor;
	@FXML
	TableView <Quarto> tblQuarto;
	
	QuartoDAO dao;
	private Quarto quarto;
	private Stage dialogStage;
	private boolean selected = false;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		dao = QuartoDAO.getDAOConnected();
		clnNumero.setCellValueFactory(new PropertyValueFactory<Quarto, Integer>("nmQuarto"));
		clnTipo.setCellValueFactory(new PropertyValueFactory<Quarto, String>("tipo"));
		clnValor.setCellValueFactory(new PropertyValueFactory<Quarto, Float>("valor"));
		try {
			tblQuarto.getItems().addAll(dao.getAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@FXML
	public void clickItem(MouseEvent event)
	{
	    if (event.getClickCount() == 2) //Checking double click
	    {
	    	this.quarto = tblQuarto.getSelectionModel().getSelectedItem();
	    	selected = true;
	    	dialogStage.close();
	    }
	}
	public Quarto getQuarto() {
		return quarto;
	}

	public void setQuarto(Quarto quarto) {
		this.quarto = quarto;
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
