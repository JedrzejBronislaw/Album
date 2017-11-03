package jk.album.albumfile.xml.marks;

import org.jdom2.Element;

import jk.album.albumfile.xml.XMLElemanetsName;
import jk.album.domain.marks.Rect;

public class XMLRect {


	public Element save(Rect r) {
		if(r == null) return null;

		Element elRect = new Element(XMLElemanetsName.elRect);
		elRect.setAttribute(XMLElemanetsName.atTop,    r.getTop()+"");
		elRect.setAttribute(XMLElemanetsName.atBottom, r.getBottom()+"");
		elRect.setAttribute(XMLElemanetsName.atLeft,   r.getLeft()+"");
		elRect.setAttribute(XMLElemanetsName.atRight,  r.getRight()+"");

		return elRect;
	}


	public Rect load(Element el) {
		el = el.getChild(XMLElemanetsName.elRect);

		String tStr = el.getAttributeValue(XMLElemanetsName.atTop);
		String bStr = el.getAttributeValue(XMLElemanetsName.atBottom);
		String lStr = el.getAttributeValue(XMLElemanetsName.atLeft);
		String rStr = el.getAttributeValue(XMLElemanetsName.atRight);
		int t, b, l, r;
		try {
			t = Integer.parseInt(tStr);
		} catch (NumberFormatException e) {
			t = 0;
		}
		try {
			b = Integer.parseInt(bStr);
		} catch (NumberFormatException e) {
			b = 0;
		}
		try {
			l = Integer.parseInt(lStr);
		} catch (NumberFormatException e) {
			l = 0;
		}
		try {
			r = Integer.parseInt(rStr);
		} catch (NumberFormatException e) {
			r = 0;
		}

		Rect object = new Rect(t, r, b, l);

		return object;
	}
}
