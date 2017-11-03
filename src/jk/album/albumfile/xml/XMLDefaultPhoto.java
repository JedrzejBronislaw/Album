package jk.album.albumfile.xml;

import org.jdom2.Element;

import jk.album.albumfile.AlbumFile;
import jk.album.domain.Photo;

public class XMLDefaultPhoto {

	private XMLPhotos xmlPhotos;

	public XMLDefaultPhoto(AlbumFile albumFile) {
		xmlPhotos = new XMLPhotos(albumFile);
	}


	public Element save(Photo defaultPhoto) {
//		ArrayList<Photo> photos = new ArrayList<>();
//		photos.add(defaultPhoto);
		return xmlPhotos.fromPhotoToElement(defaultPhoto, XMLElemanetsName.elDefaultPhoto);
//		return xmlPhotos.save(photos)[1];
	}


	public Photo load(Element root) {
		Photo photo = new Photo();
		Element elDefaultPhoto = root.getChild(XMLElemanetsName.elDefaultPhoto);
		if (elDefaultPhoto != null)
			xmlPhotos.fromElementToPhoto(elDefaultPhoto, photo);
		else
			photo = null;
		return photo;
	}

}
