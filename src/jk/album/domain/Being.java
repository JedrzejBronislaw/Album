package jk.album.domain;

import jk.album.domain.tools.IIdGenerator;
import jk.album.domain.tools.IdGenerator;

public class Being implements IdentifiedById{

	public enum Type {Human, Animal, Building, Object, Other};

	private String globalId;

	private String id;
	private String name;
	private String description;
	private Type type = Type.Other;

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

	public Being setType(Type type) {
		this.type = type;
		return this;
	}
	public Type getType() {
		return type;
	}

//	public void generateId(){
//		Random r = new Random();
//		id = String.format("%X",r.nextLong());
//	}

	public Being() {
		id = idGen.generate();
	}

	public Being(String name, String description) {
		id = idGen.generate();
		this.name = name;
		this.description = description;
	}

	public Being(String id, String name, String description) {
		this.id = id;
		this.name = name;
		this.description = description;
	}


}
