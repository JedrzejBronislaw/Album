package jk.album.domain;

import jk.album.domain.tools.IIdGenerator;
import jk.album.domain.tools.IdGenerator;

public class Author implements IdentifiedById{

	private String globalId;

	private String id;
	private String name;
	private String description;

	private IIdGenerator idGen = new IdGenerator();

	public Author() {
		id = idGen.generate();
	}
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
//	public IIdGenerator getIdGen() {
//		return idGen;
//	}
//
//	public void setIdGen(IIdGenerator idGen) {
//		this.idGen = idGen;
//	}
}
