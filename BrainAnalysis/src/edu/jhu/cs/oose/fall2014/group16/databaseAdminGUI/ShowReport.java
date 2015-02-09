package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

public class ShowReport extends JDialog{

	private static final long serialVersionUID = 1L;
	private JButton btnClose;
    private JPanel content;
    
    public ShowReport(final Frame parent, final DatabaseAdminModel model, final ReportTab tab,
    		final int subjectID, final Date importedOn, final String processType, final String processedImagePath) {
        super(parent, "Report View", true);

        generateReportView(subjectID, importedOn, processType, processedImagePath);

        btnClose = new JButton("Close");
        JPanel p2 = new JPanel();
        p2.add(btnClose);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        
        setLayout(new BorderLayout());
        add(this.content, BorderLayout.CENTER);
        add(p5, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        addWindowListener(new WindowAdapter() {  
            @Override
            public void windowClosing(WindowEvent e) {  
            	setVisible(false);
            	parent.dispose();
                System.exit(0);  
            }
        });
        
        //If cancel is press the application closes
        btnClose.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        
    }
 
    public void generateReportView(int subjectID, Date importedOn, 
    		String processType, String processedImagePath) {
		
		String reportText = "Corresponding Images and Test Results:\n" +
			"Subject ID\n" +
			"\t"+ Integer.toString(subjectID)+ "\n" +
			"Data Imported On:\n" +
			"\t"+ importedOn.toString()+"\n" +	
			"Image Processing:\n" +
			"\t"+ processType + "\n" +
			"Processed Image File Path:\n" + 
			"\t"+ processedImagePath + "\n";
	
        this.content = new JPanel();
		this.content.setLayout(new BorderLayout());
		setLayout(new BorderLayout());
		JTextArea textArea = new JTextArea();
		textArea.append(reportText);
		JScrollPane scrollPane = new JScrollPane(textArea); 
		textArea.setEditable(true);
		this.content.add(scrollPane, BorderLayout.CENTER);
		add(this.content, BorderLayout.CENTER);
    }
}
