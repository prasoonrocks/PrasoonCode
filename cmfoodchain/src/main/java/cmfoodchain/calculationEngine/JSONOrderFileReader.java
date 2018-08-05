package main.java.cmfoodchain.calculationengine;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class JSONOrderFileReader implements IOrderFileReader{
	
	static final Logger logger = Logger.getLogger(JSONOrderFileReader.class);
	File jSONOrderFile;
	double totalcollection = 0.0;
	double totalOrdersCollection = 0.0;
	String location = null;
	String locationid = null;
	
	public JSONOrderFileReader(File jSONOrderFile) {
		this.jSONOrderFile = jSONOrderFile;
	}

	public Map<String,Object> getValuesFromFile(){
		HashMap<String,Object> valuesMap = new HashMap<>();
		JSONParser parser = new JSONParser();
		try {
			JSONObject jsonFileData = null;
			if(null!=this.jSONOrderFile){
				jsonFileData = (JSONObject) parser.parse(new FileReader(jSONOrderFile));
			}
			else{
				throw new IllegalArgumentException("File cannot be null");
			}

			JSONObject cmfoodchain = (JSONObject) jsonFileData.get("cmfoodchain");
			
			// get branch details
			JSONObject branch = (JSONObject) cmfoodchain.get("branch");
			String branchLocation = (String) branch.get("location");
			valuesMap.put("location", branchLocation);
			String branchTotalcollection = (String) branch.get("totalcollection");
			valuesMap.put("totalcollection", branchTotalcollection);
			String branchLocationid = (String) branch.get("locationid");
			valuesMap.put("locationid", branchLocationid);
			
			// get order details
			JSONObject orders = (JSONObject) cmfoodchain.get("orders");
			JSONArray orderdetails = (JSONArray) orders.get("orderdetail");
			for(Object o : orderdetails){
				JSONObject order = (JSONObject) o;
				double billamount = Double.parseDouble((String) order.get("billamount"));
				totalOrdersCollection+= billamount;
			}
			valuesMap.put("totalOrdersCollection", totalOrdersCollection);
			logger.info("Order Details Successfully fetched from file ");
			
		} catch (FileNotFoundException e) {
			logger.error(" FileNotFoundException occured in getValuesFromFile method : " + e);
		} catch (IOException e) {
			logger.error(" IOException occured in getValuesFromFile method : " + e);
		} catch (ParseException e) {
			logger.error(" ParseException occured in getValuesFromFile method : " + e);
		} catch (IllegalArgumentException e) {
			logger.error(" IllegalArgumentException occured in getValuesFromFile method : " + e);
		}
		return valuesMap;
	}
}
