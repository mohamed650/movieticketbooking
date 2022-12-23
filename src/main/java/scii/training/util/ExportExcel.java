package scii.training.util;

import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import scii.training.model.ReservationModel;

public class ExportExcel {
	
	private XSSFWorkbook workBook;
	private XSSFSheet sheet;
	private List<ReservationModel> reservations;
	
	public ExportExcel(List<ReservationModel> reservations) {
		this.reservations = reservations;
		workBook = new XSSFWorkbook();
	}
	
	private void writeHeaderLine() {
		sheet = workBook.createSheet("Reservations");
		Row row = sheet.createRow(0);
		CellStyle style = workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		font.setBold(true);
		font.setFontHeight(16);
		style.setFont(font);
		
		createCell(row, 0, "Id", style);
		createCell(row, 1, "Email Id", style);
		createCell(row, 2, "Movie Name", style);
		createCell(row, 3, "Theatre Name", style);
		createCell(row, 4, "Date of Reservation", style);
		createCell(row, 5, "Show Time", style);
		createCell(row, 6, "No of Seats", style);
		createCell(row, 7, "Total Price", style);
	}
	
	private void createCell(Row row, int columnCount, Object value, CellStyle style) {
		sheet.autoSizeColumn(columnCount);
		Cell cell = row.createCell(columnCount);
		if(value instanceof Integer) {
			cell.setCellValue((Integer) value);
		}else if(value instanceof Boolean) {
			cell.setCellValue((Boolean) value);
		}else {
			cell.setCellValue((String) value);
		}
		cell.setCellStyle(style);
	}
	
	private void writeDataLines() {
		int rowCount = 1;
		CellStyle style = workBook.createCellStyle();
		XSSFFont font = workBook.createFont();
		font.setFontHeight(14);
		style.setFont(font);
		style.setWrapText(true);
		
		for(ReservationModel reservation : reservations) {
			Row row = sheet.createRow(rowCount++);
			int columnCount = 0;
			
			createCell(row, columnCount++, reservation.getReservation_id(), style);
			createCell(row, columnCount++, reservation.getEmail_id(), style);
			createCell(row, columnCount++, reservation.getMovie_name(), style);
			createCell(row, columnCount++, reservation.getTheatre_name(), style);
			createCell(row, columnCount++, reservation.getDate_of_reservation(), style);
			createCell(row, columnCount++, reservation.getShow_time(), style);
			createCell(row, columnCount++, reservation.getNo_of_seatsbooked(), style);
			createCell(row, columnCount++, reservation.getTotal_price(), style);
		}
	}
	
	public void export(HttpServletResponse response) throws Exception {
		writeHeaderLine();
		writeDataLines();
		
		ServletOutputStream outputStream = response.getOutputStream();
		workBook.write(outputStream);
		workBook.close();
		outputStream.close();
	}
}
