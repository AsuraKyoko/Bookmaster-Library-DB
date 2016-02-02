import javax.swing.JPanel;
import java.awt.GridBagLayout;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import dbConnections.Book;
import dbConnections.Database;


public class LookupBookPanel extends JPanel implements ActionListener {
	private JTextField searchField;
	private JButton btnSearch;
	private JComboBox parameterComboBox;
	private JPanel resultsPanel;
	private JScrollPane scrollPane;

	/**
	 * Create the panel.
	 */
	public LookupBookPanel() {
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		add(panel, BorderLayout.NORTH);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 35, 0};
		gbl_panel.rowHeights = new int[]{16, 0};
		gbl_panel.columnWeights = new double[]{1.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		searchField = new JTextField();
		GridBagConstraints gbc_searchField = new GridBagConstraints();
		gbc_searchField.insets = new Insets(0, 0, 0, 5);
		gbc_searchField.fill = GridBagConstraints.HORIZONTAL;
		gbc_searchField.gridx = 0;
		gbc_searchField.gridy = 0;
		panel.add(searchField, gbc_searchField);
		searchField.setColumns(10);
		
		parameterComboBox = new JComboBox();
		parameterComboBox.setModel(new DefaultComboBoxModel(new String[] {"ISBN", "Title", "Author", "Series"}));
		GridBagConstraints gbc_parameterComboBox = new GridBagConstraints();
		gbc_parameterComboBox.insets = new Insets(0, 0, 0, 5);
		gbc_parameterComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_parameterComboBox.gridx = 1;
		gbc_parameterComboBox.gridy = 0;
		panel.add(parameterComboBox, gbc_parameterComboBox);
		
		btnSearch = new JButton("Search");
		GridBagConstraints gbc_btnSearch = new GridBagConstraints();
		gbc_btnSearch.gridx = 2;
		gbc_btnSearch.gridy = 0;
		panel.add(btnSearch, gbc_btnSearch);
		btnSearch.addActionListener(this);
		
		scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		add(scrollPane, BorderLayout.CENTER);
		
		resultsPanel = new JPanel();
		scrollPane.setViewportView(resultsPanel);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnSearch)
		{
			String parameter;
			switch(parameterComboBox.getSelectedIndex())
			{
			case 1: parameter = "title"; break;
			case 2: parameter = "author"; break;
			case 3: parameter = "series"; break; 
			default: parameter = "barcode"; break;
			}
			
			resultsPanel = new ResultsPanel(Database.queryBooks(searchField.getText(), parameter), this);

			scrollPane.setViewportView(resultsPanel);
			this.validate();
		}
		
	}

	public Book getNewBook()
	{
		Book b;
		
		switch(parameterComboBox.getSelectedIndex())
		{
		case 1: b = new Book("", searchField.getText(), "", "", 0, 0); break;
		case 2: b = new Book("", "", searchField.getText(), "", 0, 0); break;
		case 3: b = new Book("", "", "", searchField.getText(), 0, 0); break; 
		default: b = new Book(searchField.getText(), "", "", "", 0, 0); break;
		}
		
		return b;
	}
}
