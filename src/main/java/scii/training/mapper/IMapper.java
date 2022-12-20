package scii.training.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import scii.training.model.MovieDescriptionModel;
import scii.training.model.MovieModel;
import scii.training.model.ReservationModel;
import scii.training.model.ShowTimingsModel;
import scii.training.model.TheatreModel;
import scii.training.model.TheatreMovieModel;
import scii.training.model.UserModel;

@Mapper
public interface IMapper {
	
	int insertUser(UserModel user);
	
	public List<UserModel> checkUserById(UserModel user);

	public List<UserModel> checkUser(UserModel user);
	
	public List<TheatreModel> loadTheatre(TheatreModel theatre);
	
	public List<TheatreModel> loadTheatreSeats(TheatreModel theatre);
	
	public List<ShowTimingsModel> loadTheatreShows(ShowTimingsModel theatreShows);
	
	public List<MovieModel> loadMovieDates(MovieModel movieDates);
	
	public List<TheatreMovieModel> loadTicketPrice(TheatreMovieModel ticketPrice);
	
	public List<TheatreMovieModel> loadTheatreNames(TheatreMovieModel theatreNames);
	
	public List<MovieModel> loadMovie(MovieModel movie);
	
	int bookTicket(ReservationModel ticketBooking);
	
	public List<MovieDescriptionModel> loadDescription(MovieDescriptionModel movieDescription);
	
	public List<MovieDescriptionModel> checkDescription(MovieDescriptionModel movieDescription);
	
	public List<ReservationModel> checkTransaction(ReservationModel transactions);
	
	int insertTransaction(ReservationModel inserttransaction);
	
	int updateTransaction(ReservationModel updatetransaction);
	
	public List<MovieModel> loadMovieInformation(MovieModel movieInformation);
	
	//public List<TheatreModel> loadTheatreInformation(TheatreModel theatreInformation);
	
	public List<TheatreModel> checkTheatreById(TheatreModel theatre);
	
	int insertTheatre(TheatreModel inserttheatre);
	
	public List<MovieModel> checkMovieById(MovieModel checkmovie);
	
	int insertMovie(MovieModel insertmovie);
	
	public List<TheatreMovieModel> checkShowTimings(TheatreMovieModel showtime);
	
	int insertTimings(TheatreMovieModel inserttimings);
	
	public List<TheatreMovieModel> loadMovieNames(TheatreMovieModel movieNames);
	
	int insertDescription(MovieDescriptionModel moviedescription);
	
	public List<ReservationModel> loadTicketDetails(ReservationModel ticketdetails);
	
	int deleteBooking(ReservationModel deletebooking);
	
	int updateBooking(ReservationModel updatebooking);
	
	public List<ReservationModel> checkBooking(ReservationModel checkbooking);
	
	public List<ReservationModel> getAllReservations();

}
