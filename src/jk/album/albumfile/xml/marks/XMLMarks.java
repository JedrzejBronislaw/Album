package jk.album.albumfile.xml.marks;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import jk.album.albumfile.AlbumFile;
import jk.album.albumfile.Find;
import jk.album.albumfile.xml.XMLElemanetsName;
import jk.album.domain.Being;
import jk.album.domain.marks.Circle;
import jk.album.domain.marks.Mark;
import jk.album.domain.marks.Polygon;
import jk.album.domain.marks.Rect;
import jk.album.domain.marks.Shapeless;
import jk.album.domain.marks.Spot;

public class XMLMarks {

	private AlbumFile albumFile;
	private XMLShapeless xmlShapeless = new XMLShapeless();
	private XMLSpot      xmlSpot      = new XMLSpot();
	private XMLRect      xmlRect      = new XMLRect();
	private XMLCircle    xmlCircle    = new XMLCircle();
	private XMLPolygon   xmlPolygon   = new XMLPolygon();

	public XMLMarks(AlbumFile albumFile) {
		this.albumFile = albumFile;
	}

	public Element save(List<Mark> marks) {
		if(marks == null) return null;

		Element elMarks = new Element(XMLElemanetsName.elMarks);

		for (Mark m : marks)
		{
			Element mark;
			mark = new Element(XMLElemanetsName.elMark);
			if (m.getBeing() != null)
				mark.setAttribute(XMLElemanetsName.atBeing, m.getBeing().getId());

			if (m instanceof Shapeless){
				mark.setAttribute(XMLElemanetsName.atMarkType, XMLElemanetsName.valMarkTypeShapeless);
			} else
			if (m instanceof Spot){
				mark.setAttribute(XMLElemanetsName.atMarkType, XMLElemanetsName.valMarkTypeSpot);
				mark.addContent(xmlSpot.save((Spot) m));
			} else
			if (m instanceof Rect){
				mark.setAttribute(XMLElemanetsName.atMarkType, XMLElemanetsName.valMarkTypeRect);
				mark.addContent(xmlRect.save((Rect) m));

			} else
			if (m instanceof Circle){
				mark.setAttribute(XMLElemanetsName.atMarkType, XMLElemanetsName.valMarkTypeCircle);
				mark.addContent(xmlCircle.save((Circle) m));
			}else
			if (m instanceof Polygon){
				mark.setAttribute(XMLElemanetsName.atMarkType, XMLElemanetsName.valMarkTypePolygon);
				mark.addContent(xmlPolygon.save((Polygon) m));
			}

			elMarks.addContent(mark);
		}

		return elMarks;
	}


	public List<Mark> load(Element root) {
		List<Element> elMarks;
		ArrayList<Mark> marks;

		Element section = root.getChild(XMLElemanetsName.elMarks);
		if (section == null)
			elMarks = new ArrayList<Element>();
		else
			elMarks = section.getChildren(XMLElemanetsName.elMark);


		marks = new ArrayList<Mark>(elMarks.size());
		for (int i=0; i<elMarks.size(); i++) {
			Element a = elMarks.get(i);
			String beingId = a.getAttributeValue(XMLElemanetsName.atBeing);
			Being being = Find.inList(albumFile.getBeings(), beingId, Being.class, false);
			String type = a.getAttributeValue(XMLElemanetsName.atMarkType);

			Mark object = null;
			if (type.equals(XMLElemanetsName.valMarkTypeShapeless)) object = xmlShapeless.load(a); else
			if (type.equals(XMLElemanetsName.valMarkTypeSpot))      object = xmlSpot.load(a); else
			if (type.equals(XMLElemanetsName.valMarkTypeRect))      object = xmlRect.load(a); else
			if (type.equals(XMLElemanetsName.valMarkTypeCircle))    object = xmlCircle.load(a); else
			if (type.equals(XMLElemanetsName.valMarkTypePolygon))   object = xmlPolygon.load(a);

			if (object != null)
				object.setBeing(being);

			if (object != null) marks.add(object);
		}

		return marks;
	}
}
