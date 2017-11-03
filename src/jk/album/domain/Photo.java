package jk.album.domain;

import java.util.ArrayList;
import java.util.List;

import jk.album.domain.marks.Mark;
import jk.album.domain.tools.IIdGenerator;
import jk.album.domain.tools.IdGenerator;

public class Photo implements IdentifiedById{

	private String globalId;

	private String id;
//	private String path;
	private PhotoFile photoFile = new PhotoFile();
	private Author author;
	private String title;
	private Place place;
	private String date; //TODO docelowo wymienic na dedykowany typ
	private String occasion;
	private String description;
	private List<String> tags = new ArrayList<>();;
	private GPSLocation gps;
	private List<Mark> marks = new ArrayList<>();

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
	public PhotoFile getPhotoFile() {
		return photoFile;
	}
	public void setPhotoFile(PhotoFile photoFile) {
		this.photoFile = photoFile;
	}
	public String getPath() {
		return photoFile.getPath();
	}
	public void setPath(String path) {
		photoFile.setPath(path);
//		photoFile = new PhotoFile(new File(path));
	}
	public Author getAuthor() {
		return author;
	}
	public void setAuthor(Author author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Place getPlace() {
		return place;
	}
	public void setPlace(Place place) {
		this.place = place;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getOccasion() {
		return occasion;
	}
	public void setOccasion(String occasion) {
		this.occasion = occasion;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getTags() {
		return tags;
	}
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	public GPSLocation getGps() {
		return gps;
	}
	public void setGps(GPSLocation gps) {
		this.gps = gps;
	}
	public void setMarks(List<Mark> marks) {
		this.marks = marks;
	}
	public List<Mark> getMarks() {
		return marks;
	}

	public Photo() {
		id = idGen.generate();
	}
}
