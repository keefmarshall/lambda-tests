package uk.co.eleusis.lambda.java7;

import java.util.Map;

/**
 * Interface for calling classes to implement in order to process
 * Maps read in from the MapFileReader
 * 
 * @author keithm
 *
 */
public interface MapProcessor
{
	void process(Map<String, String> map);
}