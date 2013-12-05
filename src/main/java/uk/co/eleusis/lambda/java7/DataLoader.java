package uk.co.eleusis.lambda.java7;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Load map data into different places
 * 
 * @author keithm
 *
 */
public class DataLoader 
{
	/**
	 * Add each map into a database, one by one.
	 * 
	 * @param filename
	 * @param database
	 * @throws IOException
	 */
	public void loadMapsIntoDatabase(String filename, 
			final Collection<Map<String, String>> database) throws IOException
	{
		MapFileParser parser = new MapFileParser(filename);
		parser.readMapFile(new MapProcessor() {
			
			@Override
			public void process(Map<String, String> map) 
			{
				database.add(map);
			}
		});
	}

	/**
	 * Inspect each map, if it has a property key of 'include' then add it
	 * to the list to be returned.
	 * 
	 * @param filename
	 * @return
	 */
	public List<Map<String, String>> filterMapsByProperty(String filename)
		throws IOException
	{
		final List<Map<String, String>> filteredList = new ArrayList<>();
		MapFileParser parser = new MapFileParser(filename);
		parser.readMapFile(new MapProcessor() {
			
			@Override
			public void process(Map<String, String> map) 
			{
				if (map.containsKey("include"))
				{
					filteredList.add(map);
				}
			}
		});
		
		return filteredList;
	}
}
