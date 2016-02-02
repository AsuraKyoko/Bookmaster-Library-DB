import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;

import dbConnections.User;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class BackingPanel extends JPanel implements ActionListener
{
	private JButton btnLogOut;
	private JButton btnHome;
	private HomePanel homePanel;
	private LoginPanel loginPanel;
	private AdminPanel adminPanel;
	private JPanel navPanel;

	/**
	 * Create the panel.
	 */
	public BackingPanel() {
		setLayout(new BorderLayout(0, 0));
		
		//goToLogin();

	}
	
	public void goToLogin()
	{
		
		clear();
		loginPanel = new LoginPanel(this);
		add(loginPanel, BorderLayout.CENTER);
		this.validate();
		getRootPane().setDefaultButton(loginPanel.btnLogin);
	}
	
	public void goToAdmin(User user)
	{
		clear();
		
		if(user==null)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong!\n\rAsk for help");
			goToLogin();
		}
		
		adminPanel = new AdminPanel(this, user);
		add(adminPanel, BorderLayout.CENTER);
		
		this.validate();
		this.getRootPane().setDefaultButton(null);
	}

	private void clear() {
		try {this.remove(loginPanel);}
		catch(NullPointerException e) {}
		
		try {this.remove(navPanel);}
		catch(NullPointerException e) {}
		
		try {this.remove(homePanel);}
		catch(NullPointerException e) {}
		
		try {this.remove(adminPanel);}
		catch(NullPointerException e) {}

	}

	public void goToHome(User user)
	{
		clear();
		
		if(user==null)
		{
			JOptionPane.showMessageDialog(null, "Something went wrong!\n\rAsk for help");
			goToLogin();
		}
		
		generateNavPanel(false);
		
		homePanel = new HomePanel(this, user);
		add(homePanel, BorderLayout.CENTER);
		
		this.validate();
		this.getRootPane().setDefaultButton(homePanel.btnGo);
	}

	private void generateNavPanel(boolean homeButton)
	{
		navPanel = new JPanel();
		add(navPanel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{71, 0};
		gbl_panel.rowHeights = new int[]{23, 0};
		gbl_panel.columnWeights = new double[]{0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		navPanel.setLayout(gbl_panel);
		
		btnLogOut = new JButton("Log Out");
		btnLogOut.addActionListener(this);
		GridBagConstraints gbc_btnLogOut = new GridBagConstraints();
		gbc_btnLogOut.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnLogOut.gridx = 0;
		gbc_btnLogOut.gridy = 0;
		navPanel.add(btnLogOut, gbc_btnLogOut);
		
		if(homeButton)
		{
			btnHome = new JButton("Home");
			btnHome.addActionListener(this);
			GridBagConstraints gbc_btnHome = new GridBagConstraints();
			gbc_btnHome.anchor = GridBagConstraints.NORTHEAST;
			gbc_btnHome.gridx = 1;
			gbc_btnHome.gridy = 0;
			navPanel.add(btnHome, gbc_btnHome);
		}
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		if(e.getSource() == btnLogOut)
			goToLogin();
	}

}
