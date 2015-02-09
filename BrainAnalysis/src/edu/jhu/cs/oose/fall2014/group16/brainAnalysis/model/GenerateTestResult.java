package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.util.List;
import java.util.Random;

public class GenerateTestResult {

	/**
	 * Generates test results randomly
	 * @param seed - integer seed for random
	 * @return - String indicating test passed or failed
	 */
	public static String getResult(int seed) {
		Random rand = new Random(seed);
		double output;
		String testResult = "Passed";

//		for (int i=0; i < 100; i++) {
			output = rand.nextDouble();
			if (output > 0.5) {
				testResult = "Failed";
			}
			else {
				testResult = "Passed";
			}
			return testResult;
//			System.out.println(testResult);	
//		}			
	}
	
	/**
	 * Generates test names from a list of test names
	 * @param seed - integer seed for random
	 * @return - String test name
	 */
	public static String getTestName(int seed) {
		Random rand = new Random(seed);
//		Random rand = new Random(new Date().getTime());
		double output = rand.nextDouble();
		String testName = "";
		
		String test_file = "tests.txt";
		List<String> testNames = ParseText.readTests(test_file);
		testName = testNames.get((int) output*testNames.size());
		return testName;
	}
}
