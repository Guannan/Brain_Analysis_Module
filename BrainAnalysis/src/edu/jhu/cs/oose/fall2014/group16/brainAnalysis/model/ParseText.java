package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ParseText {

	public static List<TestResults> readPatientResult(String filename) {
//		Map<String, List<String>> patientID = new HashMap<String, List<String>>();
		List<TestResults> testResults = new ArrayList<TestResults>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			while((line = br.readLine()) != null) {
	//			String line = br.readLine();
				String[] ar = line.split("\\s+");
				
				TestResults tr = new TestResults();
				String strToDate = ar[1].substring(0, 4) + "-" + 
						ar[1].substring(4, 6) + "-" + ar[1].substring(6, 8);
				tr.setTakenOn(Date.valueOf(strToDate));
				tr.setTestName(ar[2]);
				tr.setTestResult(ar[3]);
				tr.setSubjectID(ar[0]);
				
				testResults.add(tr);
//				if (patientID.get(ar[0]) == null) {
//					List<String> temp = new ArrayList<String>();
//					temp.add(ar[1]);
//					patientID.put(ar[0], temp);
//				}
//				else {
//					boolean found = false;
//					for (String testDate : patientID.get(ar[0])) {
//						if (testDate.equals(ar[1])) {
//							found = true;
//							break;
//						}
//					}
//					if (!found) {
//						List<String> temp = patientID.get(ar[0]);
//						temp.add(ar[1]);
//						patientID.put(ar[0], temp);
//					}
//				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return testResults;
	}
	
	public static Map<String, List<String>> readPatient(String filename) {
		Map<String, List<String>> patientID = new HashMap<String, List<String>>();
		
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(filename));
			String line;
			while((line = br.readLine()) != null) {
	//			String line = br.readLine();
				String[] ar = line.split("\\s+");
				
				if (patientID.get(ar[0]) == null) {
					List<String> temp = new ArrayList<String>();
					temp.add(ar[1]);
					patientID.put(ar[0], temp);
				}
				else {
					boolean found = false;
					for (String testDate : patientID.get(ar[0])) {
						if (testDate.equals(ar[1])) {
							found = true;
							break;
						}
					}
					if (!found) {
						List<String> temp = patientID.get(ar[0]);
						temp.add(ar[1]);
						patientID.put(ar[0], temp);
					}
				}
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		return patientID;
	}
	
	public static List<String> readTests(String filename) {
		List<String> testNames = new ArrayList<String>();
		
		BufferedReader br;
		try {
			String line;
			br = new BufferedReader(new FileReader(filename));
			while((line = br.readLine()) != null) {
	//			String[] ar = line.split("\\s+");
	//			String testID = ar[0];
				String testName = line;
				testNames.add(testName);
			}
		} catch(Exception e) {
			System.out.println(e.toString());
		}
		
		return testNames;
	}
	
}
