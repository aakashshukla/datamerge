package com.mariner.datamergeandsort;

import java.io.File;

import org.junit.Test;

import junit.framework.TestCase;

/**
 * Unit test for DataMergeAndSort
 */
public class DataMergeAndSortControllerTest extends TestCase {

	/**
	 * Unit test for successfully asserting the required result
	 */
    public void testMainSuccess() {
    	try {
			DataMergeAndSortController.main(null);
			File file = new File(this.getClass().getClassLoader().getResource("results.csv").getFile());
			assertTrue(file.exists());
		} catch (Exception e) {
			e.printStackTrace();
		} 
    }
    
    /**
	 * Unit test for null pointer generation
	 */
    @Test(expected=NullPointerException.class)
    public void testMainFailure() {
    	try {
			DataMergeAndSortController.main(null);
			new File(this.getClass().getClassLoader().getResource("results1.csv").getFile());
		} catch (Exception e) {
			e.printStackTrace();
		}  
    }
}
