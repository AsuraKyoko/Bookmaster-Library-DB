import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTabbedPane;
import javax.swing.ScrollPaneConstants;

import dbConnections.Database;
import dbConnections.User;


@SuppressWarnings("serial")
public class AdminPanel extends JPanel implements ActionListener
{
	private JMenuItem mntmLogOut;
	private JMenuItem mntmExitProgram;
	
	private BackingPanel backing;
	private User user;
	private JCheckBoxMenuItem chckbxmntmLibraryLocked;
	private JMenuItem mntmExitAndLock;

	/**
	 * Create the panel.
	 */
	public AdminPanel(BackingPanel backing, User user) {
		this.backing = backing;
		this.user = user;
		setLayout(new BorderLayout(0, 0));
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		scrollPane.setViewportView(tabbedPane);
		
		JPanel bookQueryPanel = new LookupBookPanel();
		tabbedPane.addTab("Search/Edit Books", null, bookQueryPanel, null);
		
		JPanel editUserPanel = new UserInfoPanel();
		tabbedPane.addTab("Edit User", null, editUserPanel, null);
		
		JMenuBar menuBar = new JMenuBar();
		add(menuBar, BorderLayout.NORTH);
		
		JMenu mnOptions = new JMenu("Options");
		menuBar.add(mnOptions);
		
		chckbxmntmLibraryLocked = new JCheckBoxMenuItem("Library Locked");
		chckbxmntmLibraryLocked.setSelected(Database.isLocked());
		chckbxmntmLibraryLocked.setText(Database.isLocked()?"Library Locked":"Library Unlocked");
		mnOptions.add(chckbxmntmLibraryLocked);
		chckbxmntmLibraryLocked.addActionListener(this);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenu mnExit = new JMenu("Exit");
		menuBar.add(mnExit);
		
		mntmLogOut = new JMenuItem("Log Out");
		mnExit.add(mntmLogOut);
		mntmLogOut.addActionListener(this);
		
		JSeparator separator = new JSeparator();
		mnExit.add(separator);
		
		mntmExitProgram = new JMenuItem("Exit Program");
		mnExit.add(mntmExitProgram);
		
		mntmExitAndLock = new JMenuItem("Exit and Lock Library");
		mnExit.add(mntmExitAndLock);
		mntmExitProgram.addActionListener(this);
		mntmExitAndLock.addActionListener(this);

	}
	
	/**
	 * verify if the logged in user really is the user
	 * @param id - the scanned ID of the Admin
	 * @return if the admin ID matches the logged in user
	 */
	public boolean verify(String id)
	{
		return user.getUserId().equals(id);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==mntmExitProgram)
		{
			System.exit(0);
		}
		else if(e.getSource()==mntmLogOut)
		{
			backing.goToLogin();
		}
		else if(e.getSource() == chckbxmntmLibraryLocked)
		{
			if(chckbxmntmLibraryLocked.isSelected())
			{
				chckbxmntmLibraryLocked.setText("Library Locked");
				Database.setLocked(true);
			}
			else
			{
				chckbxmntmLibraryLocked.setText("Library Unlocked");
				Database.setLocked(false);
			}
			validate();
		}
		else if(e.getSource() == mntmExitAndLock)
		{
			Database.setLocked(true);
			System.exit(0);
		}
		
	}

}
