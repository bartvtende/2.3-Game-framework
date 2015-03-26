package test.java.org.hanzet23.gameframework;

import static org.junit.Assert.assertArrayEquals;

import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import main.java.org.hanzet23.gameframework.models.CommandModel;

/**
 * Unit test for the Command class
 */
public class CommandTest extends TestCase {

	public CommandTest(String testName) {
		super(testName);
	}

	public static Test suite() {
		return new TestSuite(CommandTest.class);
	}
	
	public void testParseListNull() {
		String testStringNull1 = "";
		String testStringNull2 = "testing but no list, oops";
		String testStringNull3 = "tstdtsdt [";
		String testStringNull4 = "tstdtsdt ]";

		assertNull(CommandModel.parseList(testStringNull1));
		assertNull(CommandModel.parseList(testStringNull2));
		assertNull(CommandModel.parseList(testStringNull3));
		assertNull(CommandModel.parseList(testStringNull4));
	}
	
	public void testParseListTrue() {
		String testStringTrue1 = "testing test [\"test1\", \"test2\"]";
		String testStringTrue2 = "testing test [test1, test2]";
		String testStringTrue3 = "testing test [test1,test2]";
		String testStringTrue4 = "testing test [test1 , test2]";
		
		String[] actual1 = CommandModel.parseList(testStringTrue1);
		String[] actual2 = CommandModel.parseList(testStringTrue2);
		String[] actual3 = CommandModel.parseList(testStringTrue3);
		String[] actual4 = CommandModel.parseList(testStringTrue4);
		
		String[] expected1 = {"test1", "test2"};
		String[] expected2 = {"test1", "test2"};
		String[] expected3 = {"test1", "test2"};
		String[] expected4 = {"test1", "test2"};
		
		assertArrayEquals(expected1, actual1);
		assertArrayEquals(expected2, actual2);
		assertArrayEquals(expected3, actual3);
		assertArrayEquals(expected4, actual4);
		assertArrayEquals(expected3, actual3);
	}
	
	public void testParseMapNull() {
		String testStringNull1 = "";
		String testStringNull2 = "testing but no list, oops";
		String testStringNull3 = "tstdtsdt {";
		String testStringNull4 = "tstdtsdt }";

		assertNull(CommandModel.parseMap(testStringNull1));
		assertNull(CommandModel.parseMap(testStringNull2));
		assertNull(CommandModel.parseMap(testStringNull3));
		assertNull(CommandModel.parseMap(testStringNull4));
	}
	
	public void testParseMapTrue() {
		String testStringTrue1 = "SVR GAME <speler resultaat> {PLAYERONESCORE: \"<score speler1>\", PLAYERTWOSCORE: \"<score speler2>\", COMMENT: \"<commentaar op resultaat>\"}";
		String testStringTrue2 = "SVR GAME <speler resultaat> {PLAYERONESCORE:\"<score speler1>\",PLAYERTWOSCORE:\"<score speler2>\",COMMENT:\"<commentaar op resultaat>\"}";

		String[] actualFirst1 = new String[]{"PLAYERONESCORE", "PLAYERTWOSCORE", "COMMENT"};
		String[] actualFirst2 = new String[]{"PLAYERONESCORE", "PLAYERTWOSCORE", "COMMENT"};
		
		String[] actualSecond1 = new String[]{"<score speler1>", "<score speler2>", "<commentaar op resultaat>"};
		String[] actualSecond2 = new String[]{"<score speler1>", "<score speler2>", "<commentaar op resultaat>"};
		
		HashMap<String, String> map1 = CommandModel.parseMap(testStringTrue1);
		HashMap<String, String> map2 = CommandModel.parseMap(testStringTrue2);

		for (int i = 0; i < actualFirst1.length; i++) {
			assertTrue(map1.containsKey(actualFirst1[i]));
			assertTrue(map1.containsValue(actualSecond1[i]));
			
			assertTrue(map2.containsKey(actualFirst2[i]));
			assertTrue(map2.containsValue(actualSecond2[i]));
		}
	}
}
