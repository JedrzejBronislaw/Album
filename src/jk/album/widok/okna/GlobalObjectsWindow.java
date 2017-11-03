package jk.album.widok.okna;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class GlobalObjectsWindow extends  Application {


	public void go() {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/GlobalObjectsWindow.fxml"));

		BorderPane parent = loader.load();


		Scene scene = new Scene(parent);
		primaryStage.setScene(scene);

		primaryStage.setTitle("Obiekty globalne");
		primaryStage.show();
	}
}
