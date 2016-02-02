import javax.swing.JOptionPane;
import javax.swing.JPanel;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import javax.swing.JButton;
import java.awt.Insets;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JTextField;
import org.eclipse.wb.swing.FocusTraversalOnArray;

import dbConnections.Book;
import dbConnections.Database;
import dbConnections.User;

import java.awt.Component;


@SuppressWarnings("serial")
public class HomePanel extends JPanel implements ActionListener
{
	
	private BackingPanel backing;
	private JTextField entryField;
	public JButton btnGo;
	private User user;

	/**
	 * Create the panel.
	 */
	public HomePanel(BackingPanel backing, User user) {
		this.backing = backing;
		this.user = user;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, 1.0, 0.0, 1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel_1 = new JPanel();
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.gridwidth = 3;
		gbc_panel_1.insets = new Insets(0, 0, 5, 5);
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{0, 0, 0, 0};
		gbl_panel_1.rowHeights = new int[]{0, 0, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		if(user.getBookOut()!=null || user.getBonusBook()!=null)
		{
			
			JPanel panel_4 = new JPanel();
			GridBagConstraints gbc_panel_4 = new GridBagConstraints();
			gbc_panel_4.insets = new Insets(0, 0, 0, 5);
			gbc_panel_4.fill = GridBagConstraints.BOTH;
			gbc_panel_4.gridx = 0;
			gbc_panel_4.gridy = 1;
			panel_1.add(panel_4, gbc_panel_4);
			

			JLabel lblYouAlreadyHave = new JLabel("You already have " + user.getBookOut().getTitle() + " checked out");
			lblYouAlreadyHave.setFont(new Font("Tahoma", Font.BOLD, 16));
			GridBagConstraints gbc_lblYouAlreadyHave = new GridBagConstraints();
			gbc_lblYouAlreadyHave.insets = new Insets(0, 0, 0, 5);
			gbc_lblYouAlreadyHave.gridx = 1;
			gbc_lblYouAlreadyHave.gridy = 1;
			panel_1.add(lblYouAlreadyHave, gbc_lblYouAlreadyHave);
	
		
			JPanel panel_5 = new JPanel();
			GridBagConstraints gbc_panel_5 = new GridBagConstraints();
			gbc_panel_5.fill = GridBagConstraints.BOTH;
			gbc_panel_5.gridx = 2;
			gbc_panel_5.gridy = 1;
			panel_1.add(panel_5, gbc_panel_5);
		}
		
		JPanel panel_2 = new JPanel();
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.gridheight = 5;
		gbc_panel_2.insets = new Insets(0, 0, 0, 5);
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 0;
		gbc_panel_2.gridy = 0;
		add(panel_2, gbc_panel_2);
		
		JPanel panel_3 = new JPanel();
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.gridheight = 5;
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 4;
		gbc_panel_3.gridy = 0;
		add(panel_3, gbc_panel_3);
		
		JLabel lblScanYourBook = new JLabel("Scan a Book");
		lblScanYourBook.setFont(new Font("Tahoma", Font.BOLD, 16));
		GridBagConstraints gbc_lblScanYourBook = new GridBagConstraints();
		gbc_lblScanYourBook.gridwidth = 3;
		gbc_lblScanYourBook.insets = new Insets(0, 0, 5, 5);
		gbc_lblScanYourBook.gridx = 1;
		gbc_lblScanYourBook.gridy = 1;
		add(lblScanYourBook, gbc_lblScanYourBook);
		
		entryField = new JTextField();
		GridBagConstraints gbc_entryField = new GridBagConstraints();
		gbc_entryField.gridwidth = 3;
		gbc_entryField.insets = new Insets(0, 0, 5, 5);
		gbc_entryField.fill = GridBagConstraints.HORIZONTAL;
		gbc_entryField.gridx = 1;
		gbc_entryField.gridy = 2;
		add(entryField, gbc_entryField);
		entryField.setColumns(20);
		
		btnGo = new JButton("Go!");
		GridBagConstraints gbc_btnGo = new GridBagConstraints();
		gbc_btnGo.insets = new Insets(0, 0, 5, 5);
		gbc_btnGo.gridx = 2;
		gbc_btnGo.gridy = 3;
		add(btnGo, gbc_btnGo);
		btnGo.addActionListener(this);
		
		JPanel panel = new JPanel();
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.insets = new Insets(0, 0, 0, 5);
		gbc_panel.gridwidth = 3;
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.gridx = 1;
		gbc_panel.gridy = 4;
		add(panel, gbc_panel);
		setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{entryField}));
		
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnGo)
		{
			Book book = Database.queryBook(entryField.getText());
			if(book == null)
			{
				JOptionPane.showMessageDialog(null, "Please scan the barcode on the cover of the book. \n If you get this message again, please ask for help!");
				entryField.setText("");
			}			
			else if(user.getBookOut() == null)
			{
				user.checkOutBook(book);
				JOptionPane.showMessageDialog(null, book.getTitle() + " checked out!");
				Database.updateBook(book);

				backing.goToLogin();
			}
			else
			{
				try
				{
					if(entryField.getText().matches(user.getBookOut().getBarcode()) || entryField.getText().matches(user.getBonusBook().getBarcode()))
					{
						user.checkInBook(book);
						JOptionPane.showMessageDialog(null, book.getTitle() + " checked in!");
					
						backing.goToLogin();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "Please check in " + user.getBookOut().getTitle() + " before checking out another book");
					}
				}
				catch(NullPointerException x)
				{
					JOptionPane.showMessageDialog(null, "Please check in any books you have before checking out another book");
				}
			}
		}
	}

}
