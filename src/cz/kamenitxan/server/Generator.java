package cz.kamenitxan.server;

import com.googlecode.jatl.Html;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Generator {
	public static void generate(ArrayList<String> requests) {
		final StringWriter sw = new StringWriter();
		final Html html = new Html(sw);

		html.html();
		html.head();
			html.meta().charset("UTF-8");
			html.title().text("Requests").end();
			html.link().rel("stylesheet").href("https://maxcdn.bootstrapcdn.com/bootstrap/3.3.1/css/bootstrap.min.css");
			html.link().rel("stylesheet").href("img/tablesorter-2.18.3/css/theme.dark.css");
			html.raw("<script src=\"http://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js\"></script>");
			html.raw("<script src=\"img/tablesorter-2.18.3/js/jquery.tablesorter.min.js\"></script>");
			html.raw("<script src=\"img/tablesorter-2.18.3/js/jquery.tablesorter.widgets.js\"></script>");
			html.style().raw(".table-striped>tbody>tr:nth-child(odd) {background-color: rgb(28, 28, 28) !important;}" +
				".table {width: auto;} .role {display: none;} td a {color: inherit}").end();
		html.body().style("color: white; background-color: black;");
		html.h1().text("Seznam požadavků na podporu").end();
		html.raw("<table id=\"myTable\" class=\"table table-striped table-condensed tablesorter\"><thead><tr>" +
				"<th>Jméno</th>" +
				"<th>Oddělení</th>" +
				"<th>Umístění PC</th>" +
				"<th>Telefon</th>" +
				"<th>Restartováno</th>" +
				"<th>Závada</th>" +
				"<th>Popis</th>" +
				"<th>OS</th></tr></thead><tbody>");

		requests.parallelStream().forEach(request -> html.raw(createRow(request)));

		html.raw("</tbody></table>");
		html.script().raw("$(function(){" +
				"		$(\"#myTable\").tablesorter(" +
				"{theme: 'dark', widgets: [\"zebra\", \"filter\"],}" +
				");" +
				"});").end();
		html.endAll();

		final String result = sw.getBuffer().toString();
		try {
			Files.write(Paths.get("requests.html"), result.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("HTML vygenerováno");
	}

	private static String createRow(String request) {
		String formattedOutput = "";
		try {

			TransformerFactory tFactory = TransformerFactory.newInstance();
			Transformer transformer =
					tFactory.newTransformer( new StreamSource( "transformation.xsl" ) );

			StreamSource xmlSource = new StreamSource(new ByteArrayInputStream(request.getBytes("UTF-8")));
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			transformer.transform( xmlSource, new StreamResult( baos ) );

			formattedOutput = baos.toString();

		} catch( Exception e ) {
			e.printStackTrace();
		}
		return formattedOutput;
	}
}
