package uk.co.eleusis.lambda.java7;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Read a list of Maps from a file. 
 * 
 * Each line looks like this:
 * 
 *   key1=val1; key2=val2; key3=val3 ...
 * 
 * Lines should have one or more properties.
 * Each line can have a different number of properties.
 * 
 * Each line can be parsed into a simple Map.
 * 
 * Yes, I still use hanging braces. Deal with it.
 * 
 * @author keithm
 *
 */
public class MapFileParser 
{
	private String filename;
	
	public MapFileParser(String filename)
	{
		this.filename = filename;
	}
	
	public void readMapFile(MapProcessor processor) throws IOException 
	{
		// Java 7 try-with-resources syntax
		try (BufferedReader reader = new BufferedReader(new FileReader(filename))) 
		{
			String line = reader.readLine();
			while (line != null)
			{
				Map<String, String> map = parseLineIntoMap(line);
				processor.process(map);
				line = reader.readLine();
			}
		}
		
	}

	private Map<String, String> parseLineIntoMap(String line) 
	{
		// Java 7 generic type inference
		Map<String, String> map = new HashMap<>();

		// OK, this is poor, no error checking - but it's not the point of
		// the example, so we'll live with it for now.
		String[] entries = line.split("; ");
		for (String entry : entries)
		{
			String[] parts = entry.split("=");
			map.put(parts[0].trim(), parts[1].trim());
		}
		
		return map;
	}
}
