package scii.training.service;

import java.io.File;
import java.util.List;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import scii.training.mapper.IMapper;
import scii.training.model.MovieDescriptionModel;
import scii.training.model.MovieModel;
import scii.training.model.ReservationModel;
import scii.training.model.ShowTimingsModel;
import scii.training.model.TheatreModel;
import scii.training.model.TheatreMovieModel;
import scii.training.model.UserModel;
import scii.training.util.GenerateOtp;

@Component
public class IServiceImplementation implements IService {
	
	@Autowired(required=false)
	IMapper imapper;
	
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Value("${spring.mail.username}")
	private String sender;
	
	@Override
	public int insertUser(UserModel user) {
		int status = imapper.insertUser(user);
		return status;
	}

	@Override
	public List<UserModel> checkUserById(UserModel user) {
		List<UserModel> list = imapper.checkUserById(user);
		return list;
	}

	@Override
	public List<UserModel> checkUser(UserModel user) {
		List<UserModel> list = imapper.checkUser(user);
		return list;
	}
	
	@Override
	public List<TheatreModel> loadTheatre(TheatreModel theatre) {
		List<TheatreModel> loadTheatre = imapper.loadTheatre(new TheatreModel());
		return loadTheatre;
	}

	@Override
	public List<MovieModel> loadMovie(MovieModel movie) {
		List<MovieModel> loadMovie = imapper.loadMovie(new MovieModel());
		return loadMovie;
	}

	@Override
	public List<TheatreModel> loadTheatreSeats(TheatreModel theatre) {
		List<TheatreModel> loadTheatre = imapper.loadTheatreSeats(theatre);
		return loadTheatre;
	}

	@Override
	public List<ShowTimingsModel> loadTheatreShows(ShowTimingsModel theatreShows) {
		List<ShowTimingsModel> loadTheatreShows = imapper.loadTheatreShows(theatreShows);
		return loadTheatreShows;
	}

	@Override
	public List<TheatreMovieModel> loadTheatreNames(TheatreMovieModel theatreNames) {
		List<TheatreMovieModel> loadTheatreNames = imapper.loadTheatreNames(theatreNames);
		return loadTheatreNames;
	}

	@Override
	public List<MovieModel> loadMovieDates(MovieModel movieDates) {
		List<MovieModel> moviedatesList = imapper.loadMovieDates(movieDates);
		return moviedatesList;
	}

	@Override
	public List<TheatreMovieModel> loadTicketPrice(TheatreMovieModel ticketPrice) {
		List<TheatreMovieModel> ticketPriceList = imapper.loadTicketPrice(ticketPrice);
		return ticketPriceList;
	}

	public int bookTicket(ReservationModel ticketBooking) {
		int status = imapper.bookTicket(ticketBooking);
		return status;
	}

	@Override
	public List<MovieDescriptionModel> loadDescription(MovieDescriptionModel movieDescription) {
		List<MovieDescriptionModel> moviedescList = imapper.loadDescription(movieDescription);
		return moviedescList;
	}

	@Override
	public List<ReservationModel> checkTransaction(ReservationModel transactions) {
		List<ReservationModel> transactionList = imapper.checkTransaction(transactions);
		return transactionList;
	}

	@Override
	public int insertTransaction(ReservationModel inserttransaction) {
		int status = imapper.insertTransaction(inserttransaction);
		return status;
	}

	@Override
	public int updateTransaction(ReservationModel updatetransaction) {
		int status = imapper.updateTransaction(updatetransaction);
		return status;
	}

	@Override
	public List<MovieModel> loadMovieInformation(MovieModel movieInformation) {
		List<MovieModel> movieInformationList = imapper.loadMovieInformation(movieInformation);
		return movieInformationList;
	}

	/*@Override
	public List<TheatreModel> loadTheatreInformation(TheatreModel theatreInformation) {
		List<TheatreModel> theatreInformationList = imapper.loadTheatreInformation(theatreInformation);
		return theatreInformationList;
	}*/

	@Override
	public int insertTheatre(TheatreModel inserttheatre) {
		int status = imapper.insertTheatre(inserttheatre);
		return status;
	}

	@Override
	public List<TheatreModel> checkTheatreById(TheatreModel theatre) {
		List<TheatreModel> loadTheatre = imapper.checkTheatreById(theatre);
		return loadTheatre;
	}

	@Override
	public List<MovieModel> checkMovieById(MovieModel checkmovie) {
		List<MovieModel> checkList = imapper.checkMovieById(checkmovie);
		return checkList;
	}

	@Override
	public int insertMovie(MovieModel insertmovie) {
		int status = imapper.insertMovie(insertmovie);
		return status;
	}

	@Override
	public List<TheatreMovieModel> checkShowTimings(TheatreMovieModel showtime) {
		List<TheatreMovieModel> showList = imapper.checkShowTimings(showtime);
		return showList;
	}

	@Override
	public int insertTimings(TheatreMovieModel inserttimings) {
		int status = imapper.insertTimings(inserttimings);
		return status;
	}

	@Override
	public List<TheatreMovieModel> loadMovieNames(TheatreMovieModel movieNames) {
		List<TheatreMovieModel> loadMovieList = imapper.loadMovieNames(movieNames);
		return loadMovieList;
	}

	@Override
	public int insertDescription(MovieDescriptionModel moviedescription) {
		int status = imapper.insertDescription(moviedescription);
		return status;
	}

	@Override
	public List<ReservationModel> loadTicketDetails(ReservationModel ticketdetails) {
		List<ReservationModel> listticket = imapper.loadTicketDetails(ticketdetails);
		return listticket;
	}

	@Override
	public int deleteBooking(ReservationModel deletebooking) {
		int status = imapper.deleteBooking(deletebooking);
		return status;
	}

	@Override
	public int updateBooking(ReservationModel updatebooking) {
		int status = imapper.updateBooking(updatebooking);
		return status;
	}

	@Override
	public List<ReservationModel> checkBooking(ReservationModel checkbooking) {
		List<ReservationModel> bookingList = imapper.checkBooking(checkbooking);
		return bookingList;
	}

	@Override
	public String sendMail(String email, String path) {
		try {
			
			MimeMessage mimeMessage = javaMailSender.createMimeMessage();
			MimeMessageHelper mimeMessageHelper;
			
			String recepient = email;
			String text = "Your Ticket is Booked Successfully..\n Please find the Invoice attached below.";
			String subject = "Ticket Confirmation";
			mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
			mimeMessageHelper.setFrom(sender);
			mimeMessageHelper.setTo(recepient);
			mimeMessageHelper.setText(text);
			mimeMessageHelper.setSubject(subject);
			
			FileSystemResource file = new FileSystemResource(new File(path));
			mimeMessageHelper.addAttachment(file.getFilename(), file);
			/*SimpleMailMessage mailMessage = new SimpleMailMessage();
			String recepient = email;
			String text = "Your Ticket is Booked Successfully..";
			String subject = "Ticket Confirmation";
			mailMessage.setFrom(sender);
			mailMessage.setTo(recepient);
			mailMessage.setText(text);
			mailMessage.setSubject(subject);*/
			
			javaMailSender.send(mimeMessage);
			String status = "Mail Sent Successfully....";
			return status;
		} catch(Exception e) {
			return "Error while Sending Mail";
		}
	}

	@Override
	public List<ReservationModel> getAllReservations() {
		List<ReservationModel> reservationsList = imapper.getAllReservations();
		return reservationsList;
	}

	@Override
	public List<MovieDescriptionModel> checkDescription(MovieDescriptionModel movieDescription) {
		List<MovieDescriptionModel> moviedescList = imapper.checkDescription(movieDescription);
		return moviedescList;
	}

	@Override
	public Object sendOtp(String email, int length) {
		try {

			GenerateOtp getOtp = new GenerateOtp();
			char[] otp = getOtp.generateOtp(length);
			String strOtp = String.copyValueOf(otp);
			System.out.println(strOtp);
			SimpleMailMessage mailMessage = new SimpleMailMessage();
			String recepient = email;
			System.out.println(recepient);
			String text = "Your One time Password is : " + strOtp;
			System.out.println(text);
			String subject = "OTP for Login";
			mailMessage.setFrom(sender);
			mailMessage.setTo(recepient);
			mailMessage.setText(text);
			mailMessage.setSubject(subject);
			
			javaMailSender.send(mailMessage);
			//String status = "Mail Sent Successfully....";
			String status = strOtp;
			return status;
		} catch(Exception e) {
			return "Error while Sending Mail";
		}
	}

}
