package dbConnections;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class User
{
	private String userId;
	private String name;
	private int level;
	private int searchLimit;
	private ArrayList<String> pastSearches;
	private Book bookOut;
	private Book bonusBook;
	private ArrayList<String> pastBooks;
	private Date checkedOut;
	
	public User(String userId, String name, int level, String bookOut, String bonusBook) {
		super();
		this.userId = userId;
		this.name = name;
		this.level = level;
		try{
		this.bookOut = Database.queryBook(bookOut);
		}
		catch (Exception x)
		{
			this.bookOut = null;
		}
		try
		{
			this.setBonusBook(Database.queryBook(bonusBook));
		}
		catch (Exception x)
		{
			this.bonusBook = null;
		}
	}
	
	public User(String userId, String name, int level, String bookOut, String bonusBook, Date checkedOut) {
		this(userId, name, level, bookOut, bonusBook);
		this.setCheckedOut(checkedOut);
	}
	
	public User(String userId, String name, int level, String bookOut, String bonusBook, String checkedOut)
	{
		this(userId, name, level, bookOut, bonusBook);
		
		Date result;
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		try {
			result = new java.sql.Date(formatter.parse(checkedOut).getTime());
		} catch (ParseException e) {
			result = null;
			e.printStackTrace();
		}
		this.checkedOut = result;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}

	public int getSearchLimit() {
		return searchLimit;
	}
	public void setSearchLimit(int searchLimit) {
		this.searchLimit = searchLimit;
	}

	public ArrayList<String> getPastSearches() {
		return pastSearches;
	}
	public void addSearch(String search)
	{
		pastSearches.add(search);
	}
	public void setPastSearches(ArrayList<String> pastSearches) {
		this.pastSearches = pastSearches;
	}

	public ArrayList<String> getPastBooks() {
		return pastBooks;
	}
	public void setPastBooks(ArrayList<String> pastBooks) {
		this.pastBooks = pastBooks;
	}

	public Book getBookOut() {
		return bookOut;
	}
	public void returnBook(Book book)
	{
		bookOut = null;
		book.checkin();
		Database.updateBook(book);
		Database.updateUser(this);
	}
	public void checkOutBook(Book book)
	{
		bookOut = book;
		
		checkedOut = new Date(Calendar.getInstance().getTimeInMillis());
		
		book.checkout();
		Database.updateBook(book);
		Database.updateUser(this);
	}
	
	public void checkInBook(Book book)
	{
		if(bookOut.getBarcode().equals(book.getBarcode()))
			bookOut = null;
		else if(bonusBook!=null && bonusBook.getBarcode().equals(book.getBarcode()))
			bonusBook = null;
		else
			return;
		
		if(bookOut == null && bonusBook == null)
			checkedOut = null;
		
		book.checkin();
		Database.updateBook(book);
		Database.updateUser(this);
	}

	public Book getBonusBook() {
		return bonusBook;
	}

	public void setBonusBook(Book bonusBook) {
		this.bonusBook = bonusBook;
	}

	public Date getCheckedOut() {
		return checkedOut;
	}

	public void setCheckedOut(Date checkedOut) {
		this.checkedOut = checkedOut;
	}
	
	public String updateUserQuery()
	{
		String query = "";
		
		return query;
	}
	
	@SuppressWarnings("deprecation")
	public String getCheckedOutString()
	{
		
		if(this.checkedOut == null)
			return "NULL";
		String d;
		
		d = "'" + String.valueOf(checkedOut.getYear() + 1900);
		d= d + "-" + String.valueOf(checkedOut.getMonth() + 1);
		d= d + "-" + String.valueOf(checkedOut.getDate()) + "'";
		
		return d;
	}
}
