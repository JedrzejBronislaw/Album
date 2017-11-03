package jk.album.albumfile.xml;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Element;

import jk.album.domain.Place;

public class XMLPlaces {

//	AlbumFile albumFile;
//
//	public XmlPlaces(AlbumFile albumFile) {
//		this.albumFile = albumFile;
//	}

	public Element save(List<Place> places) {
		if(places == null) return null;

		Element elPlaces = new Element(XMLElemanetsName.elPlaces);

		for (Place p : places)
		{
			Element place, elDescription;
			String description = p.getDescription();
			String gId = p.getGlobalId();
			place = new Element(XMLElemanetsName.elPlace);
			place.setText(p.getName());
			if (p.getPlace() != null)
				place.setAttribute(XMLElemanetsName.atSuperPlace, p.getPlace().getId());
			place.setAttribute(XMLElemanetsName.atId,p.getId());
			if (gId != null && !gId.isEmpty())
				place.setAttribute(XMLElemanetsName.atGlobalId,gId);
			if (description!=null && !description.isEmpty()) {
				elDescription = new Element(XMLElemanetsName.elDescription);
				elDescription.setText(description);
				place.addContent(elDescription);
			}

			elPlaces.addContent(place);
		}

		return elPlaces;
	}

	public List<Place> load(Element root) {
		List<Element> elPlaces;
		Map<Place, String> superPlaces = new HashMap<>();
		ArrayList<Place> places;

		Element section = root.getChild(XMLElemanetsName.elPlaces);
		if (section == null)
			elPlaces = new ArrayList<Element>();
		else
			elPlaces = section.getChildren(XMLElemanetsName.elPlace);

		places = new ArrayList<Place>(elPlaces.size());
		for (int i=0; i<elPlaces.size(); i++) {
			Element p = elPlaces.get(i);
			String id = p.getAttributeValue(XMLElemanetsName.atId);
			String gid = p.getAttributeValue(XMLElemanetsName.atGlobalId);
			String name = p.getTextTrim();
			String description = p.getChildText(XMLElemanetsName.elDescription);
			String superPlace = p.getAttributeValue(XMLElemanetsName.atSuperPlace);
			Place place = new Place(id, name, description, null);
			place.setGlobalId(gid);
			if (superPlace != null && !superPlace.equals(""))
				superPlaces.put(place, superPlace);
			places.add(place);
		}


		for (Place p : places){
			String superPlace = superPlaces.get(p);
			if (superPlace != null)
				for (int i=0; i<places.size(); i++)
					if (places.get(i).getId().equals(superPlace)){
						p.setPlace(places.get(i));
						break;
					}
		}


		return places;
	}
}
