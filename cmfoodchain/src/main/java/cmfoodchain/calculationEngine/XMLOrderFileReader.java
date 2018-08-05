package main.java.cmfoodchain.calculationengine;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLOrderFileReader implements IOrderFileReader{
	
	static final Logger logger = Logger.getLogger(XMLOrderFileReader.class);
	File xMLOrderFile;
	double totalcollection = 0.0;
	double totalOrdersCollection = 0.0;
	String location = null;
	String locationid = null;
	
	public XMLOrderFileReader(File xMLOrderFile){
		this.xMLOrderFile = xMLOrderFile;
	}

	public Map<String,Object> getValuesFromFile(){
		HashMap<String,Object> valuesMap = new HashMap<>();
		DocumentBuilderFactory builderFactory =	DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		Document document = null;
		try {
			builder = builderFactory.newDocumentBuilder();
			if(null!=this.xMLOrderFile){
			document= builder.parse(this.xMLOrderFile);
			}
			else{
				throw new IllegalArgumentException("File cannot be null");
			}

			NodeList branchList = document.getElementsByTagName("branch");
			for(int i=0; i<branchList.getLength(); i++ ){
				Node p = branchList.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element branch = (Element) p;
					NodeList branchDetails = branch.getChildNodes();
					for(int j = 0; j<branchDetails.getLength(); j++){
						Node n1 = branchDetails.item(j);
						if(n1.getNodeType()==Node.ELEMENT_NODE){
							Element br = (Element) n1;
							if("totalcollection".equals(br.getTagName())){
								totalcollection=Double.parseDouble(br.getTextContent());
								valuesMap.put("totalcollection", totalcollection);
							}
							if("location".equals(br.getTagName())){
								location=br.getTextContent();
								valuesMap.put("location", location);
							}
							if("locationid".equals(br.getTagName())){
								locationid=br.getTextContent();
								valuesMap.put("locationid", locationid);
							}
						}
					}
				}
			}

			NodeList orderList = document.getElementsByTagName("orders");
			for(int i=0; i<orderList.getLength(); i++ ){
				Node p = orderList.item(i);
				if(p.getNodeType()==Node.ELEMENT_NODE){
					Element branch = (Element) p;
					NodeList orderDetails = branch.getChildNodes();
					for(int j = 0; j<orderDetails.getLength(); j++){
						Node n1 = orderDetails.item(j);
						if(n1.getNodeType()==Node.ELEMENT_NODE){
							Element br = (Element) n1;
							NodeList orders = br.getChildNodes();
							for(int k = 0; k<orders.getLength(); k++){
								Node o1 = orders.item(k);
								if(o1.getNodeType()==Node.ELEMENT_NODE){
									Element or = (Element) o1;
									if("billamount".equals(or.getTagName())){
										totalOrdersCollection+=Double.parseDouble(or.getTextContent());
									}
								}
							}
							valuesMap.put("totalOrdersCollection", totalOrdersCollection);
						}
					}
				}
			}

		} catch (ParserConfigurationException e) {
			logger.error(" ParserConfigurationException occured in getValuesFromFile method : " + e);
		}catch (SAXException e) {
			logger.error(" SAXException occured in getValuesFromFile method : " + e);
		} catch (IOException e) {
			logger.error(" IOException occured in getValuesFromFile method : " + e);
		} catch (IllegalArgumentException e) {
			logger.error(" IllegalArgumentException occured in getValuesFromFile method : " + e);
		}
		return valuesMap;
	}
}
