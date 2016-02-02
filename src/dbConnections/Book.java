package dbConnections;


public class Book
{
	private String title;
	private String author;
	private String series;
	private String barcode;
	private int quantity;
	private int available;
	
	// used for search functionality
	private String genre;
	private int lexile;
	private String notes;
	private String location;
	
	public Book(String title, String author, String series, String genre,
			int level, String summary, String location) {
		super();
		this.title = title;
		this.author = author;
		this.series = series;
		this.genre = genre;
		this.lexile = level;
		this.notes = summary;
		this.location = location;
	}
	
	public Book(String barcode, String title, String author, String series, int quantity, int available)
	{
		super();
		this.barcode = barcode;
		this.title = title;
		this.author = author;
		this.series = series;
		this.quantity = quantity;
		this.available = available;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	
	public String getBarcode() {
		return barcode;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}
	
	public String getGenre() {
		return genre;
	}
	public void setGenre(String genre) {
		this.genre = genre;
	}
	
	public int getLexile() {
		return lexile;
	}
	public void setLexile(int lexile) {
		this.lexile = lexile;
	}
	
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	public boolean checkout()
	{
		if(available > 0)
		{
			available--;
			return true;
		}
		return false;
	}
	public void checkin()
	{
		if(available<quantity)
		{
			available++;
		}
		
	}

	public String getSeries() {
		return series;
	}

	public void setSeries(String series) {
		this.series = series;
	}
}
