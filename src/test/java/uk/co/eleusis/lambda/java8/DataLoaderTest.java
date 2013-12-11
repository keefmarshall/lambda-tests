package uk.co.eleusis.lambda.java8;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;

/**
 * Tests for DataLoader class - java8 version
 * 
 * @author keithm
 *
 */
public class DataLoaderTest 
{
	private final static String TEST_FILENAME = "src/test/resources/mapfile";

	@Test
	public void testLoadMapsIntoDatabase() throws Exception
	{
		DataLoader loader = new DataLoader();
		
		// pseudo-database, we're just using a list for now!
		List<Map<String, String>> database = new ArrayList<>();
		
		loader.loadMapsIntoDatabase(TEST_FILENAME, database);
		
		assertEquals(16, database.size());
		assertEquals("val4", database.get(1).get("key1"));
	}

	@Test
	public void testFilterMapsByProperty() throws Exception
	{
		DataLoader loader = new DataLoader();
		
		List<Map<String, String>> filtered = loader.filterMapsByProperty(TEST_FILENAME);
		
		assertNotNull(filtered);
		assertEquals(5, filtered.size());
		assertEquals("val16", filtered.get(1).get("key1"));
	}
}
