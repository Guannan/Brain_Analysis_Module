package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.sql.Date;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allows JIST users to query the database. Proxy for database manager
 **/
public class JISTDatabaseQuery {
	private static DatabaseManager manager = DatabaseManager
			.getDatabaseManager();

	/**
	 * Creates a new query object with the current database manager
	 */
	public JISTDatabaseQuery() {
		manager.initializeDatabase("OOSE", "2014");
	}

	/**
	 * Checks if the database password is valid
	 */
	public boolean checkPassword(String password) {
		User user = manager.getUserByPassword(password);
		if (user == null) {
			return false;
		}
		return true;
	}

	/**
	 * Queries database with given data
	 */
	public List<ImageDataOOSE> queryDatabase(String password,
			Map<String, String> subjectData, Map<String, String> imageData) {
		List<ImageDataOOSE> images = new ArrayList<ImageDataOOSE>();
		// List<TestResults> results = new ArrayList<TestResults>();
		if (checkPassword(password)) {

			System.out.println("Password Accepted");

			boolean hasSubjectData = false;
			if (subjectData != null && !subjectData.isEmpty()) {
				hasSubjectData = true;
			}
			List<Subject> subjects = null;


				try {
					subjects = getSubjects(subjectData);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if (imageData != null && !imageData.isEmpty()) {
				if (subjects == null || (subjects.size() <= 0 && !hasSubjectData)) {
					System.out.println("Found subject");
					for (Map.Entry<String, String> entry : imageData.entrySet()) {
						List<ImageDataOOSE> iData = manager.getImageDataByType(
								entry.getKey(), entry.getValue());
						for (ImageDataOOSE data : iData) {
							images.add(data);
						}
					}
				} else if (subjects.size() > 0 || hasSubjectData) {
					for (Subject subject : subjects) {
						List<ImageDataOOSE> iData = manager
								.getImageDataFromSubjectID(String
										.valueOf(subject.getID()));
						for (ImageDataOOSE data : iData) {
							images.add(data);
						}
					}
				}
			} else {
				List<ImageDataOOSE> iData = manager.getImageData();
				if (subjects == null || (subjects.size() <= 0 && !hasSubjectData)) {
					iData = manager.getImageData();
					for (ImageDataOOSE data : iData) {
						images.add(data);
					}
				} else if (subjects.size() > 0 || hasSubjectData) {
					for (Subject subject : subjects) {
						iData = manager.getImageDataFromSubjectID(String
								.valueOf(subject.getID()));
						for (ImageDataOOSE data : iData) {
							images.add(data);
						}
					}
				}

			}

			System.out.println("Found " + images.size() + " results");
			return images;
		}
		System.out.println("Password is invalid");
		return null;

	}

	/**
	 * Get subjects based on JIST parameters
	 * 
	 * @throws ParseException
	 */
	private List<Subject> getSubjects(Map<String, String> subjectData)
			throws ParseException {
		if (subjectData == null) {
			return null;
		}

		List<Subject> subjects = null;
		if (subjectData != null && !subjectData.isEmpty()) {
			Map<String, List<Date>> between = new HashMap<String, List<Date>>();
			if (subjectData.containsKey("birthDateMin")) {
				if (subjectData.containsKey("birthDateMax")) {

					List<Date> range = new ArrayList<Date>();
					range.add(java.sql.Date.valueOf(subjectData.get("birthDateMin")));
					range.add(java.sql.Date.valueOf(subjectData.get("birthDateMax")));
					between.put("dateOfBirth", range);
					
					subjectData.remove("birthDateMax");
				}
				subjectData.remove("birthDateMin");
			}
			subjects = manager.querySubjects(subjectData, between);
		}

		if (subjects == null) {
			System.out.println("Found 0 subjects");
			return new ArrayList<Subject>();
		}
		System.out.println("Found " + subjects.size() + " subjects");
		return subjects;
	}
}
