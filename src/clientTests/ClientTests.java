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
				 * reference 
				 */
				
//				model.className1, model.className2, model.className3 
		};
		
		org.junit.runner.JUnitCore.main(testClasses);
	}

}
