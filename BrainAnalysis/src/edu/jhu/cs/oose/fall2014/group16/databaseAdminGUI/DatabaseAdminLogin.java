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
import javax.swing.JPasswordField;
import javax.swing.JTextField;


import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

/**
 * Creates the dialog that forces a user to login before seeing any content
 * @author rachelcoston
 *
 */
public class DatabaseAdminLogin extends JDialog {

	private static final long serialVersionUID = 1L;
	
	private JTextField username;
    private JPasswordField password;
    private JLabel lbUsername;
    private JLabel lbPassword;
    private JButton btnLogin;
    private JButton btnCancel;
    
    
    /**
     * Create the login dialog
     * @param parent The parent
     * @param model The model
     */
    public DatabaseAdminLogin(final Frame parent, final DatabaseAdminModel model) {
        super(parent, "JIST Database Management System Login", true);
 
        lbUsername = new JLabel("Username: ");
 
        username = new JTextField(20);
 
        lbPassword = new JLabel("Password: ");
 
        password = new JPasswordField(20);
 
        btnLogin = new JButton("Login");
        
        btnCancel = new JButton("Cancel");
        
        JPanel p3 = new JPanel(new GridLayout(2, 1));
        p3.add(lbUsername);
        p3.add(lbPassword);

        JPanel p4 = new JPanel(new GridLayout(2, 1));
        p4.add(username);
        p4.add(password);

        JPanel p1 = new JPanel();
        p1.add(p3);
        p1.add(p4);

        JPanel p2 = new JPanel();
        p2.add(btnLogin);
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
 
        //Verify the user's login or display error
        btnLogin.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
                if (model.authenticate(getUsername(), getPassword())) {
                	parent.setVisible(true);
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(DatabaseAdminLogin.this,
                            "Invalid username or password",
                            "Login",
                            JOptionPane.ERROR_MESSAGE);
                    // reset username and password
                    username.setText("");
                    password.setText("");
 
                }
            }
        });
        
        //If cancel is press the application closes
        btnCancel.addActionListener(new ActionListener() {
 
            public void actionPerformed(ActionEvent e) {
            	setVisible(false);
            	parent.dispose();
                System.exit(0);  
            }
        });
        
        pack();
        setResizable(false);
        setLocationRelativeTo(parent);
        
    }
 
    /**
     * Get the username in the field
     * @return the text in the field
     */
    public String getUsername() {
        return username.getText().trim();
    }
 
    /**
     * Get the password in the field
     * @return the text in the field
     */
    public String getPassword() {
        return new String(password.getPassword());
    }

}


