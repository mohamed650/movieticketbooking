package scii.training.util;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.colors.DeviceRgb;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.borders.Border;
import com.itextpdf.layout.element.*;
import com.itextpdf.layout.properties.TextAlignment;
import com.itextpdf.layout.properties.VerticalAlignment;


public class InvoiceReport {

	public String invoice(String name, String email_id, String contactno, String date, String movie_name, String theatre_name,
						 String date_of_reservation, String show_time, int no_of_seatsbooked, int total_price) {
		try {
			String path = "D:\\Invoice.pdf";
			PdfWriter pdfWriter = new PdfWriter(path);
			PdfDocument pdfDocument = new PdfDocument(pdfWriter);
			Document document = new Document(pdfDocument);
			pdfDocument.setDefaultPageSize(PageSize.A4);
			
			float col = 280f;
			float columnWidth[] = {col, col};
			Table table = new Table(columnWidth);
			
			table.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE);
					
			table.addCell(new Cell().add(new Paragraph("INVOICE"))
					.setTextAlignment(TextAlignment.CENTER)
					.setVerticalAlignment(VerticalAlignment.MIDDLE)
					.setMarginTop(30f)
					.setMarginBottom(30f)
					.setFontSize(30f)
					.setBorder(Border.NO_BORDER)
					);
			
			table.addCell(new Cell().add(new Paragraph("Book My Show\nBanashankari, Tumkur\n8310851687"))
					.setTextAlignment(TextAlignment.RIGHT)
					.setMarginTop(30f)
					.setMarginBottom(30f)
					.setBorder(Border.NO_BORDER)
					.setMarginRight(10f)
					);
			
			float colWidth[] = {80, 300, 100, 80};
			Table customerInfoTable = new Table(colWidth);
			
			customerInfoTable.addCell(new Cell(0, 4).add(new Paragraph("Customer Information"))
					.setBold()
					.setBorder(Border.NO_BORDER)
					);
			customerInfoTable.addCell(new Cell().add(new Paragraph("Name")).setBorder(Border.NO_BORDER));
			customerInfoTable.addCell(new Cell().add(new Paragraph(name)).setBorder(Border.NO_BORDER));
			customerInfoTable.addCell(new Cell().add(new Paragraph("Email Id")).setBorder(Border.NO_BORDER));
			customerInfoTable.addCell(new Cell().add(new Paragraph(email_id)).setBorder(Border.NO_BORDER));
			
			customerInfoTable.addCell(new Cell().add(new Paragraph("Phone No.")).setBorder(Border.NO_BORDER));
			customerInfoTable.addCell(new Cell().add(new Paragraph(contactno)).setBorder(Border.NO_BORDER));
			customerInfoTable.addCell(new Cell().add(new Paragraph("Date")).setBorder(Border.NO_BORDER));
			customerInfoTable.addCell(new Cell().add(new Paragraph(date)).setBorder(Border.NO_BORDER));
			
			float movieInfoColWidth[] = {140, 140, 140, 140, 140, 140};
			Table movieInfoTable = new Table(movieInfoColWidth);
			
			movieInfoTable.addCell(new Cell().add(new Paragraph("Movie"))
					.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.RIGHT));
			movieInfoTable.addCell(new Cell().add(new Paragraph("Theatre"))
					.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.RIGHT));
			movieInfoTable.addCell(new Cell().add(new Paragraph("Date"))
					.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.RIGHT));
			movieInfoTable.addCell(new Cell().add(new Paragraph("Show Time"))
					.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.RIGHT));
			movieInfoTable.addCell(new Cell().add(new Paragraph("Seats"))
					.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.RIGHT));
			movieInfoTable.addCell(new Cell().add(new Paragraph("Price"))
					.setBackgroundColor(new DeviceRgb(219, 31, 72))
					.setFontColor(ColorConstants.WHITE)
					.setTextAlignment(TextAlignment.RIGHT));
			
			
			movieInfoTable.addCell(new Cell().add(new Paragraph(movie_name)));
			movieInfoTable.addCell(new Cell().add(new Paragraph(theatre_name)));
			movieInfoTable.addCell(new Cell().add(new Paragraph(date_of_reservation)));
			movieInfoTable.addCell(new Cell().add(new Paragraph(show_time)));
			movieInfoTable.addCell(new Cell().add(new Paragraph(String.valueOf(no_of_seatsbooked))));
			movieInfoTable.addCell(new Cell().add(new Paragraph(String.valueOf(total_price))));
			
			
			document.add(table);
			document.add(new Paragraph("\n"));
			document.add(customerInfoTable);
			document.add(new Paragraph("\n"));
			document.add(movieInfoTable);
			document.add(new Paragraph("\nMohamed Khalid").setTextAlignment(TextAlignment.RIGHT));
			document.add(new Paragraph("(Authorised Signatory)").setTextAlignment(TextAlignment.RIGHT));
			document.close();
			return path;
		}catch(Exception e) {
			return "Error generating pdf";
		}
	}
}
