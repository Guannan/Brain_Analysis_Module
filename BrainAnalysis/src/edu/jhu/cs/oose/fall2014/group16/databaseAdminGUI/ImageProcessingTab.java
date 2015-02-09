package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import edu.jhu.cs.oose.fall2014.group16.brainAnalysis.model.ProcessType;
import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

/**
 * Crate the tab to allow the database administrator to define the default image
 * processing
 * 
 * @author rachelcoston/Guannan
 *
 */
public class ImageProcessingTab extends JComponent {

	private static final long serialVersionUID = 1L;
	private final DatabaseAdminModel myModel;
	private Set<ProcessType> imageProcessingTypes;
	private List<JCheckBox> processNameCheckboxes;
	private JPanel checkboxPane;
	private final JFrame parent;
	/**
	 * Create tab for image processing
	 * 
	 * @param model
	 */
	public ImageProcessingTab(final JFrame parent,
			final DatabaseAdminModel model) {
		super();

		this.myModel = model;
		this.parent = parent;
		imageProcessingTypes = model.getProcessTypes();
		processNameCheckboxes = new ArrayList<JCheckBox>();

		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		JLabel defaultLabel = new JLabel("Set Default Image Processing Types: ");
		JButton addProcessing = new JButton("New Processing");
		checkboxPane = new JPanel();
		JButton saveSelection = new JButton("Save");
		JButton clearSelection = new JButton("Clear All");
		JPanel bottom = new JPanel();

		for (ProcessType pt : imageProcessingTypes) {
			String processName = pt.getName();
			JCheckBox tempBox = new JCheckBox(processName);
			if (pt.isDefaultProcessing()) {
				tempBox.setSelected(true);
			}
			processNameCheckboxes.add(tempBox);
		}
		for (int i = 0; i < imageProcessingTypes.size(); i++) {
			checkboxPane.add(processNameCheckboxes.get(i));
		}

		bottom.add(addProcessing);
		bottom.add(saveSelection);
		bottom.add(clearSelection);
		center.add(checkboxPane, BorderLayout.CENTER);
		center.add(defaultLabel, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);

		addProcessing.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				addParentDialog();
			}
		});

		// Saves the list of selected/unselected default processing types
		saveSelection.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Set<String> selectedProcessType = new HashSet<String>();
				Set<String> deselectedProcessType = new HashSet<String>();

				for (int k = 0; k < processNameCheckboxes.size(); k++) {
					if (processNameCheckboxes.get(k).isSelected()) {
						selectedProcessType.add(processNameCheckboxes.get(k)
								.getText());
					}
					else {
						deselectedProcessType.add(processNameCheckboxes.get(k)
								.getText());
					}
				}
				if (model.updateDefaultProcessing(selectedProcessType, deselectedProcessType)) {
					JOptionPane.showMessageDialog(parent,
							"Default Processing Types " + "Successfully Set",
							"OK", JOptionPane.INFORMATION_MESSAGE);
					try {
						ImageProcessingTab.this.outputTextFile("test.txt", selectedProcessType);	
					} catch (Exception exception) {
						System.exit(0);
					}
				} else {
					JOptionPane.showMessageDialog(parent,
							"Failed to Set Default Processing Types", "OK",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});

		// If the cancel button is pressed uncheck the fields
		clearSelection.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int j = 0; j < processNameCheckboxes.size(); j++) {
					processNameCheckboxes.get(j).setSelected(false);
				}
			}
		});
	}

	public void addParentDialog() {
		AddProcessType ptDialog = new AddProcessType(parent, myModel, this);
		ptDialog.setVisible(true);
	}
	 
	public boolean addProcess(String name, String path) {
		boolean addSuccess = this.myModel.addProcessType(name, path);
		if (addSuccess){
			this.imageProcessingTypes = this.myModel.getProcessTypes();
			this.processNameCheckboxes = new ArrayList<JCheckBox>();
			this.checkboxPane.removeAll();
			
			for (ProcessType pt : imageProcessingTypes) {
				String processName = pt.getName();
				JCheckBox tempBox = new JCheckBox(processName);
				
				if (pt.isDefaultProcessing()) {
					tempBox.setSelected(true);
				}
				
				processNameCheckboxes.add(tempBox);
				
			}
			
			for (int i = 0; i < imageProcessingTypes.size(); i++) {
				checkboxPane.add(processNameCheckboxes.get(i));
			}
			validate(); 	
		}

		return addSuccess;
		
	}
	
	public void outputTextFile(String filename, Set<String> selectedProcessType) 
			throws FileNotFoundException, UnsupportedEncodingException {
		PrintWriter writer = new PrintWriter(filename, "UTF-8");
		
		writer.println("List of Default Process Types:");
		for (String pt : selectedProcessType) {
			writer.println(pt);
		}
		writer.close();
	}



}
