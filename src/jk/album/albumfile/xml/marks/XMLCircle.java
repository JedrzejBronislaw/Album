package jk.album.albumfile.xml.marks;

import org.jdom2.Element;

import jk.album.albumfile.xml.XMLElemanetsName;
import jk.album.domain.marks.Circle;

public class XMLCircle {


	public Element save(Circle c) {
		if(c == null) return null;

		Element elCircle = new Element(XMLElemanetsName.elCircle);
		elCircle.setAttribute(XMLElemanetsName.atX, c.getX()+"");
		elCircle.setAttribute(XMLElemanetsName.atY, c.getY()+"");
		elCircle.setAttribute(XMLElemanetsName.atR, c.getR()+"");

		return elCircle;
	}


	public Circle load(Element el) {
		el = el.getChild(XMLElemanetsName.elCircle);

		String xStr = el.getAttributeValue(XMLElemanetsName.atX);
		String yStr = el.getAttributeValue(XMLElemanetsName.atY);
		String rStr = el.getAttributeValue(XMLElemanetsName.atR);
		int x, y, r;
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
		try {
			r = Integer.parseInt(rStr);
		} catch (NumberFormatException e) {
			r = 0;
		}

		Circle object = new Circle(x, y, r);

		return object;
	}
}
