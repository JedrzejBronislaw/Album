package jk.album.albumfile.xml;

import java.util.ArrayList;
import java.util.List;

import org.jdom2.Element;

import jk.album.domain.Author;

public class XMLAuthors {

	public Element save(List<Author> authors) {
		if(authors == null) return null;

		Element elAuthors = new Element(XMLElemanetsName.elAuthors);

		for (Author o : authors)
		{
			Element author, elDescription;
			String description = o.getDescription();
			String gId = o.getGlobalId();
			author = new Element(XMLElemanetsName.elAuthor);
			author.setText(o.getName());
			author.setAttribute(XMLElemanetsName.atId,o.getId());
			if (gId != null && !gId.isEmpty())
				author.setAttribute(XMLElemanetsName.atGlobalId,gId);
			if (description!=null && !description.isEmpty()) {
				elDescription = new Element(XMLElemanetsName.elDescription);
				elDescription.setText(description);
				author.addContent(elDescription);
			}

			elAuthors.addContent(author);
		}

		return elAuthors;
	}


	public List<Author> load(Element root) {
		List<Element> elAuthors;
		ArrayList<Author> authors;

		Element section = root.getChild(XMLElemanetsName.elAuthors);
		if (section == null)
			elAuthors = new ArrayList<Element>();
		else
			elAuthors = section.getChildren(XMLElemanetsName.elAuthor);

		authors = new ArrayList<Author>(elAuthors.size());
		for (int i=0; i<elAuthors.size(); i++) {
			Element a = elAuthors.get(i);
			String id = a.getAttributeValue(XMLElemanetsName.atId);
			String gid = a.getAttributeValue(XMLElemanetsName.atGlobalId);
			String name = a.getTextTrim();
			String description = a.getChildText(XMLElemanetsName.elDescription);
			Author object = new Author();
			object.setId(id);
			object.setGlobalId(gid);
			object.setName(name);
			object.setDescription(description);
			authors.add(object);
		}

		return authors;
	}
}
