import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


@SuppressWarnings("serial")
public class BookEnterer extends JFrame implements ActionListener
{

	private JPanel contentPane;
	private JTextField isbnField;
	private JTextField titleField;
	private JTextField authorField;
	private JTextField keywordsField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookEnterer frame = new BookEnterer();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookEnterer() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblIsbn = new JLabel("ISBN");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.anchor = GridBagConstraints.EAST;
		gbc_lblIsbn.insets = new Insets(0, 0, 5, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 0;
		panel.add(lblIsbn, gbc_lblIsbn);
		
		isbnField = new JTextField();
		GridBagConstraints gbc_isbnField = new GridBagConstraints();
		gbc_isbnField.insets = new Insets(0, 0, 5, 5);
		gbc_isbnField.fill = GridBagConstraints.HORIZONTAL;
		gbc_isbnField.gridx = 1;
		gbc_isbnField.gridy = 0;
		panel.add(isbnField, gbc_isbnField);
		isbnField.setColumns(10);
		
		JButton btnCheck = new JButton("Check");
		GridBagConstraints gbc_btnCheck = new GridBagConstraints();
		gbc_btnCheck.insets = new Insets(0, 0, 5, 0);
		gbc_btnCheck.gridx = 3;
		gbc_btnCheck.gridy = 0;
		panel.add(btnCheck, gbc_btnCheck);
		
		JLabel lblTitle = new JLabel("Title");
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.anchor = GridBagConstraints.EAST;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 1;
		panel.add(lblTitle, gbc_lblTitle);
		
		titleField = new JTextField();
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.fill = GridBagConstraints.BOTH;
		gbc_titleField.insets = new Insets(0, 0, 5, 5);
		gbc_titleField.gridx = 1;
		gbc_titleField.gridy = 1;
		panel.add(titleField, gbc_titleField);
		titleField.setColumns(10);
		
		JLabel lblAuthor = new JLabel("Author");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.EAST;
		gbc_lblAuthor.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthor.gridx = 2;
		gbc_lblAuthor.gridy = 1;
		panel.add(lblAuthor, gbc_lblAuthor);
		
		authorField = new JTextField();
		GridBagConstraints gbc_authorField = new GridBagConstraints();
		gbc_authorField.insets = new Insets(0, 0, 5, 0);
		gbc_authorField.fill = GridBagConstraints.HORIZONTAL;
		gbc_authorField.gridx = 3;
		gbc_authorField.gridy = 1;
		panel.add(authorField, gbc_authorField);
		authorField.setColumns(10);
		
		JLabel lblGenre = new JLabel("Genre");
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.anchor = GridBagConstraints.EAST;
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 2;
		panel.add(lblGenre, gbc_lblGenre);
		
		JComboBox genreComboBox = new JComboBox();
		GridBagConstraints gbc_genreComboBox = new GridBagConstraints();
		gbc_genreComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_genreComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_genreComboBox.gridx = 1;
		gbc_genreComboBox.gridy = 2;
		panel.add(genreComboBox, gbc_genreComboBox);
		
		JLabel lblLevel = new JLabel("Level");
		GridBagConstraints gbc_lblLevel = new GridBagConstraints();
		gbc_lblLevel.insets = new Insets(0, 0, 5, 5);
		gbc_lblLevel.gridx = 2;
		gbc_lblLevel.gridy = 2;
		panel.add(lblLevel, gbc_lblLevel);
		
		JSpinner levelSpinner = new JSpinner();
		GridBagConstraints gbc_levelSpinner = new GridBagConstraints();
		gbc_levelSpinner.fill = GridBagConstraints.HORIZONTAL;
		gbc_levelSpinner.insets = new Insets(0, 0, 5, 0);
		gbc_levelSpinner.gridx = 3;
		gbc_levelSpinner.gridy = 2;
		panel.add(levelSpinner, gbc_levelSpinner);
		
		JLabel lblKeywords = new JLabel("Keywords");
		GridBagConstraints gbc_lblKeywords = new GridBagConstraints();
		gbc_lblKeywords.anchor = GridBagConstraints.EAST;
		gbc_lblKeywords.insets = new Insets(0, 0, 5, 5);
		gbc_lblKeywords.gridx = 0;
		gbc_lblKeywords.gridy = 3;
		panel.add(lblKeywords, gbc_lblKeywords);
		
		keywordsField = new JTextField();
		keywordsField.setToolTipText("Enter the Keywords in a comma-seperated list");
		GridBagConstraints gbc_keywordsField = new GridBagConstraints();
		gbc_keywordsField.insets = new Insets(0, 0, 5, 0);
		gbc_keywordsField.gridwidth = 3;
		gbc_keywordsField.fill = GridBagConstraints.HORIZONTAL;
		gbc_keywordsField.gridx = 1;
		gbc_keywordsField.gridy = 3;
		panel.add(keywordsField, gbc_keywordsField);
		keywordsField.setColumns(10);
		
		JLabel lblSummary = new JLabel("Summary");
		GridBagConstraints gbc_lblSummary = new GridBagConstraints();
		gbc_lblSummary.insets = new Insets(0, 0, 5, 5);
		gbc_lblSummary.gridx = 0;
		gbc_lblSummary.gridy = 4;
		panel.add(lblSummary, gbc_lblSummary);
		
		JTextArea summaryTextArea = new JTextArea();
		GridBagConstraints gbc_summaryTextArea = new GridBagConstraints();
		gbc_summaryTextArea.gridwidth = 4;
		gbc_summaryTextArea.insets = new Insets(0, 0, 5, 5);
		gbc_summaryTextArea.fill = GridBagConstraints.BOTH;
		gbc_summaryTextArea.gridx = 0;
		gbc_summaryTextArea.gridy = 5;
		panel.add(summaryTextArea, gbc_summaryTextArea);
		
		JButton submitButton = new JButton("Submit");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		GridBagConstraints gbc_submitButton = new GridBagConstraints();
		gbc_submitButton.gridx = 3;
		gbc_submitButton.gridy = 6;
		panel.add(submitButton, gbc_submitButton);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		// TODO Auto-generated method stub
		
	}

}
