package jk.album.albumfile.xml;

import java.util.ArrayList;
import java.util.List;

import javax.naming.directory.InvalidAttributeValueException;

import org.jdom2.Element;

import jk.album.albumfile.AlbumFile;
import jk.album.albumfile.Find;
import jk.album.albumfile.xml.marks.XMLMarks;
import jk.album.domain.Author;
import jk.album.domain.GPSLocation;
import jk.album.domain.Photo;
import jk.album.domain.PhotoFile;
import jk.album.domain.Place;
import jk.album.domain.marks.Mark;

public class XMLPhotos {

	private AlbumFile albumFile;
	private XMLMarks xmlMarks;

	public XMLPhotos(AlbumFile albumFile) {
		this.albumFile = albumFile;
		this.xmlMarks = new XMLMarks(albumFile);
	}


	public Element[] save(List<Photo> photos) {

		if(photos == null) return null;

		Element elPhotoList = new Element(XMLElemanetsName.elPhotoList);
		Element elPhotos = new Element(XMLElemanetsName.elPhotos);

		for (Photo p : photos)
		{
			Element elOfList;
			PhotoFile pf = p.getPhotoFile();
			String gId = p.getGlobalId();
			elOfList = new Element(XMLElemanetsName.elPhotoListElement);
			elOfList.setText(p.getPath());
			elOfList.setAttribute(XMLElemanetsName.atId, p.getId());
			if (gId != null && !gId.isEmpty())
				elOfList.setAttribute(XMLElemanetsName.atGlobalId, gId);
			if (pf.getSize() > 0)
				elOfList.addContent(new Element(XMLElemanetsName.elSize).setText(pf.getSize()+""));
			if (pf.getHeight() > 0)
				elOfList.addContent(new Element(XMLElemanetsName.elWidth).setText(pf.getHeight()+""));
			if (pf.getWidth() > 0)
				elOfList.addContent(new Element(XMLElemanetsName.elHeight).setText(pf.getWidth()+""));
			if (pf.getHash() != null){
				Element elHash = new Element(XMLElemanetsName.elHash);
				elHash.setText(pf.getHash());
				elHash.setAttribute(XMLElemanetsName.atAlgorithm, "SHA-256");
				elOfList.addContent(elHash);
			}
			elPhotoList.addContent(elOfList);

			//-----------------

			Element elPhoto;
			elPhoto = fromPhotoToElement(p,XMLElemanetsName.elPhoto);

			elPhotos.addContent(elPhoto);
		}

		return new Element[]{elPhotoList, elPhotos};
	}


	public List<Photo> load(Element root) {
		List<Element> elPhotos;
		List<Element> elPhotoList;
		ArrayList<Photo> photos;

		Element section;
		section = root.getChild(XMLElemanetsName.elPhotos);
		if (section == null)
			elPhotos = new ArrayList<Element>();
		else
			elPhotos = section.getChildren(XMLElemanetsName.elPhoto);

		section = root.getChild(XMLElemanetsName.elPhotoList);
		if (section == null)
			elPhotoList = new ArrayList<Element>();
		else
			elPhotoList = section.getChildren(XMLElemanetsName.elPhotoListElement);

		photos = new ArrayList<Photo>(elPhotoList.size());
		for (int i=0; i<elPhotoList.size(); i++) {
			Element o = elPhotoList.get(i);
			String id = o.getAttributeValue(XMLElemanetsName.atId);
			String gid = o.getAttributeValue(XMLElemanetsName.atGlobalId);
			String path = o.getTextTrim();
			String sizeStr = o.getChildText(XMLElemanetsName.elSize);
			String widthStr = o.getChildText(XMLElemanetsName.elWidth);
			String heightStr = o.getChildText(XMLElemanetsName.elHeight);
			String hash = o.getChildText(XMLElemanetsName.elHash);

			long size;
			int width, height;
			try {
				size = Long.parseLong(sizeStr);
			} catch (NumberFormatException e) {
				size = 0;
			}
			try {
				width = Integer.parseInt(widthStr);
			} catch (NumberFormatException e) {
				width = 0;
			}
			try {
				height = Integer.parseInt(heightStr);
			} catch (NumberFormatException e) {
				height = 0;
			}

			PhotoFile photoFile = new PhotoFile();
			photoFile.setPath(path);
			photoFile.setSize(size);
			photoFile.setWidth(width);
			photoFile.setHeight(height);
			photoFile.setHash(hash);

			Photo photo = new Photo();
			photo.setId(id);
			photo.setGlobalId(gid);
			photo.setPhotoFile(photoFile);

			photos.add(photo);
		}

		for (int i=0; i<elPhotos.size(); i++) {
			Element o = elPhotos.get(i);
			String id = o.getAttributeValue(XMLElemanetsName.atId);
			fromElementToPhoto(o, Find.inList(photos, id, Photo.class, true));

		}

		return photos;
	}

	Element fromPhotoToElement(Photo p, String elementName){
		Element elPhoto;
		elPhoto = new Element(elementName);
		elPhoto.setAttribute(XMLElemanetsName.atId, p.getId());
		if (p.getAuthor() != null)
			elPhoto.addContent(new Element(XMLElemanetsName.elAuthor).setText(p.getAuthor().getId()));
		if (p.getTitle() != null && !p.getTitle().isEmpty())
			elPhoto.addContent(new Element(XMLElemanetsName.elTitle).setText(p.getTitle()));
		if (p.getPlace() != null)
			elPhoto.addContent(new Element(XMLElemanetsName.elPlace).setText(p.getPlace().getId()));
		if (p.getDate() != null && !p.getDate().isEmpty())
			elPhoto.addContent(new Element(XMLElemanetsName.elDate).setText(p.getDate()));
		if (p.getOccasion() != null && !p.getOccasion().isEmpty())
			elPhoto.addContent(new Element(XMLElemanetsName.elOccasion).setText(p.getOccasion()));
		if (p.getDescription() != null && !p.getDescription().isEmpty())
			elPhoto.addContent(new Element(XMLElemanetsName.elDescription).setText(p.getDescription()));
		if (p.getGps() != null) {
			Element elGps = new Element(XMLElemanetsName.elGps);
			elGps.setAttribute(XMLElemanetsName.atLatitude, p.getGps().getLatitude()+"");
			elGps.setAttribute(XMLElemanetsName.atLongitude, p.getGps().getLongitude()+"");
			elPhoto.addContent(elGps);
		}

		List<String> tags = p.getTags();
		if (tags != null && tags.size()>0){
			Element elTags = new Element(XMLElemanetsName.elTags);
			for (String t : tags)
				elTags.addContent(new Element(XMLElemanetsName.elTag).setText(t));
			elPhoto.addContent(elTags);
		}

		if (p.getMarks() != null &&  p.getMarks().size()>0)
			elPhoto.addContent(xmlMarks .save(p.getMarks()));

		return elPhoto;
	}

	void fromElementToPhoto(Element o, Photo photo){
		String authorId = o.getChildText(XMLElemanetsName.elAuthor);
		String description = o.getChildText(XMLElemanetsName.elDescription);
		String date = o.getChildText(XMLElemanetsName.elDate);
		String occasion = o.getChildText(XMLElemanetsName.elOccasion);
		String placeId = o.getChildText(XMLElemanetsName.elPlace);
		String title = o.getChildText(XMLElemanetsName.elTitle);
		Element elTagSection = o.getChild(XMLElemanetsName.elTags);
		ArrayList<String> tags = new ArrayList<>();
		if (elTagSection != null){
			List<Element> elTags = elTagSection.getChildren(XMLElemanetsName.elTag);
			for (Element e : elTags){
				tags.add(e.getTextTrim());
			}
		}
		Element gpsSection = o.getChild(XMLElemanetsName.elGps);
		GPSLocation gps = null;
		if (gpsSection != null){
			String lat = gpsSection.getAttributeValue(XMLElemanetsName.atLatitude);
			String lon = gpsSection.getAttributeValue(XMLElemanetsName.atLongitude);
			if (lat != null || lon != null)
			{
				gps = new GPSLocation();
				if (lat != null){
					double latitude = Double.parseDouble(lat);
					try {
						gps.setLatitude(latitude);
					} catch (InvalidAttributeValueException e) {
					}
				}
				if (lon != null){
					double longitude = Double.parseDouble(lon);
					gps.setLongitude(longitude);
				}
			}
		}

		List<Mark> marks = xmlMarks.load(o);

		//Photo photo = Find.inList(photos, id, Photo.class, true);//new Photo();
		if (authorId != null)
			photo.setAuthor(Find.inList(albumFile.getAuthors(), authorId, Author.class, true));
		photo.setDescription(description);
		photo.setDate(date);
		photo.setGps(gps);
		photo.setOccasion(occasion);
		if (placeId != null)
			photo.setPlace(Find.inList(albumFile.getPlaces(), placeId, Place.class, true));
		photo.setTags(tags);
		photo.setTitle(title);
		photo.setMarks(marks);
	}
}
