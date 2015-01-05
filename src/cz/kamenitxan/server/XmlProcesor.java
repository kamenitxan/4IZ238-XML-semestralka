package cz.kamenitxan.server;

import cz.kamenitxan.klient.Request;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class XmlProcesor implements Runnable {
	Socket clientSocket = null;
	static int i = 0;
	private Map<Integer, Request> requests = new HashMap<>();

	public XmlProcesor(Socket clientSocket, boolean cleanStart) {
		this.clientSocket = clientSocket;
	}

	@Override
	public void run() {
		try {
			DataInputStream in = new DataInputStream(clientSocket.getInputStream());
			processXml(convertStreamToString(in));
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private void processXml(String xml) {
		i++;
		System.out.println(i + xml);
		File schemaFile = new File("schema.xsd");

		try {
			Source xmlFile = new StreamSource(new ByteArrayInputStream(xml.getBytes("UTF-8")));
			SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = schemaFactory.newSchema(schemaFile);
			Validator validator = schema.newValidator();
			validator.validate(xmlFile);
			System.out.println("Požadavek je validní");
			ServerReciever.addRequest(xml);
		} catch (SAXException e) {
			System.out.println("Požadavek není validní");
			System.out.println("Reason: " + e.getLocalizedMessage());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	static String convertStreamToString(java.io.InputStream is) {
		try(java.util.Scanner s = new java.util.Scanner(is)) {
			return s.useDelimiter("\\A").hasNext() ? s.next() : "";
		}
	}
}
