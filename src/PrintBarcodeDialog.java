import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.EmptyBorder;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.Barcode;
import com.itextpdf.text.pdf.BarcodeEAN;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;

import dbConnections.Book;

public class PrintBarcodeDialog extends JDialog implements ActionListener
{

	private final JPanel contentPanel = new JPanel();
	
	private Book book;
	private JButton okButton;
	private JButton cancelButton;
	private JSpinner quantitySpinner;

	/**
	 * Create the dialog.
	 */
	public PrintBarcodeDialog(Book book) {
		setBounds(100, 100, 450, 300);
		this.book = book;

		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JLabel lblQuantity = new JLabel("Quantity");
				buttonPane.add(lblQuantity);
			}
			{
				quantitySpinner = new JSpinner();
				quantitySpinner.setModel(new SpinnerNumberModel(0, 0, 100, 1));
				buttonPane.add(quantitySpinner);
			}
			{
				okButton = new JButton("Print");
				okButton.addActionListener(this);
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(this);
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public void createPdf(String filename) throws IOException, DocumentException {
        // step 1
        Document document = new Document(new Rectangle(300, 80));
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));
        // step 3
        document.open();
        // step 4
        PdfContentByte cb = writer.getDirectContent();
 
        String s = book.getBarcode();
        while((13 - s.length()) > 0)
        {
        	s = "0" + s;
        }
        
        BarcodeEAN codeEAN = new BarcodeEAN();
       
        codeEAN.setCodeType(Barcode.EAN13);
        codeEAN.setCode(s);
        document.add(codeEAN.createImageWithBarcode(cb, null, null));
 
        PrinterJob pj = PrinterJob.getPrinterJob();
        
        pj.printDialog();
        
        try {
			pj.print();
		} catch (PrinterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        // step 5
        document.close();
    }

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if(e.getSource() == cancelButton)
		{
			this.dispose();
		}
		else if(e.getSource() == okButton)
		{
			try {
				createPdf("C:/testing.pdf");
			} catch (IOException x) {
				// TODO Auto-generated catch block
				x.printStackTrace();
			} catch (DocumentException x) {
				// TODO Auto-generated catch block
				x.printStackTrace();
			}
		}
		
	}

}
