package jk.album.domain;

import jk.album.domain.tools.IIdGenerator;
import jk.album.domain.tools.IdGenerator;

public class Place implements IdentifiedById{

	private String globalId;

	private String id;
	private String name;
	private String description;
	private Place place;

	private IIdGenerator idGen = new IdGenerator();

	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}
	@Override
	public String getGlobalId() {
		return globalId;
	}
	@Override
	public void setGlobalId(String id) {
		this.globalId = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}

//	public void generateId(){
//		Random r = new Random();
//		id = String.format("%X",r.nextLong());
//	}

	public Place() {
		id = idGen.generate();
	}
	public Place(String name, String description, Place place) {
		id = idGen.generate();
		this.name = name;
		this.description = description;
		this.place = place;
	}
	public Place(String id, String name, String description, Place place) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.place = place;
	}


}
