package main.java.cmfoodchain.calculationengine;

import java.util.Map;

public class OrderFileWriterFactory {
	
	private OrderFileWriterFactory(){}

	public static IOrderFileWriter createOrderFileWriter(String fileType, Map<String, Object> valuesMap){
		if("json".equalsIgnoreCase(fileType)){
			return new JSONOrderFileWriter(valuesMap);
		}
		return null;
	}
}
