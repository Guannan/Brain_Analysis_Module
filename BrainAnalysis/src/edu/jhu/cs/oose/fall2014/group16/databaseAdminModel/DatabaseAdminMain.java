package edu.jhu.cs.oose.fall2014.group16.databaseAdminModel;

import java.awt.Dimension;

import javax.swing.JFrame;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI.DatabaseAdminFrame;

/**
 * Starts the database administrators GUI
 * @author rachelcoston
 *
 */
public class DatabaseAdminMain {

	/**
	 * Start a new GUI session
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		DatabaseAdminModel model; 
        model = new DatabaseAdminModel();
        DatabaseAdminFrame gui = new DatabaseAdminFrame(model);
        gui.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        gui.setSize(new Dimension(900, 300));
	}

}
