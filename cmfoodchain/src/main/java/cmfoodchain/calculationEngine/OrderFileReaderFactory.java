package main.java.cmfoodchain.calculationengine;

import java.io.File;

public class OrderFileReaderFactory {
	
	private OrderFileReaderFactory(){}

	public static IOrderFileReader createOrderFileReader(String fileType, File inputFile){
		if("xml".equalsIgnoreCase(fileType)){
			return new XMLOrderFileReader(inputFile);
		}
		else if("json".equalsIgnoreCase(fileType)){
			return new JSONOrderFileReader(inputFile);
		}
		return null;
	}
	
}
