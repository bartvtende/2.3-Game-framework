package test.java.org.hanzet23.gameframework;

import main.java.org.hanzet23.gameframework.models.Command;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.junit.Assert.*;

/**
 * Unit test for the Network class
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

		assertNull(Command.parseList(testStringNull1));
		assertNull(Command.parseList(testStringNull2));
		assertNull(Command.parseList(testStringNull3));
		assertNull(Command.parseList(testStringNull4));
	}
	
	public void testParseListTrue() {
		String testStringTrue1 = "testing test [\"test1\", \"test2\"]";
		String testStringTrue2 = "testing test [test1, test2]";
		String testStringTrue3 = "testing test [test1,test2]";
		String testStringTrue4 = "testing test [test1 , test2]";
		
		String[] actual1 = Command.parseList(testStringTrue1);
		String[] actual2 = Command.parseList(testStringTrue2);
		String[] actual3 = Command.parseList(testStringTrue3);
		String[] actual4 = Command.parseList(testStringTrue4);
		
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
}
