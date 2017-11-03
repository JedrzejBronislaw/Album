package jk.album.albumfile.xml.marks;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import jk.album.albumfile.xml.XMLElemanetsName;
import jk.album.domain.marks.Polygon;

public class XMLPolygon {


	public Element save(Polygon p) {
		if(p == null) return null;

		Element elPoly = new Element(XMLElemanetsName.elPolygon);
		for (Point point : p.getPoints()) {
			Element elPoint = new Element(XMLElemanetsName.elPointOfPolygon);
			elPoint.setAttribute(XMLElemanetsName.atX, point.x+"");
			elPoint.setAttribute(XMLElemanetsName.atY, point.y+"");
			elPoly.addContent(elPoint);
		}

		return elPoly;
	}


	public Polygon load(Element el) {
			el = el.getChild(XMLElemanetsName.elPolygon);

			List<Point> points = new ArrayList<Point>();
			List<Element> elPoints = el.getChildren(XMLElemanetsName.elPointOfPolygon);
			for(Element elPoint : elPoints){
				Point p;

				String xStr = elPoint.getAttributeValue(XMLElemanetsName.atX);
				String yStr = elPoint.getAttributeValue(XMLElemanetsName.atY);
				int x,y;
				try {
					x = Integer.parseInt(xStr);
				} catch (NumberFormatException e){
					x = 0;
				}
				try {
					y = Integer.parseInt(yStr);
				} catch (NumberFormatException e){
					y = 0;
				}
				
				p = new Point(x,y);
				points.add(p);
			}
			Polygon object = new Polygon(points);


		return object;
	}
}
