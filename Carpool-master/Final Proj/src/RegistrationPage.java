import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class RegistrationPage extends JPanel {

	final static int FIELD_WIDTH = 10;

	private JLabel title, nameLabel, accountIDLabel, emailCheck, passwordLabel, idCheck;
	private JTextField nameField, accountIDField, emailField;
	private JPasswordField passwordField;
	private JPanel namePanel, accountIDPanel, emailPanel, passwordPanel, btnPanel;
	private JButton submitbtn, backtoLoginbtn;
	private ExecSQL exec;
	private GridBagLayout gridBagLayout;

	public RegistrationPage() {
		exec = new ExecSQL();
		gridBagLayout = new GridBagLayout();
		setLayout(gridBagLayout);

		createComp();
	}

	public void createComp() {
		// title
		Font f1 = new Font("Helvetica", Font.BOLD, 28);
		title = new JLabel("Registration");
		title.setFont(f1);
		GridBagConstraints c1 = new GridBagConstraints();
		c1.fill = GridBagConstraints.HORIZONTAL;
		c1.gridx = 0;
		c1.gridy = 0;
		c1.gridwidth = 3;
		c1.gridheight = 1;
		c1.anchor = GridBagConstraints.CENTER;
		add(title, c1);

		// Name
		nameLabel = new JLabel("Name");
		nameField = new JTextField(FIELD_WIDTH);
		namePanel = new JPanel();
		namePanel.add(nameLabel);
		namePanel.add(nameField);
		GridBagConstraints c2 = new GridBagConstraints();
		c2.fill = GridBagConstraints.HORIZONTAL;
		c2.gridx = 0;
		c2.gridy = 1;
		c2.gridwidth = 3;
		c2.gridheight = 1;
		c2.anchor = GridBagConstraints.CENTER;
		add(namePanel, c2);

		/*
		 * //Email emailLabel = new JLabel("Email"); emailField = new
		 * JTextField(FIELD_WIDTH); emailPanel = new JPanel();
		 * emailPanel.add(emailLabel); emailPanel.add(emailField); add(emailPanel);
		 * 
		 * //email check Font f2 = new Font("Helvetica", Font.PLAIN, 8); emailCheck =
		 * new JLabel(""); emailCheck.setFont(f2); emailCheck.setForeground(Color.red);
		 * add(emailCheck);
		 */

		// AccountID
		accountIDLabel = new JLabel("Account");
		accountIDField = new JTextField(FIELD_WIDTH);
		accountIDPanel = new JPanel();
		accountIDPanel.add(accountIDLabel);
		accountIDPanel.add(accountIDField);
		GridBagConstraints c3 = new GridBagConstraints();
		c3.fill = GridBagConstraints.HORIZONTAL;
		c3.gridx = 0;
		c3.gridy = 2;
		c3.gridwidth = 3;
		c3.gridheight = 1;
		c3.anchor = GridBagConstraints.CENTER;
		add(accountIDPanel, c3);

		// AccountID check
		Font f3 = new Font("Helvetica", Font.PLAIN, 8);
		idCheck = new JLabel("");
		idCheck.setFont(f3);
		idCheck.setForeground(Color.red);
		GridBagConstraints c4 = new GridBagConstraints();
		c4.fill = GridBagConstraints.HORIZONTAL;
		c4.gridx = 0;
		c4.gridy = 3;
		c4.gridwidth = 3;
		c4.gridheight = 1;
		c4.anchor = GridBagConstraints.CENTER;
		add(idCheck, c4);

		// Password
		passwordLabel = new JLabel("Password");
		passwordField = new JPasswordField(FIELD_WIDTH);
		passwordPanel = new JPanel();
		passwordPanel.add(passwordLabel);
		passwordPanel.add(passwordField);
		GridBagConstraints c5 = new GridBagConstraints();
		c5.fill = GridBagConstraints.HORIZONTAL;
		c5.gridx = 0;
		c5.gridy = 4;
		c5.gridwidth = 3;
		c5.gridheight = 1;
		c5.anchor = GridBagConstraints.CENTER;
		add(passwordPanel, c5);

		// btms
		submitbtn = new JButton("Submit");
		backtoLoginbtn = new JButton("Back to log in");
		submitbtn();
		backtoLoginbtn();
		// backtoLoginbtn.setOpaque(false);
		backtoLoginbtn.setBorderPainted(false);
		backtoLoginbtn.setContentAreaFilled(false);
		backtoLoginbtn.setForeground(Color.blue);
		btnPanel = new JPanel();
		btnPanel.add(submitbtn);
		btnPanel.add(backtoLoginbtn);
		GridBagConstraints c6 = new GridBagConstraints();
		c6.fill = GridBagConstraints.HORIZONTAL;
		c6.gridx = 0;
		c6.gridy = 5;
		c6.gridwidth = 3;
		c6.gridheight = 1;
		c6.anchor = GridBagConstraints.CENTER;
		add(btnPanel, c6);
	}

	// submitbtm
	// if no error (if create account successfully, set text in the result area and
	// return back to the log in panel after 3 seconds

	public void submitbtn() {
		class clickListener implements ActionListener {
			public void actionPerformed(ActionEvent e) {
				try {
					if (exec.createAccount(accountIDField.getText(), nameField.getText(),
							passwordField.getPassword())) {

						JOptionPane.showMessageDialog(null,
								"Your account is successfully created \n You will be redirected to the login page");
						Viewer.cardLayout.show(Viewer.switchPanel, "1");

					} else {
						boolean result = exec.checkAccountID(accountIDField.getText());
						if (result == false) {
							idCheck.setText("The Account ID is taken");
						} else {
							JOptionPane.showMessageDialog(null, "An error occurred");
						}
					}
				} catch (SQLException e1) {
					e1.printStackTrace();

				}
			}
		}
		ActionListener listener = new clickListener();
		submitbtn.addActionListener(listener);
	}

	public void backtoLoginbtn() {
		class clickListener implements ActionListener {
			public void actionPerformed(ActionEvent e1) {
				Viewer.cardLayout.show(Viewer.switchPanel, "1");
			}
		}
		ActionListener listener = new clickListener();
		backtoLoginbtn.addActionListener(listener);
	}

	/*
	 * public boolean checkEmailFormat() { if (isValid(emailField.getText())) {
	 * return true; }else {
	 * 
	 * emailCheck.setText("Please enter a correct email address"); return false; } }
	 * 
	 * public boolean isValid(String email) { String emailRegex =
	 * "^[a-zA-Z0-9_+&*-]+(?:\\."+ "[a-zA-Z0-9_+&*-]+)*@" +
	 * "(?:[a-zA-Z0-9-]+\\.)+[a-z" + "A-Z]{2,7}$";
	 * 
	 * Pattern pat = Pattern.compile(emailRegex); if (email == null) return false;
	 * return pat.matcher(email).matches(); }
	 * 
	 * public MailSender javaMailService() { JavaMailSenderImpl javaMailSender = new
	 * JavaMailSenderImpl(); javaMailSender.setHost(“smtp.gmail.com”);
	 * javaMailSender.setPort(587); javaMailSender.setProtocol(“smtp”);
	 * javaMailSender.setUsername(“sender’s email”);
	 * javaMailSender.setPassword(“sender’s password”); Properties mailProperties =
	 * new Properties(); mailProperties.put(“mail.smtp.auth”, “true”);
	 * mailProperties.put(“mail.smtp.starttls.enable”, “true”);
	 * mailProperties.put(“mail.smtp.debug”, “true”);
	 * javaMailSender.setJavaMailProperties(mailProperties); return javaMailSender;
	 * }
	 */

}
