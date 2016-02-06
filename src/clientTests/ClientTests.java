package clientTests;

import static org.junit.Assert.*;

import org.junit.Test;

public class ClientTests {

	@Test
	public void test() {
		assertEquals("OK", "OK");
		assertTrue(true);
		assertFalse(false);
	}
	
	public static void main(String[] args){
		String[] testClasses = new String[]{
				/*
				 * reference junits here...
				 */
				
				"clientTests.PlayerTest",
				"clientTests.ClientModelTests",
				"clientTests.GameMapTest" ,
				"clientTests.ClientCanDoTests",
				"clientTests.ClientFacadeTest",
				"clientTests.ServerProxyTests"
		};
		
		org.junit.runner.JUnitCore.main(testClasses);
	}

}
