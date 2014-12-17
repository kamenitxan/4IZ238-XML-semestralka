package cz.kamenitxan.klient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XmlCreator {

	public static void startCreator() {
		sendXML(createXML());
	}

	private static Document createXML() {
		final DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("pozadavek");
			document.appendChild(root);

			Element name = document.createElement("name");
			root.appendChild(name);

			Element department = document.createElement("department");
			root.appendChild(department);

			Element place = document.createElement("place");
			root.appendChild(place);

			Element phone = document.createElement("phone");
			root.appendChild(phone);

			// TODO: multiple choice

			Element desc = document.createElement("desc");
			root.appendChild(desc);

			return document;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void sendXML(Document document) {
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);


			StreamResult result = new StreamResult(System.out);

			transformer.transform(domSource, result);
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		}


	}

}
