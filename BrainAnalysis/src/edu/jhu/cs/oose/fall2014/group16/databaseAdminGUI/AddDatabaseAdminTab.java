package edu.jhu.cs.oose.fall2014.group16.databaseAdminGUI;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import edu.jhu.cs.oose.fall2014.group16.databaseAdminModel.DatabaseAdminModel;

/**
 * Creates a tab that allows a current database administrator to add a new
 * database administrator
 * 
 * @author rachelcoston
 *
 */
public class AddDatabaseAdminTab extends JComponent {

	private static final long serialVersionUID = 1L;

	private JTextField username;
	private JPasswordField password;
	private JLabel lbUsername;
	private JLabel lbPassword;
	private JButton btnAdd;
	private JButton btnCancel;

	/**
	 * Create a new tab to add database administrators
	 * 
	 * @param parent
	 *            The JFrame
	 * @param model
	 *            The model
	 */
	public AddDatabaseAdminTab(final JFrame parent,
			final DatabaseAdminModel model) {
		super();
		lbUsername = new JLabel(
				"Enter the database administrator's new username: ");

		username = new JTextField(20);

		lbPassword = new JLabel(
				"Enter the database administrator's new password: ");

		password = new JPasswordField(20);

		btnAdd = new JButton("Add User");

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
		p2.add(btnAdd);
		p2.add(btnCancel);

		JPanel p5 = new JPanel(new BorderLayout());
		p5.add(p2, BorderLayout.CENTER);

		setLayout(new BorderLayout());
		add(p1, BorderLayout.CENTER);
		add(p5, BorderLayout.SOUTH);

		// Bring up error if username is taken, otherwise report successful
		// registration
		btnAdd.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				if (model.adminValid(getUsername(), getPassword())) {
					JOptionPane.showMessageDialog(parent,
							"User Successfully Created", "User Created",
							JOptionPane.INFORMATION_MESSAGE);
					// reset username and password
					username.setText("");
					password.setText("");
				} else {
					JOptionPane.showMessageDialog(parent, "The username "
							+ getUsername() + " is not available",
							"Registration Error", JOptionPane.ERROR_MESSAGE);

					username.setText("");
				}
			}
		});
		//If the cancel button is pressed clear the fields
		btnCancel.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				username.setText("");
				password.setText("");
			}
		});
	}

	/**
	 * Get the text in the username field
	 * @return the text in the field
	 */
	public String getUsername() {
		return username.getText().trim();
	}

	/**
	 * Get the text in the password field
	 * @return the text in the field
	 */
	public String getPassword() {
		return new String(password.getPassword());
	}

}
