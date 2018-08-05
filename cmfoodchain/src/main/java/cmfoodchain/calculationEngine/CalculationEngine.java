package main.java.cmfoodchain.calculationengine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;

import org.apache.log4j.Logger;

public class CalculationEngine {
	
	static final Logger logger = Logger.getLogger(CalculationEngine.class);

	File inputFile;
	File match;
	File mismatch;

	public CalculationEngine(File inputFile, File match, File mismatch) {
		this.inputFile = inputFile;
		this.match = match;
		this.mismatch = mismatch;
	}
	
	public String identifyFileTypeUsingFilesProbeContentType(final File file)
	{
	   String fileType = "Undetermined";
	   try
	   {
	      fileType = Files.probeContentType(file.toPath());
	   }
	   catch (IOException e)
	   {
		  logger.error(" IOException occured in identifyFileTypeUsingFilesProbeContentType method : " + e);
	   }
	   return fileType;
	}

	public void matchTotalExpenses(){
		// initialize
		IOrderFileReader orderReader = null;
		IOrderFileWriter orderWriter = null;
		HashMap<String,Object> valuesMap = null;
		
		// determine file type
		String inputFileType = identifyFileTypeUsingFilesProbeContentType(this.inputFile);
		
		// calculate values
		if("text/xml".equals(inputFileType)){
			logger.info(" XML format detected ");
			orderReader = new XMLOrderFileReader(inputFile);
			valuesMap = (HashMap<String, Object>) orderReader.getValuesFromFile();
		}
		else{
			logger.info(" JSON format detected ");
			orderReader = new JSONOrderFileReader(inputFile);
			valuesMap = (HashMap<String, Object>) orderReader.getValuesFromFile();
		}
		
		// Initialize writer
		orderWriter = new JSONOrderFileWriter(valuesMap);
		
		// write values to file
		if(valuesMap.get("totalcollection") == valuesMap.get("totalOrdersCollection")){
			orderWriter.writeToFile(match);		
			logger.info("Expenses Matched correctly! Successfully written objects to match.json file ");
		}
		else{
			orderWriter.writeToFile(mismatch);		
			logger.info("Expenses Mismatch occured, logged objects to mismatch.json file ");
		}

	}

}

