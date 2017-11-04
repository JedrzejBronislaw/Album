package jk.album.albumfile.xml;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import jk.album.albumfile.AlbumFile;
import jk.album.domain.Link;
import jk.album.domain.tools.PathTool;

public class XMLLinks {

	private AlbumFile albumFile;

	public XMLLinks(AlbumFile albumFile) {
		this.albumFile = albumFile;
	}

	public Element save(List<Link> links) {
		if(links == null) return null;

		Element elLinks = new Element(XMLElemanetsName.elLinks);

		for (Link o : links)
		{
			Element elLink;
			elLink = new Element(XMLElemanetsName.elLink);

			String path = PathTool.makeRelativePath(o.getRawPath(), o.getBasePath());
//			path = o.getPath();
			if (path == null) {
				path = o.getRawPath();
				elLink.setAttribute(XMLElemanetsName.atLinkType, XMLElemanetsName.valLinkTypeOther);
			} else {
				o.checkFile();
				elLink.setAttribute(XMLElemanetsName.atCorrectLink, o.fileExists().booleanValue() ? "1" : "0");
				elLink.setAttribute(XMLElemanetsName.atCheckDate, albumFile.getDateFormat().format(o.getCheckDate()));
			}
			elLink.setText(path);
//			link.setAttribute(XMLElemanetsName.atId,o.getId());

			elLinks.addContent(elLink);
		}

		return elLinks;
	}


	public List<Link> load(Element root) {
		List<Element> elLinks;
		ArrayList<Link> links;

		Element section = root.getChild(XMLElemanetsName.elLinks);
		if (section == null)
			elLinks = new ArrayList<Element>();
		else
			elLinks = section.getChildren(XMLElemanetsName.elLink);

		links = new ArrayList<Link>(elLinks.size());
		for (int i=0; i<elLinks.size(); i++) {
			Element a = elLinks.get(i);
			String path = a.getTextTrim();
			Link object = new Link(path);
			links.add(object);
		}

		return links;
	}
}
