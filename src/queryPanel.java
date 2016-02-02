import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JScrollPane;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JButton;


public class queryPanel extends JPanel implements ActionListener{
	private JTextArea resultArea;
	private JButton btnExecuteQuery;

	/**
	 * Create the panel.
	 */
	public queryPanel() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JLabel lblMysqlStatement = new JLabel("MySQL Statement");
		GridBagConstraints gbc_lblMysqlStatement = new GridBagConstraints();
		gbc_lblMysqlStatement.anchor = GridBagConstraints.WEST;
		gbc_lblMysqlStatement.insets = new Insets(10, 10, 5, 5);
		gbc_lblMysqlStatement.gridx = 0;
		gbc_lblMysqlStatement.gridy = 0;
		add(lblMysqlStatement, gbc_lblMysqlStatement);
		
		JButton btnClearQuery = new JButton("Clear Query");
		GridBagConstraints gbc_btnClearQuery = new GridBagConstraints();
		gbc_btnClearQuery.insets = new Insets(10, 10, 5, 5);
		gbc_btnClearQuery.gridx = 1;
		gbc_btnClearQuery.gridy = 0;
		add(btnClearQuery, gbc_btnClearQuery);
		
		btnExecuteQuery = new JButton("Execute Query");
		GridBagConstraints gbc_btnExecuteQuery = new GridBagConstraints();
		gbc_btnExecuteQuery.anchor = GridBagConstraints.WEST;
		gbc_btnExecuteQuery.insets = new Insets(10, 5, 5, 0);
		gbc_btnExecuteQuery.gridx = 2;
		gbc_btnExecuteQuery.gridy = 0;
		add(btnExecuteQuery, gbc_btnExecuteQuery);
		btnExecuteQuery.addActionListener(this);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane_1 = new GridBagConstraints();
		gbc_scrollPane_1.gridwidth = 3;
		gbc_scrollPane_1.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane_1.fill = GridBagConstraints.BOTH;
		gbc_scrollPane_1.gridx = 0;
		gbc_scrollPane_1.gridy = 1;
		add(scrollPane_1, gbc_scrollPane_1);
		
		JTextArea textArea = new JTextArea();
		textArea.setWrapStyleWord(true);
		textArea.setTabSize(4);
		textArea.setLineWrap(true);
		scrollPane_1.setViewportView(textArea);
		
		JLabel lblNewLabel = new JLabel("Results");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel.insets = new Insets(0, 10, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 2;
		add(lblNewLabel, gbc_lblNewLabel);
		
		JButton btnClearResults = new JButton("Clear Results");
		GridBagConstraints gbc_btnClearResults = new GridBagConstraints();
		gbc_btnClearResults.anchor = GridBagConstraints.WEST;
		gbc_btnClearResults.insets = new Insets(0, 10, 5, 5);
		gbc_btnClearResults.gridx = 1;
		gbc_btnClearResults.gridy = 2;
		add(btnClearResults, gbc_btnClearResults);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.gridwidth = 3;
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 3;
		add(scrollPane, gbc_scrollPane);
		
		resultArea = new JTextArea();
		resultArea.setWrapStyleWord(true);
		resultArea.setEditable(false);
		resultArea.setLineWrap(true);
		scrollPane.setViewportView(resultArea);

	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource()==btnExecuteQuery)
		{
			
		}
		
	}

}
