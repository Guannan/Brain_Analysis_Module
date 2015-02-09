package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

/**
 * Create the tab that displays the reports of imports
 * @author rachelcoston
 *
 */
public class ReportTab extends JComponent{
	
	private static final long serialVersionUID = 1L;
	private DatabaseAdminModel myModel;
	private JPanel content = new JPanel();
	private List<JCheckBox> reportIDList;
	private final JFrame parent;
	
	/**
	 * Create the report view
	 * @param model
	 */
	public ReportTab(final JFrame parent, 
			DatabaseAdminModel model) {
		super();
		this.myModel = model;
		this.parent = parent;
		
		JPanel center = new JPanel();
		center.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		JLabel defaultLabel = new JLabel("Select the report ID you wish to look at: ");
		JButton showReport = new JButton("Show Report");
		JPanel buttonPane = new JPanel();
		JPanel bottom = new JPanel();

		reportIDList = new ArrayList<JCheckBox>();
		List<Integer> reportIds = model.getReportId();
		for (Integer reportId : reportIds) {
			String ID = reportId.toString();
			JCheckBox tempCheckBox = new JCheckBox("Report : " + ID);
			reportIDList.add(tempCheckBox);
		}
		for (int i = 0; i < reportIDList.size(); i++) {
			buttonPane.add(reportIDList.get(i));
		}

		bottom.add(showReport);
		center.add(buttonPane, BorderLayout.CENTER);
		center.add(defaultLabel, BorderLayout.NORTH);
		add(center, BorderLayout.CENTER);
		add(bottom, BorderLayout.SOUTH);
		
		showReport.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < reportIDList.size(); i++) {
					if (reportIDList.get(i).isSelected()){
						String checkboxText = reportIDList.get(i).getText();
						int index = checkboxText.indexOf(": ");
						int reportId = Integer.parseInt(checkboxText.substring(index+2, checkboxText.length()));   
						addParentDialog(reportId);
						return;
					}
				}
			}
		});
	}
	public void addParentDialog(int reportId) {
		String processType = myModel.getReportProcessType(reportId);
		String processedTypeImagePath = myModel.getReportProcessedImagePath(reportId);
		Date importedOn = myModel.getReportImportedOn(reportId);
		int subjectID = myModel.getReportSubjectId(reportId);
		
		ShowReport reportDialog = new ShowReport(parent, myModel, this, subjectID, 
				importedOn, processType, processedTypeImagePath);
		reportDialog.setVisible(true);
	}
	
//	public void generateReportView(int subjectID, Date importedOn, 
//			String processType, String processedImagePath) {
//			
//			String reportText = "Corresponding Images and Test Results:\n" +
//				"Subject ID\n" +
//				"\t"+ Integer.toString(subjectID)+ "\n" +
//				"Data Imported On:\n" +
//				"\t"+ importedOn.toString()+"\n" +	
//				"Image Processing:\n" +
//				"\t"+ processType + "\n" +
//				"Processed Image File Path:\n" + 
//				"\t"+ processedImagePath + "\n";
//		
//		this.content.removeAll();;
//		this.content.setLayout(new BorderLayout());
//		setLayout(new BorderLayout());
//		JTextArea textArea = new JTextArea();
//		textArea.append(reportText);
//		JScrollPane scrollPane = new JScrollPane(textArea); 
//		textArea.setEditable(true);
//		this.content.add(scrollPane, BorderLayout.CENTER);
//		add(this.content, BorderLayout.CENTER);
//	}

}
