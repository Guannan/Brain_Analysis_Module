package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.apache.commons.io.FilenameUtils;
import org.apache.struts.upload.FormFile;

import com.jogamp.gluegen.cgram.types.Type;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

/**
 * Create a new tab that allows the database administrator to input new image,
 * test results, and demographics.
 * 
 * @author rachelcoston
 *
 */
public class InputDataTab extends JComponent {

	private static final long serialVersionUID = 1L;
	private JTextField imagePath;
	private JButton imageBrowse;
	private JLabel lbImages;

	private JTextField testPath;
	private JButton testBrowse;
	private JLabel lbTestResults;
	//
	// private JTextField demoPath;
	// private JButton demoBrowse;
	// private JLabel lbDemographics;

	private JButton btnUpdate;

	/**
	 * Create the tab to input new data into the database
	 * 
	 * @param parent
	 *            The JFrame
	 * @param model
	 *            The model
	 */
	public InputDataTab(final DatabaseAdminFrame parent,
			final DatabaseAdminModel model) {
		super();

		lbImages = new JLabel("Input Images: ");
		imagePath = new JTextField(30);
		imageBrowse = new JButton("Browse");
		final JFileChooser imageFile = new JFileChooser();

		lbTestResults = new JLabel("Input Test Results: ");
		testPath = new JTextField(30);
		testBrowse = new JButton("Browse");
		final JFileChooser testFile = new JFileChooser();

		// lbDemographics = new JLabel("Input Demographics: ");
		// demoPath = new JTextField(30);
		// demoBrowse = new JButton("Browse");
		// final JFileChooser demoFile = new JFileChooser();

		btnUpdate = new JButton("\tUpdate Database\t");

		JPanel p3 = new JPanel(new GridLayout(3, 1));
		p3.add(lbImages);
		p3.add(lbTestResults);
		// p3.add(lbDemographics);

		JPanel p4 = new JPanel(new GridLayout(3, 1));
		p4.add(imagePath);
		p4.add(testPath);
		// p4.add(demoPath);

		JPanel p6 = new JPanel(new GridLayout(3, 1));
		p6.add(imageBrowse);
		p6.add(testBrowse);
		// p6.add(demoBrowse);

		JPanel p1 = new JPanel();
		p1.add(p3);
		p1.add(p4);
		p1.add(p6);

		JPanel p2 = new JPanel();
		p2.add(btnUpdate);

		JPanel p5 = new JPanel(new BorderLayout());
		p5.add(p2, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(p1, BorderLayout.CENTER);
		add(p5, BorderLayout.SOUTH);

		// Add action listener to bring up a file chooser when the browse button
		// is pressed. Set the path field to match the chosen file.
		imageBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = imageFile.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = imageFile.getSelectedFile();
					imagePath.setText(file.getAbsolutePath());

				}
			}

		});

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (model.inputImageDataValid(getSubjectID(), getDate(),
						getImageType(), getImagePath())) {
					JOptionPane.showMessageDialog(parent,
							"FilePath Successfully Inserteded", "OK",
							JOptionPane.INFORMATION_MESSAGE);
					// reset username and password
					imagePath.setText("");
				} else {
					JOptionPane.showMessageDialog(parent, "ImagePath:"
							+ getImagePath() + " is not available", "OK",
							JOptionPane.ERROR_MESSAGE);

					imagePath.setText("");
				}
			}
		});

		// Add action listener to bring up a file chooser when the browse button
		// is pressed. Set the path field to match the chosen file.
		testBrowse.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int returnVal = testFile.showOpenDialog(null);

				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = testFile.getSelectedFile();
					testPath.setText(file.getAbsolutePath());
				}
			}

		});

		btnUpdate.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (testPath.getText().trim().isEmpty()) {

					if (model.inputImageDataValid(getSubjectID(), getDate(),
							getImageType(), getImagePath())) {
						JOptionPane.showMessageDialog(parent,
								"FilePath Successfully Inserteded", "OK",
								JOptionPane.INFORMATION_MESSAGE);
						// reset username and password
						imagePath.setText("");
					} else {
						JOptionPane.showMessageDialog(parent, "ImagePath:"
								+ getImagePath() + " is not available", "OK",
								JOptionPane.ERROR_MESSAGE);

						imagePath.setText("");
					}
				}
				if (imagePath.getText().trim().isEmpty()) {
					if (model.readTestData(testPath.getText().trim())) {
						JOptionPane.showMessageDialog(parent,
								"Test Result's Path Successfully Inserteded",
								"OK", JOptionPane.INFORMATION_MESSAGE);
						// reset username and password
						imagePath.setText("");
					} else {
						JOptionPane.showMessageDialog(parent,
								"Test Result's Path:" + getImagePath()
										+ " is not available", "OK",
								JOptionPane.ERROR_MESSAGE);

						imagePath.setText("");
					}
				} else {
					JOptionPane.showMessageDialog(parent, "Path:"
							+ getImagePath() + " is not available", "OK",
							JOptionPane.ERROR_MESSAGE);

					imagePath.setText("");
				}
			}
		});
		/*
		 * // Add action listener to bring up a file chooser when the browse
		 * button // is pressed. Set the path field to match the chosen file.
		 * demoBrowse.addActionListener(new ActionListener() {
		 * 
		 * @Override public void actionPerformed(ActionEvent e) { int returnVal
		 * = demoFile.showOpenDialog(null);
		 * 
		 * if (returnVal == JFileChooser.APPROVE_OPTION) { File file =
		 * demoFile.getSelectedFile(); demoPath.setText(file.getAbsolutePath());
		 * } }
		 * 
		 * });
		 */
	}

	public String getSubjectID() {
		String fullPath = imagePath.getText().trim();
		Path path = Paths.get(fullPath);
		String fileName = path.getFileName().toString();
		List<String> list = new ArrayList<String>(Arrays.asList(fileName
				.split("_")));
		String subjectID = list.get(0);
		return subjectID;
	}

	public Date getDate() {
		String fullPath = imagePath.getText().trim();
		Path path = Paths.get(fullPath);
		String fileName = path.getFileName().toString();
		List<String> list = new ArrayList<String>(Arrays.asList(fileName
				.split("_")));
		String dateString = list.get(2);
		String dateString2 = dateString.substring(0, 4) + "-"
				+ dateString.substring(4, 6) + "-" + dateString.substring(6, 8);
		Date date = Date.valueOf(dateString2);
		return date;
	}

	public String getImageType() {
		String fullPath = imagePath.getText().trim();
		Path path = Paths.get(fullPath);
		String fileName = path.getFileName().toString();
		List<String> list = new ArrayList<String>(Arrays.asList(fileName
				.split("_")));
		String type = list.get(3);
		int index = type.indexOf(".");
		String imageType = type.substring(0, index);
		return imageType;
	}

	public String getImagePath() {
		String rawImagePath = imagePath.getText().trim();
		// String rawImagePath = FilenameUtils.removeExtension(fullPath);
		// int index = fullPath.indexOf(".");
		// String rawImagePath = fullPath.substring(0, index);
		return rawImagePath;
	}

}
