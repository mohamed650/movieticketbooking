package scii.training.util;

import java.util.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import scii.training.model.ReservationModel;

public class GeneratedPdfReport {
	
	private static final Logger logger = LogManager.getLogger(GeneratedPdfReport.class.getName());
	
	public static ByteArrayInputStream bookingReport(List<ReservationModel> reservations) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {
			PdfPTable table = new PdfPTable(8);
			table.setWidthPercentage(100);
			table.setWidths(new int[] {8, 25, 20, 20, 20, 10, 10, 10});
			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
			PdfPCell hcell;
			
            hcell = new PdfPCell(new Phrase("Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Email Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Movie Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Theatre Name", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Date of Reservation", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Show Time", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("No of Seats", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Total Price", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(hcell);
            
            for(ReservationModel reservation : reservations) {
            	PdfPCell cell;
            	
            	 cell = new PdfPCell(new Phrase(String.valueOf(reservation.getReservation_id())));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(reservation.getEmail_id()));
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(reservation.getMovie_name()));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setPaddingRight(5);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(reservation.getTheatre_name()));
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(reservation.getDate_of_reservation()));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setPaddingRight(5);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(reservation.getShow_time()));
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);

                 cell = new PdfPCell(new Phrase(String.valueOf(reservation.getNo_of_seatsbooked())));
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 cell.setPaddingRight(5);
                 table.addCell(cell);
                 
                 cell = new PdfPCell(new Phrase(String.valueOf(reservation.getTotal_price())));
                 cell.setPaddingLeft(5);
                 cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                 cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                 table.addCell(cell);
            }
            
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();
		}catch(Exception e) {
			logger.log(Level.ERROR, e);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}
