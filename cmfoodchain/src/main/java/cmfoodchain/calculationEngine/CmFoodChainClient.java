package main.java.cmfoodchain.calculationEngine;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;

public class CmFoodChainClient {

	final static Logger logger = Logger.getLogger(CmFoodChainClient.class);

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		
		// trying with XML File
		CalculationEngine ce = new CalculationEngine(new File("file.xml"), new File("match.json") , new File("mismatch.json"));
		ce.matchTotalExpenses();
		logger.info(" XML Successfully calculated ");
		
		// trying with JSON File
		ce = new CalculationEngine(new File("file.json"), new File("match.json") , new File("mismatch.json"));
		ce.matchTotalExpenses();
		logger.info(" JSON Successfully calculated ");
		
	}
}
