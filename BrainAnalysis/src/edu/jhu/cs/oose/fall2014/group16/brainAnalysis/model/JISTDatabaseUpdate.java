package edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Allows JIST users to query the database.  Proxy for database manager
 **/
public class JISTDatabaseUpdate {
	private static DatabaseManager manager;

    /**
     * Creates a new query object with the current database manager
     */
	public JISTDatabaseUpdate() {
		manager = DatabaseManager.getDatabaseManager();
		manager.initializeDatabase("OOSE", "Group16");
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
     * Update database with given data
     */
	public boolean updateDatabase(String password, String processedData, Integer rawImageID, String processType) {
			
		if (checkPassword(password)) {
			ProcessedImage processedImage = new ProcessedImage(processedData, rawImageID, processType);
			boolean out = manager.databaseInsert(processedImage);
			return out;
		}
		else {return false;}

	}

}			
