package cz.kamenitxan.klient;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import oshi.SystemInfo;
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
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class XmlCreator {
	private static final String APPVERSION = "2";

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

			Element person = document.createElement("person");
			root.appendChild(person);

			Element name = document.createElement("name");
			name.setTextContent(r.name);
			person.appendChild(name);

			Element department = document.createElement("department");
			department.setTextContent(r.department);
			person.appendChild(department);

			Element place = document.createElement("place");
			place.setTextContent(r.place);
			person.appendChild(place);

			Element phone = document.createElement("phone");
			phone.setTextContent(r.phone);
			person.appendChild(phone);

			Element problem = document.createElement("problem");
			root.appendChild(problem);

			Element createTime = document.createElement("createTime");
			createTime.setTextContent(
					new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime())
			);
			problem.appendChild(createTime);

			Element type = document.createElement("type");
			type.setTextContent(r.type);
			problem.appendChild(type);

			Element restart = document.createElement("restart");
			restart.setTextContent(r.isRestart());
			problem.appendChild(restart);

			Element priority = document.createElement("priority");
			priority.setTextContent("0");
			problem.appendChild(priority);

			Element desc = document.createElement("desc");
			desc.setTextContent(r.desc);
			problem.appendChild(desc);

			Element pc = document.createElement("pc");
			root.appendChild(pc);
			SystemInfo si = new SystemInfo();
			Element os = document.createElement("os");
			os.setTextContent(si.getOperatingSystem().getFamily());
			pc.appendChild(os);

			Element osVersion = document.createElement("osVersion");
			osVersion.setTextContent(si.getOperatingSystem().getVersion().toString());
			pc.appendChild(osVersion);

			Element totalRam = document.createElement("totalRam");
			totalRam.setTextContent(String.valueOf(FormatUtil.formatBytes(si.getHardware().getMemory().getTotal())));
			pc.appendChild(totalRam);

			Element freeRam = document.createElement("freeRam");
			freeRam.setTextContent(String.valueOf(FormatUtil.formatBytes(si.getHardware().getMemory().getAvailable())));
			pc.appendChild(freeRam);

			Element cpu = document.createElement("cpu");
			cpu.setTextContent(si.getHardware().getProcessors()[1].toString());
			pc.appendChild(cpu);

			Element appv = document.createElement("appv");
			appv.setTextContent(APPVERSION);
			pc.appendChild(appv);

			Element action = document.createElement("action");
			root.appendChild(action);

			Element actionTime = document.createElement("actionTime");
			action.appendChild(actionTime);

			Element itWorker = document.createElement("itWorker");
			action.appendChild(itWorker);

			Element resolved = document.createElement("resolved");
			resolved.setTextContent("false");
			action.appendChild(resolved);

			Element problemSource = document.createElement("problemSource");
			action.appendChild(problemSource);

			Element fixes = document.createElement("fixes");
			action.appendChild(fixes);

			return document;
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	private static void sendXML(Document document) {
		final TransformerFactory transformerFactory = TransformerFactory.newInstance();
		try {
			Socket socket = new Socket("localhost", 9999);
			DataOutputStream os = new DataOutputStream(socket.getOutputStream());

			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);

			StreamResult result = new StreamResult(os);
			transformer.transform(domSource, result);

			os.flush();
			socket.close();
			System.out.println("Požadavek odeslán");

		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}


	}

}
