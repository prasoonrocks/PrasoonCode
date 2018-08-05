package main.java.cmfoodchain.calculationengine;

import java.io.File;

import org.apache.log4j.Logger;

public class CmFoodChainClient {

	static final  Logger logger = Logger.getLogger(CmFoodChainClient.class);

	public static void main(String[] args) {
		
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
