package cz.kamenitxan.klient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import oshi.SystemInfo;
import oshi.software.os.OperatingSystem;
import oshi.util.FormatUtil;


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

	public static void startCreator(Request request) {
		sendXML(createXML(request));
	}

	private static Document createXML(Request r) {
		final DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
		try {
			final DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("pozadavek");
			document.appendChild(root);

			Element name = document.createElement("name");
			name.setTextContent(r.name);
			root.appendChild(name);

			Element department = document.createElement("department");
			department.setTextContent(r.department);
			root.appendChild(department);

			Element place = document.createElement("place");
			place.setTextContent(r.place);
			root.appendChild(place);

			Element phone = document.createElement("phone");
			phone.setTextContent(r.phone);
			root.appendChild(phone);

			Element type = document.createElement("type");
			type.setTextContent(r.type);
			root.appendChild(type);

			Element desc = document.createElement("desc");
			desc.setTextContent(r.desc);
			root.appendChild(desc);

			SystemInfo si = new SystemInfo();
			Element os = document.createElement("os");
			os.setTextContent(si.getOperatingSystem().getFamily());
			root.appendChild(os);

			Element osVersion = document.createElement("osVersion");
			osVersion.setTextContent(si.getOperatingSystem().getVersion().toString());
			root.appendChild(osVersion);

			Element totalRam = document.createElement("totalRam");
			totalRam.setTextContent(String.valueOf(FormatUtil.formatBytes(si.getHardware().getMemory().getTotal())));
			root.appendChild(totalRam);

			Element freeRam = document.createElement("freeRam");
			freeRam.setTextContent(String.valueOf(FormatUtil.formatBytes(si.getHardware().getMemory().getAvailable())));
			root.appendChild(freeRam);

			Element cpu = document.createElement("cpu");
			cpu.setTextContent(si.getHardware().getProcessors()[1].toString());
			root.appendChild(cpu);

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
