package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

public class AddProcessType extends JDialog{

	private static final long serialVersionUID = 1L;
	
	private JTextField processName;
    private JTextField processPath;
    private JLabel lbProcessName;
    private JLabel lbProcessPath;
    private JButton btnSave;
    private JButton btnCancel;
    
    public AddProcessType(final Frame parent, final DatabaseAdminModel model, final ImageProcessingTab tab) {
        super(parent, "Add New Image Processing Type", true);
 
        lbProcessName = new JLabel("Process Name: ");
        processName = new JTextField(20);
        
        lbProcessPath = new JLabel("Process Path: ");
        processPath = new JTextField(20);

        btnSave = new JButton("Save");    
        btnCancel = new JButton("Cancel");
        
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(lbProcessName);
        p3.add(lbProcessPath);

        JPanel p4 = new JPanel(new GridLayout(2, 1));
        p4.add(processName);
        p4.add(processPath);

        JPanel p1 = new JPanel();
        p1.add(p3);
        p1.add(p4);

        JPanel p2 = new JPanel();
        p2.add(btnSave);
        p2.add(btnCancel);

        JPanel p5 = new JPanel(new BorderLayout());
        p5.add(p2, BorderLayout.CENTER);
        
        setLayout(new BorderLayout());
        add(p1, BorderLayout.CENTER);
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
 
        btnSave.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (tab.addProcess(getProcessName(), getProcessPath())){
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(AddProcessType.this,
                            "Invalid process name or process path",
                            "OK",
                            JOptionPane.ERROR_MESSAGE);
                    processName.setText("");
                    processPath.setText("");
                }
            }
        });
        
        //If cancel is press the application closes
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	dispose();
            }
        });
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        
    }
 
    /**
     * Get the processname in the field
     * @return the text in the field
     */
    public String getProcessName() {
        return processName.getText().trim();
    }
 
    /**
     * Get the process path in the field
     * @return the text in the field
     */
    public String getProcessPath() {
        return processPath.getText().trim();
    }
    
}
