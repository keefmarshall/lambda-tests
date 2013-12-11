package uk.co.eleusis.lambda.java8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.function.Consumer;
import java.util.HashMap;
import java.util.Map;

import uk.co.eleusis.lambda.java7.MapProcessor;

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
 * THIS CLASS USES JAVA 8 PREVIEW RELEASE 1.8.0_ea - JSR 335 IS NOT YET FINAL
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
	
	public void readMapFile(Consumer<Map<String, String>> processor) throws IOException 
	{
		// Java 7 try-with-resources syntax
		Path file = Paths.get(filename);
		try (BufferedReader reader = Files.newBufferedReader(file, StandardCharsets.UTF_8)) 
		{
			String line = reader.readLine();
			while (line != null)
			{
				Map<String, String> map = parseLineIntoMap(line);
				processor.accept(map);
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
