package inter;
	
import java.io.IOException;

import inter.Main;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;


public class Main extends Application {
	private Stage primaryStage;	
	private VBox layoutPrincipal;
	
	
	@Override
	public void start(Stage primaryStage) {
		
			this.primaryStage = primaryStage;
			this.primaryStage.setTitle("Projeto Hotel");
			setLayoutPrincipal();
	}
	
	
	private void setLayoutPrincipal() {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("views/PrincipalView.fxml"));
            layoutPrincipal = (VBox) loader.load();
            

            Scene scene = new Scene(layoutPrincipal);
            primaryStage.setScene(scene);
            primaryStage.show();
            
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	
	
	public static void main(String[] args) {
		launch(args);
	}
}
