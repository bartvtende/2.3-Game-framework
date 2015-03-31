package test.java.org.hanzet23.gameframework;

import java.util.LinkedHashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.org.hanzet23.gameframework.models.SettingsModel;

/**
 * Unit test for the Settings class
 */
public class SettingsTest extends TestCase {

	public SettingsTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(SettingsTest.class);
	}
	
	/**
	 * Tests if the setting file contains the specified info
	 */
	public void testReadingTheSettingsFile() {
		SettingsModel settings = new SettingsModel("settings_for_testing.conf");
		
		LinkedHashMap<String, String> map = settings.getSettings();
		
		String[] actualFirst1 = new String[]{"test1", "test34", "test99"};
		String[] actualSecond1 = new String[]{"hallo_dit_is_een_test", "nog_een_test_hallo", "hoi"};

		for (int i = 0; i < actualFirst1.length; i++) {
			assertTrue(map.containsKey(actualFirst1[i]));
			assertTrue(map.containsValue(actualSecond1[i]));
		}
	}
	
	/**
	 * Tests writing a key value pair to the settings file
	 */
	public void testWritingTheSettingsFile() {
		SettingsModel settings = new SettingsModel("settings_for_testing.conf");
		
		boolean check1 = settings.writeSettings("test1", "wauw");

		assertTrue(check1);
		
		LinkedHashMap<String, String> map1 = settings.getSettings();
		
		assertTrue(map1.containsKey("test1"));
		assertTrue(map1.containsValue("wauw"));
		
		boolean check2 = settings.writeSettings("test1", "hallo_dit_is_een_test");
		
		assertTrue(check2);

		LinkedHashMap<String, String> map2 = settings.getSettings();
		
		assertTrue(map2.containsKey("test1"));
		assertTrue(map2.containsValue("hallo_dit_is_een_test"));
	}
	
	/**
	 * Tests if you can write a value to a non-existing key
	 */
	public void testWritingANonExistingKeyToSettingsFile() {
		SettingsModel settings = new SettingsModel("settings_for_testing.conf");
		
		boolean check = settings.writeSettings("test1337", "wauw");
		
		assertFalse(check);

		LinkedHashMap<String, String> map1 = settings.getSettings();
		
		assertFalse(map1.containsKey("test1337"));
	}
}
