package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

import main.java.cmfoodchain.calculationengine.JSONOrderFileReader;
import main.java.cmfoodchain.calculationengine.XMLOrderFileReader;

public class TestWriter {

	static final Logger logger = Logger.getLogger(TestWriter.class);
	static final String SUCCESS= "Test ran Successfully";
	
	@Test
	public void testXMLOrderFileReader() {
		XMLOrderFileReader testObj = new XMLOrderFileReader(new File("file.xml"));	
		assertFalse(testObj.getValuesFromFile().isEmpty());
		logger.info(SUCCESS);
	}

	@Test
	public void testXMLOrderFileReaderNull() {
		XMLOrderFileReader testObj = new XMLOrderFileReader(null);	
		assertTrue(testObj.getValuesFromFile().isEmpty());
		logger.info(SUCCESS);
	}
	
	@Test
	public void testJSONOrderFileReader() {
		JSONOrderFileReader testObj = new JSONOrderFileReader(new File("file.json"));	
		assertFalse(testObj.getValuesFromFile().isEmpty());
		logger.info(SUCCESS);
	}
	
	@Test
	public void testJSONOrderFileReaderNull() {
		JSONOrderFileReader testObj = new JSONOrderFileReader(null);	
		assertTrue(testObj.getValuesFromFile().isEmpty());
		logger.info(SUCCESS);
	}

}
