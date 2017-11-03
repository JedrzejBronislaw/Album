package jk.album.albumfile.xml.marks;

import org.jdom2.Element;

import jk.album.albumfile.xml.XMLElemanetsName;
import jk.album.domain.marks.Spot;

public class XMLSpot {


	public Element save(Spot s) {
		if(s == null) return null;

		Element elSpot = new Element(XMLElemanetsName.elSpot);
		elSpot.setAttribute(XMLElemanetsName.atX, s.getX()+"");
		elSpot.setAttribute(XMLElemanetsName.atY, s.getY()+"");

		return elSpot;
	}


	public Spot load(Element el) {
		el = el.getChild(XMLElemanetsName.elSpot);

		String xStr = el.getAttributeValue(XMLElemanetsName.atX);
		String yStr = el.getAttributeValue(XMLElemanetsName.atY);
		int x, y;
		try {
			x = Integer.parseInt(xStr);
		} catch (NumberFormatException e) {
			x = 0;
		}
		try {
			y = Integer.parseInt(yStr);
		} catch (NumberFormatException e) {
			y = 0;
		}

		Spot object = new Spot(x, y);

		return object;
	}
}
