package dbConnections;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class ResultArray
{
	private ArrayList<ArrayList<String>> rows;
	private ArrayList<String> columns;

	public ArrayList<String> getRow(int row) {
		return rows.get(row);
	}

	public ResultArray(ResultSet r)
	{
		rows = new ArrayList<ArrayList<String>>();
		columns = new ArrayList<String>();
		try
		{
			int cols = r.getMetaData().getColumnCount();
			
			for(int j = 0; j < cols; j++)
			{
				columns.add(r.getMetaData().getColumnLabel(j));
			}
			
			do
			{
				ArrayList<String> s = new ArrayList<String>();
				
				r.next();
				
				for(int i = 0; i < cols; i++)
				{
					s.add(r.getString(i));
				}
				rows.add(s);
				
			}while(!r.isLast());
			
		}
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String[][] toArray()
	{
		String[][] s = new String[rows.size()][columns.size()];
		
		for(int i = 0; i < rows.size(); i++)
		{
			s[i] = (String[])rows.get(i).toArray();
		}
		return s;
	}
}
