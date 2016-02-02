import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.TitledBorder;

import dbConnections.Book;
import dbConnections.Database;



public class BookInfoPanel extends JPanel implements ActionListener
{
	private JTextField titleField;
	private JTextField authorField;
	private JTextField seriesField;
	private JTextField isbnField;
	private JTextField genreField;
	private JSpinner quantitySpinner;
	private JSpinner availableSpinner;
	private JButton btnUpdate;

	private boolean adding;

	private Book book;
	private JButton btnPrintBarcodes;
	private JLabel lblLexile;
	private JSpinner lexileSpinner;
	private JTextArea notesArea;
	private JLabel lblLocation;
	private JTextField locationField;
	private JSpinner printQuantitySpinner;
	private JLabel lblPrintQuantity;

	
	/**
	 * Create the panel.
	 */
	public BookInfoPanel(Book book, boolean adding) {

		this.adding = adding;
		this.book = book;
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);

		JLabel titleLabel = new JLabel("Title");
		GridBagConstraints gbc_titleLabel = new GridBagConstraints();
		gbc_titleLabel.insets = new Insets(10, 10, 5, 5);
		gbc_titleLabel.anchor = GridBagConstraints.EAST;
		gbc_titleLabel.gridx = 0;
		gbc_titleLabel.gridy = 0;
		add(titleLabel, gbc_titleLabel);

		titleField = new JTextField();
		GridBagConstraints gbc_titleField = new GridBagConstraints();
		gbc_titleField.gridwidth = 5;
		gbc_titleField.insets = new Insets(10, 0, 5, 5);
		gbc_titleField.fill = GridBagConstraints.HORIZONTAL;
		gbc_titleField.gridx = 1;
		gbc_titleField.gridy = 0;
		add(titleField, gbc_titleField);

		JLabel lblAuthor = new JLabel("Author");
		GridBagConstraints gbc_lblAuthor = new GridBagConstraints();
		gbc_lblAuthor.anchor = GridBagConstraints.EAST;
		gbc_lblAuthor.insets = new Insets(0, 10, 5, 5);
		gbc_lblAuthor.gridx = 6;
		gbc_lblAuthor.gridy = 0;
		add(lblAuthor, gbc_lblAuthor);

		authorField = new JTextField();
		GridBagConstraints gbc_authorField = new GridBagConstraints();
		gbc_authorField.insets = new Insets(0, 0, 5, 10);
		gbc_authorField.fill = GridBagConstraints.HORIZONTAL;
		gbc_authorField.gridx = 7;
		gbc_authorField.gridy = 0;
		add(authorField, gbc_authorField);

		JLabel lblIsbn = new JLabel("ISBN");
		GridBagConstraints gbc_lblIsbn = new GridBagConstraints();
		gbc_lblIsbn.anchor = GridBagConstraints.EAST;
		gbc_lblIsbn.insets = new Insets(0, 10, 5, 5);
		gbc_lblIsbn.gridx = 0;
		gbc_lblIsbn.gridy = 1;
		add(lblIsbn, gbc_lblIsbn);

		isbnField = new JTextField();
		GridBagConstraints gbc_isbnField = new GridBagConstraints();
		gbc_isbnField.gridwidth = 5;
		gbc_isbnField.insets = new Insets(0, 0, 5, 5);
		gbc_isbnField.fill = GridBagConstraints.HORIZONTAL;
		gbc_isbnField.gridx = 1;
		gbc_isbnField.gridy = 1;
		add(isbnField, gbc_isbnField);

		JLabel lblSeries = new JLabel("Series");
		GridBagConstraints gbc_lblSeries = new GridBagConstraints();
		gbc_lblSeries.anchor = GridBagConstraints.EAST;
		gbc_lblSeries.insets = new Insets(0, 10, 5, 5);
		gbc_lblSeries.gridx = 6;
		gbc_lblSeries.gridy = 1;
		add(lblSeries, gbc_lblSeries);

		seriesField = new JTextField();
		GridBagConstraints gbc_seriesField = new GridBagConstraints();
		gbc_seriesField.insets = new Insets(0, 0, 5, 10);
		gbc_seriesField.fill = GridBagConstraints.HORIZONTAL;
		gbc_seriesField.gridx = 7;
		gbc_seriesField.gridy = 1;
		add(seriesField, gbc_seriesField);

		JLabel lblGenre = new JLabel("Genre");
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.anchor = GridBagConstraints.EAST;
		gbc_lblGenre.insets = new Insets(0, 10, 5, 5);
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 2;
		add(lblGenre, gbc_lblGenre);

		genreField = new JTextField();
		GridBagConstraints gbc_genreComboBox = new GridBagConstraints();
		gbc_genreComboBox.gridwidth = 5;
		gbc_genreComboBox.insets = new Insets(0, 0, 5, 5);
		gbc_genreComboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_genreComboBox.gridx = 1;
		gbc_genreComboBox.gridy = 2;
		add(genreField, gbc_genreComboBox);

		lblLexile = new JLabel("Lexile");
		GridBagConstraints gbc_lblLexile = new GridBagConstraints();
		gbc_lblLexile.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblLexile.insets = new Insets(0, 0, 5, 5);
		gbc_lblLexile.gridx = 0;
		gbc_lblLexile.gridy = 3;
		add(lblLexile, gbc_lblLexile);

		lexileSpinner = new JSpinner();
		GridBagConstraints gbc_lexileSpinner = new GridBagConstraints();
		gbc_lexileSpinner.anchor = GridBagConstraints.NORTH;
		gbc_lexileSpinner.insets = new Insets(0, 0, 5, 5);
		gbc_lexileSpinner.gridx = 1;
		gbc_lexileSpinner.gridy = 3;
		add(lexileSpinner, gbc_lexileSpinner);

		notesArea = new JTextArea();
		notesArea.setLineWrap(true);
		notesArea.setWrapStyleWord(true);
		notesArea.setColumns(35);
		notesArea.setRows(5);
		GridBagConstraints gbc_notesArea = new GridBagConstraints();
		gbc_notesArea.gridwidth = 2;
		gbc_notesArea.gridheight = 4;
		gbc_notesArea.insets = new Insets(0, 0, 5, 10);
		gbc_notesArea.fill = GridBagConstraints.BOTH;
		gbc_notesArea.gridx = 6;
		gbc_notesArea.gridy = 2;
		add(notesArea, gbc_notesArea);
		notesArea.setBorder(new TitledBorder("Notes"));

		lblLocation = new JLabel("Location");
		GridBagConstraints gbc_lblLocation = new GridBagConstraints();
		gbc_lblLocation.anchor = GridBagConstraints.EAST;
		gbc_lblLocation.insets = new Insets(0, 0, 5, 5);
		gbc_lblLocation.gridx = 2;
		gbc_lblLocation.gridy = 3;
		add(lblLocation, gbc_lblLocation);

		locationField = new JTextField();
		GridBagConstraints gbc_locationField = new GridBagConstraints();
		gbc_locationField.gridwidth = 3;
		gbc_locationField.insets = new Insets(0, 0, 5, 5);
		gbc_locationField.fill = GridBagConstraints.HORIZONTAL;
		gbc_locationField.gridx = 3;
		gbc_locationField.gridy = 3;
		add(locationField, gbc_locationField);
		locationField.setColumns(10);

		JLabel lblNewLabel = new JLabel("Quantity");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.anchor = GridBagConstraints.NORTHEAST;
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 4;
		add(lblNewLabel, gbc_lblNewLabel);

		quantitySpinner = new JSpinner();
		quantitySpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_quantitySpinner = new GridBagConstraints();
		gbc_quantitySpinner.anchor = GridBagConstraints.NORTHEAST;
		gbc_quantitySpinner.insets = new Insets(0, 0, 5, 5);
		gbc_quantitySpinner.gridx = 1;
		gbc_quantitySpinner.gridy = 4;
		add(quantitySpinner, gbc_quantitySpinner);

		JLabel lblAvailable = new JLabel("Available");
		GridBagConstraints gbc_lblAvailable = new GridBagConstraints();
		gbc_lblAvailable.anchor = GridBagConstraints.NORTH;
		gbc_lblAvailable.insets = new Insets(0, 0, 5, 5);
		gbc_lblAvailable.gridx = 2;
		gbc_lblAvailable.gridy = 4;
		add(lblAvailable, gbc_lblAvailable);

		availableSpinner = new JSpinner();
		availableSpinner.setModel(new SpinnerNumberModel(new Integer(0), new Integer(0), null, new Integer(1)));
		GridBagConstraints gbc_availableSpinner = new GridBagConstraints();
		gbc_availableSpinner.anchor = GridBagConstraints.NORTH;
		gbc_availableSpinner.insets = new Insets(0, 0, 5, 10);
		gbc_availableSpinner.gridx = 3;
		gbc_availableSpinner.gridy = 4;
		add(availableSpinner, gbc_availableSpinner);
				
				lblPrintQuantity = new JLabel("Print Quantity");
				GridBagConstraints gbc_lblPrintQuantity = new GridBagConstraints();
				gbc_lblPrintQuantity.anchor = GridBagConstraints.SOUTH;
				gbc_lblPrintQuantity.gridwidth = 2;
				gbc_lblPrintQuantity.insets = new Insets(0, 0, 5, 5);
				gbc_lblPrintQuantity.gridx = 1;
				gbc_lblPrintQuantity.gridy = 5;
				add(lblPrintQuantity, gbc_lblPrintQuantity);
				
				printQuantitySpinner = new JSpinner();
				printQuantitySpinner.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
				GridBagConstraints gbc_printQuantitySpinner = new GridBagConstraints();
				gbc_printQuantitySpinner.anchor = GridBagConstraints.SOUTH;
				gbc_printQuantitySpinner.insets = new Insets(0, 0, 5, 5);
				gbc_printQuantitySpinner.gridx = 3;
				gbc_printQuantitySpinner.gridy = 5;
				add(printQuantitySpinner, gbc_printQuantitySpinner);
		
				btnPrintBarcodes = new JButton("Print Barcodes");
				GridBagConstraints gbc_btnPrintBarcodes = new GridBagConstraints();
				gbc_btnPrintBarcodes.anchor = GridBagConstraints.SOUTH;
				gbc_btnPrintBarcodes.insets = new Insets(0, 0, 5, 5);
				gbc_btnPrintBarcodes.gridx = 4;
				gbc_btnPrintBarcodes.gridy = 5;
				add(btnPrintBarcodes, gbc_btnPrintBarcodes);
				btnPrintBarcodes.addActionListener(this);
				
						btnUpdate = new JButton("Update");
						GridBagConstraints gbc_btnUpdate = new GridBagConstraints();
						gbc_btnUpdate.anchor = GridBagConstraints.SOUTH;
						gbc_btnUpdate.insets = new Insets(0, 0, 5, 10);
						gbc_btnUpdate.gridx = 5;
						gbc_btnUpdate.gridy = 5;
						add(btnUpdate, gbc_btnUpdate);
						btnUpdate.addActionListener(this);

		populate(book);

		if(adding)
			btnUpdate.setText("Add");
	}

	public void populate(Book book)
	{
		titleField.setText(book.getTitle());
		authorField.setText(book.getAuthor());
		seriesField.setText(book.getSeries());
		isbnField.setText(book.getBarcode());
		genreField.setText(book.getGenre());
		quantitySpinner.setValue(book.getQuantity());
		availableSpinner.setValue(book.getAvailable());
		lexileSpinner.setValue(book.getLexile());
		notesArea.setText(book.getNotes());
		locationField.setText(book.getLocation());
	}

	public void generateBook()
	{
		book = new Book(isbnField.getText(), titleField.getText(), authorField.getText(), seriesField.getText(), (Integer)quantitySpinner.getValue(), (Integer)availableSpinner.getValue());
		book.setGenre(genreField.getText());
		book.setLocation(locationField.getText());
		book.setLexile((Integer)lexileSpinner.getValue());
		book.setNotes(notesArea.getText());
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == btnUpdate)
		{
			generateBook();
			if(adding)
				Database.addBook(book);
			else
				Database.updateBook(book);
		}
		else if(e.getSource() == btnPrintBarcodes)
		{
			generateBook();

			try {
				printBook();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}

	public void printBook() throws IOException {


		String title = book.getTitle();
		String barcode = book.getBarcode();
		int quantity = (Integer)printQuantitySpinner.getValue();
		
		Runtime.getRuntime().exec("ConsoleLabel.exe \"" + title + "\" \"" + barcode + "\" \"" + quantity + "\"");
		
	}
	
}
