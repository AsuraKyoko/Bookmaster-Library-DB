import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTextField;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import dbConnections.*;


@SuppressWarnings("serial")
public class UserInfoPanel extends JPanel implements ActionListener{
	private JTextField userSearchField;
	@SuppressWarnings("rawtypes")
	private JComboBox searchFieldComboBox;
	private JButton btnSearch;
	private JScrollPane scrollPane;
	private JPanel resultPanel;

	/**
	 * Create the panel.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public UserInfoPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{167, 116, 0, 0};
		gbl_panel.rowHeights = new int[]{22, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		userSearchField = new JTextField();
		GridBagConstraints gbc_userSearchField = new GridBagConstraints();
		gbc_userSearchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_userSearchField.insets = new Insets(0, 0, 0, 5);
		gbc_userSearchField.anchor = GridBagConstraints.NORTH;
		gbc_userSearchField.gridx = 0;
		gbc_userSearchField.gridy = 0;
		panel.add(userSearchField, gbc_userSearchField);
		userSearchField.setColumns(10);
		
		searchFieldComboBox = new JComboBox();
		searchFieldComboBox.setModel(new DefaultComboBoxModel(new String[] {"ID", "Name", "Level", "Overdue"}));
		GridBagConstraints gbc_searchFieldComboBox = new GridBagConstraints();
		gbc_searchFieldComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_searchFieldComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchFieldComboBox.gridx = 1;
		gbc_searchFieldComboBox.gridy = 0;
		panel.add(searchFieldComboBox, gbc_searchFieldComboBox);
		searchFieldComboBox.addActionListener(this);
		
		btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 0;
		panel.add(btnSearch, gbc_btnSearch);
		btnSearch.addActionListener(this);
		
		scrollPane = new JScrollPane();
		add(scrollPane, BorderLayout.CENTER);
		
		resultPanel = new JPanel();
		scrollPane.setViewportView(resultPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == searchFieldComboBox)
		{
			switch(searchFieldComboBox.getSelectedIndex())
			{
			case 3: userSearchField.setEditable(false); break;
			case 0:
			case 1:
			case 2:
			default: userSearchField.setEditable(true);
			}
		}
		else if(e.getSource() == btnSearch)
		{			
			ArrayList<User> users;
			switch(searchFieldComboBox.getSelectedIndex())
			{
			case 0: users = Database.queryUsers(userSearchField.getText(), "id"); break;
			case 1: users = Database.queryUsers(userSearchField.getText(), "name"); break;
			case 2: users = Database.queryUsers(userSearchField.getText(), "level"); break;
			case 3: users = Database.queryOverdue(); break;
			default: users = new ArrayList<User>(); break;
			}
			
			resultPanel = new UserResultsPanel(users);

			scrollPane.setViewportView(resultPanel);
			this.validate();
		}
		
	}

}
