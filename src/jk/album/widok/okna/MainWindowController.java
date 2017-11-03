package jk.album.widok.okna;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class MainWindowController {

	@FXML
	private ImageView imvi;

	@FXML
	private ListView<String> listview;

	public ImageView getImvi() {
		return imvi;
	}

	@FXML
	void initialize()
	{}

	@FXML
	public void klik(){
		System.out.println("klik");

//		Image image = new Image("/malta.jpg");
		Image image = new Image("file:E:\\programy\\Album\\JAVA\\Album\\res\\malta.jpg");

		imvi.setImage(image);
		imvi.setFitHeight(400);
		imvi.setPreserveRatio(true);
		imvi.setSmooth(true);

		ObservableList<String> value = FXCollections.observableArrayList(
			"jeden", "dwa", "trzy", "jeden", "dwa", "trzy", "jeden", "dwa", "trzy", "jeden", "dwa", "trzy", "jeden", "dwa", "trzy"
		);
		listview.setItems(value);
	}
}
