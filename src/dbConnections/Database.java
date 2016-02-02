package dbConnections;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database
{
	private static String portNumber = "3306";
	private static String address = "localhost";
	private static String username = "root";
	private static String password = "root";

	private static String databaseName = "library";
	private static String libraryTable = "libraries";
	private static String userTable = "users";
	private static String bookTable = "books";
	
	public static void readSettings()
	{
		Path p = Paths.get("BookmasterSettings.bms");
		try {
			BufferedReader reader = Files.newBufferedReader(p, StandardCharsets.UTF_8);
			portNumber = reader.readLine();
			address = reader.readLine();
			username = reader.readLine();
			password = reader.readLine();
			
			databaseName = reader.readLine();
			libraryTable = reader.readLine();
			userTable = reader.readLine();
			bookTable = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void addBook(Book book)
	{
		execute("INSERT INTO " + databaseName + "." + bookTable + " (barcode, title, author, series, quantity, available) VALUES ('" + book.getBarcode() + "', '" + book.getTitle() + "', '" + book.getAuthor() + "', '" + book.getSeries() + "', " + book.getQuantity() + ", " + book.getAvailable() + ")");
	}
	
	public static Book queryBook(String barcode)
	{
		try
		{
			Book book = queryBooks(barcode, "barcode").get(0);
			return book;
		}
		catch (NullPointerException x)
		{
			return null;
		}
	}
	
	public static ArrayList<Book> queryBooks(String value, String field)
	{
		if(value == "null")
			return null;
		String query = "SELECT barcode, title, author, series, quantity, available, lexile, location, notes FROM " + databaseName + "." + bookTable + " WHERE " + field + " = '" + value + "'";
		ResultSet r;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			r = s.executeQuery(query);
			ArrayList<Book> books = new ArrayList<Book>();
			do
			{
				r.next();
				Book b = new Book(r.getString("barcode"), r.getString("title"), r.getString("author"), r.getString("series"), r.getInt("quantity"), r.getInt("available"));
				b.setLexile(r.getInt("lexile"));
				b.setLocation(r.getString("location"));
				b.setNotes(r.getString("notes"));
				books.add(b);
			} while(!r.isLast());
			s.close();
			conn.close();
			return books;
		} catch (SQLException e) {
		} finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public static ArrayList<User> queryUsers(String value, String field)
	{
		String query = "SELECT id, name, level, out_book, bonus_book, checked_out FROM " + databaseName + "." + userTable + " WHERE " + field + " = '" + value + "'";
		ResultSet r;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			r = s.executeQuery(query);
			ArrayList<User> users = new ArrayList<User>();
			do
			{
				r.next();
				users.add(new User(r.getString("id"), r.getString("name"), r.getInt("level"), r.getString("out_book"), r.getString("bonus_book"), r.getDate("checked_out")));
			} while(!r.isLast());
			s.close();
			conn.close();
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static User queryUser(String ID)
	{
		try
		{
			User user = queryUsers(ID, "id").get(0);
			return user;
		}
		catch (NullPointerException x)
		{
			return null;
		}
	}
	
	public static ArrayList<User> queryOverdue()
	{
		String query = "SELECT name, level, out_book, bonus_book, checked_out FROM " + databaseName + "." + userTable + " WHERE checked_out < NOW() - INTERVAL 4 WEEKS";
		ResultSet r;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			r = s.executeQuery(query);
			ArrayList<User> users = new ArrayList<User>();
			do
			{
				r.next();
				users.add(new User(r.getString("id"), r.getString("name"), r.getInt("level"), r.getString("out_book"), r.getString("bonus_book"), r.getDate("checked_out")));
			} while(!r.isLast());
			s.close();
			conn.close();
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
		
	public static void updateUser(User user)
	{
		String q = "UPDATE " + databaseName + "." + userTable + " SET " + "name='" + user.getName() + "', level=" + user.getLevel() + ", checked_out=" + user.getCheckedOutString() + ", out_book='";
		try
		{
			q=q + user.getBookOut().getBarcode();
		}
		catch (Exception x)
		{
			
		}
		q=q+"', bonus_book='";
		try
		{
			 q=q + user.getBonusBook().getBarcode();
		}
		catch (Exception x)
		{
		}
		q=q+"' WHERE id='" + user.getUserId() + "'";
		execute(q);
	}
	
	public static void updateBook(Book book)
	{
		execute("UPDATE " + databaseName + "." + bookTable + " SET title='" + book.getTitle() + "', author='" + book.getAuthor() + "', series='" + book.getSeries() + "', barcode='" + book.getBarcode() + "', quantity=" + book.getQuantity() + ", available=" + book.getAvailable() + ", lexile=" + book.getLexile() + ", location='" + book.getLocation() + "', notes='" + book.getNotes() + "' WHERE barcode='" + book.getBarcode() + "'");
	}
	
	public static void execute(String query)
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			s.execute(query);
			s.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		
		return;
	}
	
	public static ResultArray query(String query)
	{
		Connection conn = null;
		ResultArray results = null;
		try {
			ResultSet r;
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			r = s.executeQuery(query);
			results = new ResultArray(r);
			s.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		
		return results;
	}
	
	public static boolean isLocked()
	{
		boolean b = true;
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			ResultSet r = s.executeQuery("SELECT locked_status FROM " + databaseName + "." + libraryTable);
			r.next();
			b = r.getString("locked_status").equals("locked");
			s.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return true;
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		
		return b;
	}
	
	public static void setLocked(boolean lock)
	{
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(
			        "jdbc:mysql://" +
			        address + ":" + portNumber + "/",
			        username, password);
			Statement s = conn.createStatement();
			s.execute("UPDATE " + databaseName + "." + libraryTable + " SET locked_status='" + (lock?"locked":"unlocked") + "' WHERE id=1");
			s.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			return;
		}
		finally
		{
			try {
				conn.close();
			} catch (SQLException e) {
				
			}
		}
		
	}
}
