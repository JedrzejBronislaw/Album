package jk.album.albumfile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import jk.album.albumfile.xml.XMLAuthors;
import jk.album.albumfile.xml.XMLBeings;
import jk.album.albumfile.xml.XMLDefaultPhoto;
import jk.album.albumfile.xml.XMLElemanetsName;
import jk.album.albumfile.xml.XMLLinks;
import jk.album.albumfile.xml.XMLPhotos;
import jk.album.albumfile.xml.XMLPlaces;
import jk.album.domain.Author;
import jk.album.domain.Being;
import jk.album.domain.Link;
import jk.album.domain.Photo;
import jk.album.domain.Place;
import jk.album.domain.marks.Mark;
import jk.album.domain.tools.PathTool;

public class AlbumFile {

	private int version;

	private String recentFile = null;

	private List<Being>  beings  = new ArrayList<Being>();
	private List<Place>  places  = new ArrayList<Place>();
	private List<Link>   links   = new ArrayList<Link>();
	private List<Author> authors = new ArrayList<Author>();

	private List<Photo> photos = new ArrayList<Photo>();
	private Photo defaultPhoto = null;//new Photo();

//	List<Playlist> playlists;
//	List<Map> maps;

	private XMLBeings  xmlBeings  = new XMLBeings();
	private XMLPlaces  xmlPlaces  = new XMLPlaces();
	private XMLLinks   xmlLinks   = new XMLLinks(this);
	private XMLAuthors xmlAuthors = new XMLAuthors();
	private XMLDefaultPhoto xmlDefaultPhoto  = new XMLDefaultPhoto(this);
	private XMLPhotos  xmlPhotos  = new XMLPhotos(this);

	private SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyy HH:mm:ss");
	private Date createDate = null;
	private Date editDate = null;

	public String getRecentFile() {
		return recentFile;
	}

	public List<Place> getPlaces() {
		return places;
	}

	public void setPlaces(List<Place> places) {
		this.places = places;
	}

	public List<Being> getBeings() {
		return beings;
	}

	public void setBeings(List<Being> beings) {
		this.beings = beings;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public List<Photo> getPhotos() {
		return photos;
	}

	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}

	public Photo getDefaultPhoto() {
		return defaultPhoto;
	}

	public void setDefaultPhoto(Photo defaultPhoto) {
		this.defaultPhoto = defaultPhoto;
	}

	public int getVersion() {
		return version;
	}

	public Date getEditDate() {
		return editDate;
	}
	public Date getCreateDate() {
		return createDate;
	}

	AlbumFile(int version) {
		this.version = version;
	}

	public SimpleDateFormat getDateFormat() {
		return dateFormat;
	}

	public boolean load(String filePath){

		File file = new File(filePath);
		filePath = PathTool.tryCanonicalPath(filePath);
		recentFile = filePath;

		SAXBuilder saxBuilder = new SAXBuilder();

		Document document;
		try {
			document = saxBuilder.build(file);
		} catch (JDOMException|IOException e) {
			e.printStackTrace();
			return false;
		}
		Element root = document.getRootElement();

		String versionStr = root.getAttributeValue(XMLElemanetsName.atVersion);
		try {
			version = Integer.parseInt(versionStr);
		} catch (NumberFormatException e){
			version = 0;
		}
		String editDateStr = root.getAttributeValue(XMLElemanetsName.atEditTime);
		String createDateStr = root.getAttributeValue(XMLElemanetsName.atCreateTime);
		try {editDate   = dateFormat.parse(editDateStr);}   catch (ParseException e) {editDate = null;}
		try {createDate = dateFormat.parse(createDateStr);} catch (ParseException e) {createDate = null;}

		beings  = xmlBeings.load(root);
		places  = xmlPlaces.load(root);
		links   = xmlLinks.load(root);
		authors = xmlAuthors.load(root);
		photos  = xmlPhotos.load(root);
		defaultPhoto = xmlDefaultPhoto.load(root);

		addBasePathToLinks(PathTool.paretnPath(filePath));


		return true;
	}

	public boolean save(String filePath){
		File file = new File(filePath);
		try {
			filePath = file.getCanonicalPath();
		} catch (IOException e1) {
			filePath = file.getAbsolutePath();
		}
		recentFile = filePath;

		Element root = new Element(XMLElemanetsName.rootName);
		Document doc = new Document(root);

		addBasePathToLinks(PathTool.paretnPath(filePath));
		checkCohesion();
		editDate = new Date();
		if (createDate == null) createDate = new Date();

		root.setAttribute(XMLElemanetsName.atVersion,version+"");
		root.setAttribute(XMLElemanetsName.atCreateTime,dateFormat.format(createDate));
		root.setAttribute(XMLElemanetsName.atEditTime,dateFormat.format(editDate));
//		root.setAttribute(XMLElemanetsName.atMainFile,"yes");

		if (beings  != null && beings.size()  > 0)	root.addContent(xmlBeings.save(beings));
		if (places  != null && places.size()  > 0)	root.addContent(xmlPlaces.save(places));
		if (links   != null && links.size()   > 0)	root.addContent(xmlLinks.save(links));
		if (authors != null && authors.size() > 0)	root.addContent(xmlAuthors.save(authors));
		if (defaultPhoto != null)					root.addContent(xmlDefaultPhoto.save(defaultPhoto));
		if (photos  != null && photos.size()  > 0) {
			Element[] photoSections = xmlPhotos.save(photos);
			root.addContent(photoSections[0]);
			root.addContent(photoSections[1]);
		}
//		if (elPhotoList != null) root.addContent(elPhotoList);
//		if (elPhotos != null) root.addContent(elPhotos);

		XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
		FileOutputStream foStream;
		try {
			foStream = new FileOutputStream(file);
			outputter.output(doc, foStream);
			foStream.close();
		} catch (IOException e) {
			return false;
//		} finally {
		}

		return true;
	}


	private void addBasePathToLinks(String filePath) {
		if (links == null) links = new ArrayList<>();

		for(Link l : links)
			l.setBasePath(filePath);
	}

	private void checkCohesion()
	{
		if (places  == null) places  = new ArrayList<Place>();
		if (beings  == null) beings  = new ArrayList<Being>();
		if (authors == null) authors = new ArrayList<Author>();
		if (photos  == null) photos  = new ArrayList<Photo>();

		for(int i=0; i<places.size(); i++){
			Place p = places.get(i);
			Place superPlace = p.getPlace();

			if (superPlace != null && !places.contains(superPlace))
				places.add(superPlace);
		}

		for(Photo p : photos){
			Place place = p.getPlace();
			Author author = p.getAuthor();

			if (place != null && !places.contains(place))
				places.add(place);

			if (author != null && !authors.contains(author))
				authors.add(author);
		}
		if(defaultPhoto != null){
			Place place = defaultPhoto.getPlace();
			Author author = defaultPhoto.getAuthor();

			if (place != null && !places.contains(place))
				places.add(place);

			if (author != null && !authors.contains(author))
				authors.add(author);
		}

		for(Photo p : photos){
			if (p.getMarks() != null)
			for(Mark m : p.getMarks()){
				if (m != null && m.getBeing() != null && !beings.contains(m.getBeing()))
					beings.add(m.getBeing());
			}
		}
	}

}
