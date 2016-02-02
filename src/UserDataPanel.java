import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;

import dbConnections.Database;
import dbConnections.User;


public class UserDataPanel extends JPanel implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nameField;
	private JTextField idField;
	private JSpinner levelSpinner;
	private JTextField bookOutField;
	private JTextField bonusBookField;
	private JTextField dueDateField;
	private JButton btnUpdate;
	
	private User user;

	/**
	 * Create the panel.
	 */
	public UserDataPanel(User user)
	{
		this.user = user;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblName = new JLabel("Name");
		GridBagConstraints gbc_lblName = new GridBagConstraints();
		gbc_lblName.insets = new Insets(10, 10, 5, 5);
		gbc_lblName.anchor = GridBagConstraints.EAST;
		gbc_lblName.gridx = 0;
		gbc_lblName.gridy = 0;
		add(lblName, gbc_lblName);
		
		nameField = new JTextField();
		GridBagConstraints gbc_nameField = new GridBagConstraints();
		gbc_nameField.gridwidth = 2;
		gbc_nameField.insets = new Insets(10, 0, 5, 5);
		gbc_nameField.fill = GridBagConstraints.HORIZONTAL;
		gbc_nameField.gridx = 1;
		gbc_nameField.gridy = 0;
		add(nameField, gbc_nameField);
		nameField.setText(user.getName());
		
		JLabel lblBookOut = new JLabel("Book Out");
		GridBagConstraints gbc_lblBookOut = new GridBagConstraints();
		gbc_lblBookOut.anchor = GridBagConstraints.EAST;
		gbc_lblBookOut.insets = new Insets(0, 5, 5, 5);
		gbc_lblBookOut.gridx = 3;
		gbc_lblBookOut.gridy = 0;
		add(lblBookOut, gbc_lblBookOut);
		
		bookOutField = new JTextField();
		GridBagConstraints gbc_bookOutField = new GridBagConstraints();
		gbc_bookOutField.insets = new Insets(0, 0, 5, 10);
		gbc_bookOutField.fill = GridBagConstraints.HORIZONTAL;
		gbc_bookOutField.gridx = 4;
		gbc_bookOutField.gridy = 0;
		add(bookOutField, gbc_bookOutField);
		
		JLabel lblId = new JLabel("ID");
		GridBagConstraints gbc_lblId = new GridBagConstraints();
		gbc_lblId.anchor = GridBagConstraints.EAST;
		gbc_lblId.insets = new Insets(0, 0, 5, 5);
		gbc_lblId.gridx = 0;
		gbc_lblId.gridy = 1;
		add(lblId, gbc_lblId);
		
		idField = new JTextField();
		GridBagConstraints gbc_idField = new GridBagConstraints();
		gbc_idField.gridwidth = 2;
		gbc_idField.insets = new Insets(0, 0, 5, 5);
		gbc_idField.fill = GridBagConstraints.HORIZONTAL;
		gbc_idField.gridx = 1;
		gbc_idField.gridy = 1;
		add(idField, gbc_idField);
		idField.setText(user.getUserId());
		
		JLabel lblBonusBook = new JLabel("Bonus Book");
		GridBagConstraints gbc_lblBonusBook = new GridBagConstraints();
		gbc_lblBonusBook.anchor = GridBagConstraints.EAST;
		gbc_lblBonusBook.insets = new Insets(0, 5, 5, 5);
		gbc_lblBonusBook.gridx = 3;
		gbc_lblBonusBook.gridy = 1;
		add(lblBonusBook, gbc_lblBonusBook);
		
		bonusBookField = new JTextField();
		GridBagConstraints gbc_bonusBookField = new GridBagConstraints();
		gbc_bonusBookField.insets = new Insets(0, 0, 5, 10);
		gbc_bonusBookField.fill = GridBagConstraints.HORIZONTAL;
		gbc_bonusBookField.gridx = 4;
		gbc_bonusBookField.gridy = 1;
		add(bonusBookField, gbc_bonusBookField);
		
		JLabel lblDateDue = new JLabel("Check Out Date");
		GridBagConstraints gbc_lblDateDue = new GridBagConstraints();
		gbc_lblDateDue.insets = new Insets(0, 10, 5, 5);
		gbc_lblDateDue.gridx = 0;
		gbc_lblDateDue.gridy = 2;
		add(lblDateDue, gbc_lblDateDue);
		
		dueDateField = new JTextField();
		GridBagConstraints gbc_dueDateSpinner = new GridBagConstraints();
		gbc_dueDateSpinner.gridwidth = 2;
		gbc_dueDateSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_dueDateSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_dueDateSpinner.gridx = 1;
		gbc_dueDateSpinner.gridy = 2;
		add(dueDateField, gbc_dueDateSpinner);
		dueDateField.setText(user.getCheckedOutString().replace("'", ""));
		
		JLabel lblLevel = new JLabel("Level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.anchor = GridBagConstraints.EAST;
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 3;
		gbc_lblLevel.gridy = 2;
		add(lblLevel, gbc_lblLevel);
		
		levelSpinner = new JSpinner();
		levelSpinner.setModel(new SpinnerNumberModel(1, 1, 9001, 1));
		GridBagConstraints gbc_levelSpinner = new GridBagConstraints();
		gbc_levelSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_levelSpinner.anchor = GridBagConstraints.WEST;
		gbc_levelSpinner.gridx = 4;
		gbc_levelSpinner.gridy = 2;
		add(levelSpinner, gbc_levelSpinner);
		levelSpinner.setValue(user.getLevel());
		
		btnUpdate = new JButton("Update");
		GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
		gbc_btnUpdate.gridx = 4;
		gbc_btnUpdate.gridy = 3;
		add(btnUpdate, gbc_btnUpdate);
		btnUpdate.addActionListener(this);
		

		if(user.getBonusBook() != null)
			bonusBookField.setText(user.getBonusBook().getBarcode());

		if(user.getBookOut() != null)
			bookOutField.setText(user.getBookOut().getBarcode());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnUpdate)
		{
			user = new User(idField.getText(), nameField.getText(), (Integer)levelSpinner.getValue(), bookOutField.getText(), bonusBookField.getText(), dueDateField.getText());
			Database.updateUser(user);
		}
	}
}
