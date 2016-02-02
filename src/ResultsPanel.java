import java.util.ArrayList;

import javax.swing.JPanel;

import dbConnections.Book;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;


public class ResultsPanel extends JPanel
{
	private LookupBookPanel lookupBookPanel;
	/**
	 * Create the panel.
	 */
	public ResultsPanel(ArrayList<Book> books, LookupBookPanel lookupBookPanel)
	{
		this.lookupBookPanel = lookupBookPanel;
		
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		if(books != null)
		{
			for(int i = 0; i < books.size(); i++)
			{
				JPanel panel = new BookInfoPanel(books.get(i), false);
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = i;
				add(panel, gbc_panel);
			}
		}
		else
		{
			JPanel panel = new BookInfoPanel(lookupBookPanel.getNewBook(), true);
			GridBagConstraints gbc_panel = new GridBagConstraints();
			gbc_panel.fill = GridBagConstraints.BOTH;
			gbc_panel.gridx = 0;
			gbc_panel.gridy = 0;
			add(panel, gbc_panel);
		}
	}

}
