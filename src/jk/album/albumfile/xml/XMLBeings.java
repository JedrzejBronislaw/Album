package jk.album.albumfile.xml;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import jk.album.domain.Being;
import jk.album.domain.Being.Type;
//import jk.album.loader.xml.XMLElemanetsName;

public class XMLBeings {


	public Element save(List<Being> beings) {
		if(beings == null) return null;

		Element elBeings = new Element(XMLElemanetsName.elBeings);

		for (Being o : beings)
		{
			Element being, elDescription;
			String description = o.getDescription();
			String gId = o.getGlobalId();
			being = new Element(XMLElemanetsName.elBeing);
			being.setText(o.getName());
			being.setAttribute(XMLElemanetsName.atTypeBeings, o.getType().toString());
			being.setAttribute(XMLElemanetsName.atId,o.getId());
			if (gId != null && !gId.isEmpty())
				being.setAttribute(XMLElemanetsName.atGlobalId,gId);
			if (description!=null && !description.isEmpty()) {
				elDescription = new Element(XMLElemanetsName.elDescription);
				elDescription.setText(description);
				being.addContent(elDescription);
			}

			elBeings.addContent(being);
		}

		return elBeings;
	}


	public List<Being> load(Element root) {
		List<Element> elBeings;
		ArrayList<Being> beings;

		Element section = root.getChild(XMLElemanetsName.elBeings);
		if (section == null)
			elBeings = new ArrayList<Element>();
		else
			elBeings = section.getChildren(XMLElemanetsName.elBeing);

		beings = new ArrayList<Being>(elBeings.size());
		for (int i=0; i<elBeings.size(); i++) {
			Element o = elBeings.get(i);
			String id = o.getAttributeValue(XMLElemanetsName.atId);
			String gid = o.getAttributeValue(XMLElemanetsName.atGlobalId);
			String name = o.getTextTrim();
			String description = o.getChildText(XMLElemanetsName.elDescription);
			String type = o.getAttributeValue(XMLElemanetsName.atTypeBeings);
			Being object = new Being(id, name, description).setType(Type.valueOf(type));
			object.setGlobalId(gid);
			beings.add(object);
		}

		return beings;
	}
}
