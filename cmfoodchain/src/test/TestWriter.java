package test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;

import org.apache.log4j.Logger;
import org.junit.Test;

import main.java.cmfoodchain.calculationEngine.JSONOrderFileReader;
import main.java.cmfoodchain.calculationEngine.XMLOrderFileReader;

public class TestWriter {

	final static Logger logger = Logger.getLogger(TestWriter.class);
	
	@Test
	public void testXMLOrderFileReader() {
		XMLOrderFileReader testObj = new XMLOrderFileReader(new File("file.xml"));	
		assertFalse(testObj.getValuesFromFile().isEmpty());
		System.out.println(" successful test");
	}

	@Test
	public void testXMLOrderFileReaderNull() {
		XMLOrderFileReader testObj = new XMLOrderFileReader(null);	
		assertTrue(testObj.getValuesFromFile().isEmpty());
		System.out.println(" successful test");
	}
	
	@Test
	public void testJSONOrderFileReader() {
		JSONOrderFileReader testObj = new JSONOrderFileReader(new File("file.json"));	
		assertFalse(testObj.getValuesFromFile().isEmpty());
		System.out.println(" successful test");
	}
	
	@Test
	public void testJSONOrderFileReaderNull() {
		JSONOrderFileReader testObj = new JSONOrderFileReader(null);	
		assertTrue(testObj.getValuesFromFile().isEmpty());
		System.out.println(" successful test");
	}

}
