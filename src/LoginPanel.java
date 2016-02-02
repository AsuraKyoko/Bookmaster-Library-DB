import java.awt.Component;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.eclipse.wb.swing.FocusTraversalOnArray;

import dbConnections.Database;
import dbConnections.User;


@SuppressWarnings("serial")
public class LoginPanel extends JPanel implements ActionListener
{
	private JTextField textField;
	public JButton btnLogin;
	
	private BackingPanel backing;
	/**
	 * Create the panel.
	 */
	public LoginPanel(BackingPanel backing)
	{
		this.backing = backing;
		
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblNewLabel = new JLabel("Scan your ID card");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.SOUTH;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 0);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.anchor = GridBagConstraints.SOUTH;
		gbc_textField.insets = new Insets(0, 0, 5, 0);
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		add(textField, gbc_textField);
		textField.setColumns(20);
		
		btnLogin = new JButton("Login");
		GridBagConstraints gbc_btnLogin = new GridBagConstraints();
		gbc_btnLogin.anchor = GridBagConstraints.NORTH;
		gbc_btnLogin.gridx = 0;
		gbc_btnLogin.gridy = 2;
		add(btnLogin, gbc_btnLogin);
		btnLogin.addActionListener(this);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textField, btnLogin}));
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{		
		User user = Database.queryUser(textField.getText());
		
		boolean locked = Database.isLocked();
		
		if(user == null)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong!\n\rPlease ask for help");
			return;
		}
		
		if(user.getLevel()==9001)
		{
			backing.goToAdmin(user);
		}
		else if(locked)
		{
			JOptionPane.showMessageDialog(null, "Library is Locked!\n\rPlease ask for help");
		}
		else
		{
			backing.goToHome(user);
		}
	}

}
