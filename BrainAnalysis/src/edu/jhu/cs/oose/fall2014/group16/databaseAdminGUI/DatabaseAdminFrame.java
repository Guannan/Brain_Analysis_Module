package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

/**
 * Creates the database administrator's main frame
 * @author rachelcoston
 *
 */
public class DatabaseAdminFrame extends JFrame {

	private DatabaseAdminModel model;

	/**
	 * Create an application with the given model
	 * @param model The model
	 */
	public DatabaseAdminFrame(DatabaseAdminModel model) {
		super("JIST Database Management System");
		this.model = model;
//		model.populateTestNames();

		// Begin Menu Bar
		final JMenuItem exit = new JMenuItem("Exit");
		final JMenu file = new JMenu("File");
		file.add(exit);

		final JMenuBar menuBar = new JMenuBar();
		menuBar.add(file);

		this.setJMenuBar(menuBar);
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				System.exit(DISPOSE_ON_CLOSE);
			}

		});
		
		//End Menu Bar
		
		

		final JPanel contentPane = new JPanel(new BorderLayout());
		
		//Start login
		DatabaseAdminLogin login = new DatabaseAdminLogin(this, model);
		login.setVisible(true);
		//End login
		
		//Start Content
		
		JComponent inputData = new InputDataTab(this, model);
		JComponent imagePross = new ImageProcessingTab(this, model);
		JComponent report = new ReportTab(this, model);
		JComponent newAdmin = new AddDatabaseAdminTab(this, model);
		JComponent newUser = new AddUserTab(this, model);
		
		
		JTabbedPane tabbedPane = new JTabbedPane();
		
		tabbedPane.addTab("Input Data", inputData);
		tabbedPane.addTab("Default Image Processing", imagePross);
		tabbedPane.addTab("Reports", report);
		tabbedPane.addTab("Register New User", newUser);

		//End Content
		
		contentPane.add(tabbedPane);
		contentPane.setOpaque(true);
		contentPane.setPreferredSize(new Dimension(300, 300));
		this.setContentPane(contentPane);

	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

}
