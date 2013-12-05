package uk.co.eleusis.lambda.java7;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

public class DataLoaderTest
{

	@Test
	public void testLoadMapsIntoDatabase() throws Exception
	{
		DataLoader loader = new DataLoader();
		
		// pseudo-database, we're just using a list for now!
		List<Map<String, String>> database = new ArrayList<>();
		String filename = "src/test/resources/mapfile";
		
		loader.loadMapsIntoDatabase(filename, database);
		
		assertEquals(16, database.size());
		assertEquals("val4", database.get(1).get("key1"));
	}


}
