package jk.album.widok.okna;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TreeCell;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import jk.album.albumfile.AlbumFile;
import jk.album.albumfile.AlbumFileFactory;
import jk.album.domain.Being;
import jk.album.domain.Photo;
import jk.album.domain.Place;

public class GlobalObjectsWindowController {

	private static String path = "obiektyGlobalne.xml";

	@FXML
	private ListView<Being> lvBeings;

	@FXML
	private TreeView<Place> tvPlaces;

	@FXML
	private Label beingDes;

	@FXML
	private Label placeDes;

	@FXML
	public void initialize() {
//		GlobalObjectsLoader loader;
		AlbumFile albumFile = AlbumFileFactory.build(1);
		albumFile.load(path);
		List<Being> objects = albumFile.getBeings();
		List<Place> places = albumFile.getPlaces();

//		try {
//			loader = new XMLGlobalObjectsLoader("obiektyGlobalne.xml");
			// loader = new FakeGlobalObjectsLoader();
//			objects = loader.getGlobalObjects();
//			places = loader.getGlobalPlaces();

			ObservableList<Being> values = FXCollections.observableArrayList();

			for (Being o : objects)
				values.add(o);

			lvBeings.setItems(values);
			lvBeings.setCellFactory(new Callback<ListView<Being>, ListCell<Being>>() {

				@Override
				public ListCell<Being> call(ListView<Being> param) {
					return new BeingCellFactory();
				}
			});

			lvBeings.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent event) {
					String opis = "";
					Being selectedItem = lvBeings.selectionModelProperty().getValue().getSelectedItem();

					if (selectedItem != null)
						opis = selectedItem.getDescription();

					if (opis == null) System.out.println("opis jest null");

					beingDes.setText(opis);
				}
			});

			Place[] placesTab = new Place[places.size()];
			places.toArray(placesTab);
			TreeItem<Place> rootPlace = treeItem(null, placesTab);
			tvPlaces.setRoot(rootPlace);
			// miejsca.setEditable(true);
			tvPlaces.setCellFactory(new Callback<TreeView<Place>, TreeCell<Place>>() {

				@Override
				public TreeCell<Place> call(TreeView<Place> param) {

					return new PlaceCellFactory();
				}
			});

			tvPlaces.setOnMouseClicked(new EventHandler<MouseEvent>() {
				@Override
				public void handle(MouseEvent event) {
					String opis = "";

					TreeItem<Place> selectedItem = tvPlaces.getSelectionModel().getSelectedItem();

					if (selectedItem != null)
						opis = selectedItem.getValue().getDescription();

					placeDes.setText(opis);
				}
			});

			System.out.println("Obiektów: " + objects.size());
			System.out.println("Miejsc: " + places.size());
//		} catch (IOException | JDOMException e) {
//			System.out.println("Nie mo¿na otworzy pliku");
//			e.printStackTrace();
//		}

		Photo photo1 = new Photo();
		photo1.setPath("pic1.jpg");
		photo1.setTitle("Tytul 1");
		photo1.setPlace(places.get(4));
		photo1.setDate("data 1");
		photo1.setOccasion("okazja 1");
		photo1.setDescription("opis 1");
		List<String> tags1 = new ArrayList<>();
		tags1.add("tag 1_1");
		tags1.add("tag 1_2");
		tags1.add("tag 1_3");
		tags1.add("tag 1_4");
		photo1.setTags(tags1);

		Photo photo2 = new Photo();
		photo2.setPath("pic2.jpg");
		photo2.setTitle("Tytul 2");
		photo2.setPlace(places.get(4));
		photo2.setDate("data 2");
		photo2.setOccasion("okazja 2");
		photo2.setDescription("opis 2");
		List<String> tags2 = new ArrayList<>();
		tags2.add("tag 2_1");
		tags2.add("tag 2_2");
		tags2.add("tag 2_3");
		photo2.setTags(tags2);


//		XMLGlobalObjectsSaver saver = new XMLGlobalObjectsSaver("obiektyGlobalne.xml");
		List<Photo> photos = new ArrayList<Photo>();
		photos.add(photo1);
		photos.add(photo2);
		albumFile.setPhotos(photos);
		albumFile.save(path);
//		saver.setGlobalObjects(objects);
//		saver.setGlobalPlaces(places);
//		saver.addPhoto(photo1);
//		saver.addPhoto(photo2);
//		saver.save();
	}

	private TreeItem<Place> treeItem(Place place, Place[] places) {
		TreeItem<Place> item;

		if (place == null)
			item = new TreeItem<>(new Place("Miejsca", "", null));
		else
			item = new TreeItem<>(place);

		for (Place p : places)
			if (p.getPlace() == place)
				item.getChildren().add(treeItem(p, places));

		return item;
	}

	class PlaceCellFactory extends TreeCell<Place> {

		@Override
		protected void updateItem(Place item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
			} else {
				setText(item.getName());
			}
		}

	}

	class BeingCellFactory extends ListCell<Being> {
		@Override
		protected void updateItem(Being item, boolean empty) {
			super.updateItem(item, empty);

			if (empty) {
				setText(null);
			} else {
				setText(item.getName());
			}
		}
	}
}
