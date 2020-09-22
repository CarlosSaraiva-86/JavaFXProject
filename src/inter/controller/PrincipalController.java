package inter.controller;

import java.io.IOException;

import inter.Main;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;

public class PrincipalController {
	@FXML 
	private AnchorPane anchorPrincipal;
	
	
	@FXML
	protected void exibirAlugar(ActionEvent event) {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/AlugarQuartoView.fxml"));
			anchorPrincipal.getChildren().setAll(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@FXML
	protected void exibirHospede(ActionEvent event) {
		try {
			AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/ManterHospedeView.fxml"));
			anchorPrincipal.getChildren().setAll(a);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			 
	}
	
	@FXML
	protected void exibirServicos(ActionEvent event) {
		try {
			 AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/ManterServicosView.fxml"));
			 anchorPrincipal.getChildren().setAll(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@FXML
	protected void exibirFechar(ActionEvent event) {
		try {
			 AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/EncerrarEstadiaView.fxml"));
			 anchorPrincipal.getChildren().setAll(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	@FXML
	protected void exibirQuarto(ActionEvent event) {
		try {
			 AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/ManterQuartosView.fxml"));
			 anchorPrincipal.getChildren().setAll(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	
	@FXML
	protected void exibirReserva(ActionEvent event) {
		try {
			 AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/ReservarQuartoView.fxml"));
			 anchorPrincipal.getChildren().setAll(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
	
	@FXML
	protected void cadastrarServicos(ActionEvent event) {
		try {
			 AnchorPane a = (AnchorPane) FXMLLoader.load(Main.class.getResource("views/CadastrarServicoView.fxml"));
			 anchorPrincipal.getChildren().setAll(a);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}	
}
