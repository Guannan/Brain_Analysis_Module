package edu.jhu.cs.oose.fall2014.group16.databaseAdminModel;

import java.sql.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.DatabaseManager;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.GenerateTestResult;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ImageDataOOSE;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ParseText;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.RawImage;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.Report;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.TestResults;
import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.User;

/**
 * The model for the database administrator's GUI
 * @author rachelcoston/Guannan
 *
 */
public class DatabaseAdminModel {
	
	private static DatabaseManager manager = DatabaseManager.getDatabaseManager();
	private Set<ProcessType> processTypes;
//	private List<Data> imageData;
	
	public DatabaseAdminModel() throws Exception {
		manager.initializeDatabase("OOSE", "2014");
		this.processTypes = new HashSet<ProcessType>();
//		this.imageData = new ArrayList<Data>();
		
		Report r1 = new Report();
		r1.setImportedOn(Date.valueOf("1991-09-03"));
		String processedImagePath = "image1.img";
		r1.setIndependentProcessType("Denoise");
		r1.setIndependentProcessedImagePath(processedImagePath);
		r1.setSubjectId(1);
		manager.databaseInsert(r1);
		
		Report r2 = new Report();
		r2.setImportedOn(Date.valueOf("1991-09-04"));
		processedImagePath = "image2.img";
		r2.setIndependentProcessType("Blend");
		r2.setIndependentProcessedImagePath(processedImagePath);
		r2.setSubjectId(2);
		manager.databaseInsert(r2);
		
//		this.readTestData();
//		this.populateTestNames();
		
//		System.out.println("Working Directory = " +
//	              System.getProperty("user.dir"));
		
//		for (String testName : testInfo) {
//			System.out.println(testName);
//		}
		
	}
	
	/**
	 * Determine if a inputFile is available
	 * @param string the rawImagePath
	 * @return true if the rawImagePath is available, false otherwise
	 */
	public boolean inputFileValid(String rawImagePath) {
		// TODO Auto-generated method stub
		RawImage rawImage = new RawImage(rawImagePath);
		if (manager.databaseInsert(rawImage)) {
			return true;
		}
		else {
			return false;
		}
	}
	
	/**
	 * Determine if a inputFile is available
	 * @param string the rawImagePath
	 * @return true if the rawImagePath is available, false otherwise
	 */
	public boolean inputImageDataValid(String subjectID, Date scanDate, String imageType, String imagePath) {
		// TODO Auto-generated method stub
		ImageDataOOSE imageData = new ImageDataOOSE(subjectID, scanDate, imageType, imagePath);
		if (manager.databaseInsert(imageData)) {
			return true;
		}
		else {
			return false;
		}

	}
	
	/**
	 * Authenticate the user
	 * @param username the username
	 * @param password the password
	 * @return true if the database administrator is valid, false otherwise
	 */
	public boolean authenticate(String username, String password) {
        // hardcoded username and password
        if (username.equals("user") && password.equals("password")) {
            return true;
        }
        return false;
    }

	/**
	 * Determine if a username is available
	 * @param string the username
	 * @return true if the username is available, false otherwise
	 */
	public boolean userValid(String username, String password) {
		// TODO Auto-generated method stub
		User user = new User(username, password);
		if (manager.databaseInsert(user)) {
			return true;
		}
		else {
			return false;
		}

	}

	/**
	 * Determine if a data admin's username is available
	 * @param string the username
	 * @return true if the username is available, false otherwise
	 */
	public boolean adminValid(String username, String password) {
		// TODO Auto-generated method stub
//		manager.initializeDatabase(username, password);
		return true;
	}
	
	/**
	 * Imports a list of data and generates a report from the data
	 * @param data List of data, such as images 
	 * @return A report with the information gathered from the data list
	 */
//	public Report generateReport(List<Data> data) {
//		return null;
//	}
	
	public List<Integer> getReportId() {
		return manager.getReportID();
	}
	
	public int getReportSubjectId(int reportId) {
		return manager.getReportSubjectID(reportId);
	}
	
	public String getReportProcessType(int reportId) {
		return manager.getIndependentProcessType(reportId);
	}
	
	public String getReportProcessedImagePath(int reportId) {
		return manager.getIndependentProcessedImagePath(reportId);
	}
	
	public Date getReportImportedOn(int reportId) {
		return manager.getImportedOn(reportId);
	}
	
	/**
	 * Specify a list of default image processing types for data processing
	 * @param types A list of data processing types
	 */
	public boolean updateDefaultProcessing(Set<String> selectedProcessTypes, Set<String> deselectedProcessTypes) {
		for (String processName : selectedProcessTypes) {
			for (ProcessType pt : this.processTypes) {
				if (pt.getName().equals(processName)){
					ProcessType updatedPT = new ProcessType(pt);
					updatedPT.setDefaultProcessing(true);
					manager.databaseUpdate(pt, updatedPT);
//					pt.setDefaultProcessing(true);
					break;
				}
			}
		}
		
		for (String processName : deselectedProcessTypes) {
			for (ProcessType pt : this.processTypes) {
				if (pt.getName().equals(processName)) {
					ProcessType updatedPT = new ProcessType(pt);
					updatedPT.setDefaultProcessing(false);
					manager.databaseUpdate(pt, updatedPT);
//					pt.setDefaultProcessing(false);
					break;
				}
			}
		}
		if (selectedProcessTypes.size() + deselectedProcessTypes.size() > 0) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean addProcessType(String processName, String processPath){
		for (ProcessType pt : this.processTypes){
			if (pt.getName().equals(processName)){
				return false;
			}
		}
		
		manager.databaseInsert(new ProcessType(processName, processPath));
		return true;
	}
	
	public Set<ProcessType> getProcessTypes() {
		this.processTypes = new HashSet<ProcessType>(manager.getProcessType());
		return processTypes;
	}
	
	public boolean readTestData (String filename) {
		String test_data_file = filename;
		
		List<TestResults> testResults = ParseText.readPatientResult(test_data_file);
		
		boolean insertSuccess = false;
		for (TestResults tr : testResults) {
//			System.out.println(tr.getSubjectID());
//			System.out.println(tr.getTestName());
//			System.out.println(tr.getTakenOn());
//			TestResults tr1 = new TestResults();
//			tr1.setTestName(tr.getTestName());
//			tr1.setTestResult(tr.getTestResult());
//			tr1.setSubjectID(tr.getSubjectID());
//			tr1.setTakenOn(tr.getTakenOn());
			if (manager.databaseInsert(tr)){
				insertSuccess = true;
			}
		}
		
		return insertSuccess;
	}
	
	public void populateTestNames() {
		
		String patient_file = "patient_data.txt";
		String test_file = "tests.txt";
		
		Map<String, List<String>> patientInfo = ParseText.readPatient(patient_file);
		List<String> testInfo = ParseText.readTests(test_file);
		
		for (String key : patientInfo.keySet()) {
			List<String> testDates = patientInfo.get(key);
			
			for (String dateString : testDates) {
				String dateString2 = dateString.substring(0, 4) + "-" + 
						dateString.substring(4, 6) + "-" + dateString.substring(6, 8);
				TestResults tr = new TestResults();
//				System.out.println(dateString2);
//				tr.setTakenOn(Date.valueOf(dateString2));
				tr.setTestName(GenerateTestResult.getTestName((int)System.currentTimeMillis()));
				tr.setTestResult(GenerateTestResult.getResult((int)System.currentTimeMillis()));
				manager.databaseInsert(tr);
			}
		}
	    
	//		for (String date : patientInfo.get("AT1002")) {
	//		System.out.println(date);
	//	}
		
//		for (String testName : testInfo) {
//			System.out.println(testName);
//		}
		
	}

}

