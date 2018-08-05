package main.java.cmfoodchain.calculationengine;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.json.JSONException;
import org.json.JSONObject;

public class JSONOrderFileWriter implements IOrderFileWriter{

	static final Logger logger = Logger.getLogger(JSONOrderFileWriter.class);

	HashMap<String,Object> valuesMap = null;

	public JSONOrderFileWriter(Map<String,Object> valuesMap) {
		this.valuesMap = (HashMap<String, Object>) valuesMap;
	}

	public void writeToFile(File file){
		// Initialize
		JSONObject cmfoodchain = new JSONObject();
		JSONObject branch = new JSONObject();
		JSONObject orders = new JSONObject();
		
		//prepare objects to write
		try {
			orders.put("location", valuesMap.get("location"));
			orders.put("totalcollection", valuesMap.get("totalcollection"));
			orders.put("sumoforder", valuesMap.get("totalOrdersCollection"));
			orders.put("locationid", valuesMap.get("locationid"));
			branch.put("branch", orders);
			cmfoodchain.put("cmfoodchain", branch);
		} catch (JSONException e) {
			logger.error(" IOException occured in writeToJSON method : " + e);
		}

		// write objects to file
		try(FileWriter fileW = new FileWriter(file)){
			fileW.write(cmfoodchain.toString());
			fileW.flush();
			logger.info("Successfully written objects to json file ");
		} catch (IOException e) {
			logger.error(" IOException occured in writeToJSON method : " + e);
		}

	}
}

