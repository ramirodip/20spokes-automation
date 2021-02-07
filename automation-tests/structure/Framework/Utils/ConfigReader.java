package structure.Framework.Utils;
import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class ConfigReader {
	
	private ConfigReader() {}
	
	public static String GetValue(String tag)
	{
		Element result = null;
		try {
			File inputFile = new File("shared/webtests/TestData/config.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputFile);
			NodeList items = document.getElementsByTagName(tag);
			result = (Element)items.item(0);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return result.getTextContent();
	}

	public static String GetValue(String tag, String path)
	{
		Element result = null;
		try {
			File inputFile = new File(path);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputFile);
			NodeList items = document.getElementsByTagName(tag);
			result = (Element)items.item(0);
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return result.getTextContent();
	}

	public static List<String> GetMultipleValues(String tag)
	{
		List<String> values = new ArrayList<String>();
		List<Element> result = new ArrayList<Element>();
		try {
			File inputFile = new File("shared/webtests/TestData/config.xml");
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputFile);
			NodeList items = document.getElementsByTagName(tag);
			result = (List<Element>)items;
			result.stream().forEachOrdered(x-> values.add(x.getTextContent()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return values;
	}

	public static List<String> GetMultipleValuesFromXml(String tag, String xml)
	{
		List<String> values = new ArrayList<String>();
		List<Element> result = new ArrayList<Element>();
		try {
			File inputFile = new File(xml);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputFile);
			NodeList items = document.getElementsByTagName(tag);
			result = (List<Element>)items;
			result.stream().forEachOrdered(x-> values.add(x.getTextContent()));
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		return values;
	}

	public static String GetRandomValueFromXml(String tag, String xml)
	{
		List<String> values = new ArrayList<String>();
		List<Element> result = new ArrayList<Element>();
		try {
			File inputFile = new File(xml);
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder;
			builder = factory.newDocumentBuilder();
			Document document = builder.parse(inputFile);
			NodeList items = document.getElementsByTagName(tag);
			int leng = items.getLength();
			int i = 0;
			while(i < leng)
			{
				values.add(items.item(i).getTextContent());
				i++;
			}
		} catch (ParserConfigurationException | SAXException | IOException e) {
			e.printStackTrace();
		}
		Random rnd = new Random();
		return values.get(rnd.nextInt(values.size()));
	}
	
}
	
	 	 

