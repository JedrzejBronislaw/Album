package jk.album.loader;

import jk.album.domain.Place;
import jk.album.domain.Being;
import jk.album.domain.Being.Type;

public class FakeGlobalObjectsLoader {

	public Being[] getGlobalObjects() {
		Being o1 = new Being("Adam","pan Adam Nowak").setType(Type.Human);
		Being o2 = new Being("Ewa", "pani Ewa Kowalska").setType(Type.Human);
		Being o3 = new Being("Reksio", "pies Reksio").setType(Type.Animal);
		Being o4 = new Being("Dom Wiœniewskich", "Osiek 15/3").setType(Type.Building);


		return new Being[]{o1,o2,o3,o4};
	}

	public Place[] getGlobalPlaces() {
		Place usa		= new Place("USA", "Stany Zjednoczone Ameryki", null);
		Place polska	= new Place("Polska", "", null);
		Place floryda = new Place("Floryda", "stan USA", usa);
		Place miami	= new Place("Miami", "miasto na Florydzie", floryda);
		Place westKey	= new Place("West Key", "", floryda);
		Place stPeter	= new Place("Sant Petersburg", "", floryda);
		Place tampa	= new Place("Tampa", "", floryda);
		Place buschG	= new Place("Busch Gardens", "park rozwywki", tampa);

		return new Place[]{usa,polska, floryda, miami, westKey, stPeter, tampa, buschG};
	}

}
