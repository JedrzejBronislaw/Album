package jk.album.widok.okna;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class MainWindow extends Application {

//	private ImageView iv;

	public void go() {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/MainWindow.fxml"));

		AnchorPane panel = loader.load();
//		MainWindowController controller = loader.getController();
		Scene scene = new Scene(panel);

//		try {
//		Image image = new Image("/malta.jpg");
//		Image image = new Image("file:E:\\programy\\Album\\JAVA\\Album\\res\\malta.jpg");
//		String url = "file:H:\\zdjecia\\STARE ZDJÊCIA\\DO ZROBIENIA\\2017.04.20 zdjêcia od w. Henryka Krajewskiego\\Obraz 005.jpg";
//		Image image = new Image(url, 4000, 0, true, true);

//		if (image.isError())
//			System.out.println("image == null");
//		else
//		{
//			HBox box = new HBox();
//			ImageView iv = //new ImageView(image);

//			iv.setImage(image);
//			iv.setFitHeight(400);
//			iv.setPreserveRatio(true);
//			iv.setSmooth(true);
//			panel.getChildren().add(iv);

//			ImageView iv2 = new ImageView(image);
//			iv2.setFitHeight(400);
//			Rectangle2D obszar = new Rectangle2D(700, 700, 300, 300);
//			iv2.setViewport(obszar);
//			iv2.setPreserveRatio(true);
//			iv2.setSmooth(false);

//			box.getChildren().add(iv);
//			box.getChildren().add(iv2);
//			panel.getChildren().add(box);
//		}

//		} catch (Exception  e)
//		{
//			System.out.println("Exception: " + e.getMessage());
//			e.printStackTrace();
//		}
//		Button button = new Button("imvi");
//		button.setOnMouseClicked(new EventHandler<Event>() {
//		});

		primaryStage.setScene(scene);
		primaryStage.setWidth(500);
		primaryStage.setHeight(500);
		primaryStage.setTitle("Album");
		primaryStage.show();
	}

//	public void klik() {
//		Image image = new Image("/malta.jpg");
//		iv = controller.getImvi();
//
//			iv.setImage(image);
//			iv.setFitHeight(400);
//			iv.setPreserveRatio(true);
//			iv.setSmooth(true);
//	}

}
