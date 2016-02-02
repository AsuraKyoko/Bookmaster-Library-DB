import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.JPanel;
import dbConnections.*;

public class UserResultsPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public UserResultsPanel(ArrayList<User> users)
	{
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		if(users != null)
		{
			for(int i = 0; i < users.size(); i++)
			{
				JPanel panel = new UserDataPanel(users.get(i));
				GridBagConstraints gbc_panel = new GridBagConstraints();
				gbc_panel.fill = GridBagConstraints.BOTH;
				gbc_panel.gridx = 0;
				gbc_panel.gridy = i;
				add(panel, gbc_panel);
			}
		}
	}

}
