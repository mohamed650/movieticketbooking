package scii.training.controller;


import java.io.ByteArrayInputStream;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.collections4.MultiMap;
import org.apache.commons.collections4.map.MultiValueMap;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import scii.training.model.MovieDescriptionModel;
import scii.training.model.MovieModel;
import scii.training.model.ReservationModel;
import scii.training.model.ShowTimingsModel;
import scii.training.model.TheatreModel;
import scii.training.model.TheatreMovieModel;
import scii.training.model.UserModel;
import scii.training.service.IService;
import scii.training.util.ExportExcel;
import scii.training.util.GeneratedPdfReport;
import scii.training.util.InvoiceReport;

@RestController
public class MainController {
	
	private static final Logger logger = LogManager.getLogger(MainController.class.getName());
	
	@Autowired
	IService iservice;
	
	@GetMapping("/")
	public ModelAndView login() throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	@GetMapping("/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("Registration");
		return modelAndView;
	}
	
	@RequestMapping("/loginUser")
	public @ResponseBody Object loginUser(HttpSession httpSession, @ModelAttribute ("user") UserModel user) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		UserModel loginUser = user;
		String email = loginUser.getEmail_id();
		String password = loginUser.getPassword();
		loginUser.setEmail_id(email);
		loginUser.setPassword(password);
		httpSession.setAttribute("email", email);
		List<UserModel> loginList = iservice.checkUser(loginUser);
		httpSession.setAttribute("username", loginList.get(0).getUser_name());
		httpSession.setAttribute("contactno", loginList.get(0).getContactno());
		if(loginList.size() > 0) {
			map.put("MESSAGE", "Success");
			logger.log(Level.INFO, email+ " Logged In...");
		}else {
			map.put("MESSAGE", "USERNOTEXIST");
			logger.log(Level.WARN, "Invalid Credentials");
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping("/user")
	public @ResponseBody Object user(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("user");
		modelAndView.addObject(httpSession.getAttribute("email"));
		modelAndView.addObject("adminuser", "USER");
		return modelAndView;
	}
	
	@GetMapping("/loginAdmin")
	public @ResponseBody Object loginAdmin(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("admin");
		httpSession.setAttribute("email", "Admin");
		logger.log(Level.INFO, "Admin Logged In...");
		return modelAndView;
	}
	
	@GetMapping("/viewTicket")
	public @ResponseBody Object viewUser(HttpSession httpSession) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("UserView");
		modelAndView.addObject(httpSession.getAttribute("email"));
		modelAndView.addObject("adminuser", "USER");
		return modelAndView;
	}
	
	@RequestMapping("/insertUser")
	public @ResponseBody Object insertUser(@ModelAttribute ("user") UserModel user) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		UserModel insertDetails = user;
		List<UserModel> checkList = iservice.checkUserById(insertDetails);
		if(checkList.size() > 0) {
			map.put("MESSAGE", "USEREXIST");
			logger.log(Level.WARN, "User Already Exist...");
		}else{
			int status = iservice.insertUser(user);
			if(status == 1) {
				map.put("MESSAGE", "Success");
				logger.log(Level.INFO, "User Registered...");
			}else {
				map.put("MESSAGE", "Failure");
				logger.log(Level.WARN, "Registration Failed...");
			}
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping("/loadTheatre")
	public @ResponseBody void loadTheatre(HttpServletResponse response)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		TheatreModel theatre = new TheatreModel();
		List<TheatreModel> loadTheatreList = iservice.loadTheatre(theatre);
		for(int i=0; i<loadTheatreList.size(); i++) {
			map.put(loadTheatreList.get(i).getTheatre_id(), loadTheatreList.get(i).getTheatre_name());
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/loadTheatreSeats")
	public @ResponseBody void loadTheatreSeats(HttpServletResponse response, HttpSession httpSession, @ModelAttribute ("theatre") TheatreModel theatre, @ModelAttribute ("seats") ReservationModel seats)throws Exception{
		Map<String, Integer> map = new HashMap<String, Integer>();
		TheatreModel theatreSeats = theatre;
		ReservationModel reservationSeats = seats;
		List<ReservationModel> seatList = iservice.checkTransaction(reservationSeats);
		if(seatList.size() > 0) {
			for(int i=0; i<seatList.size(); i++) {
				map.put(seatList.get(i).getTheatre_id(), seatList.get(i).getNo_of_availableseats());
			}
		}else {
			List<TheatreModel> loadTheatreList = iservice.loadTheatreSeats(theatreSeats);
			for(int i=0; i<loadTheatreList.size(); i++) {
				map.put(loadTheatreList.get(i).getTheatre_id(), loadTheatreList.get(i).getMaximum_seats());
				httpSession.setAttribute("MAXSEATS", loadTheatreList.get(i).getMaximum_seats());
			}
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/loadTheatreShows")
	public @ResponseBody void loadTheatreShows(HttpServletResponse response, @ModelAttribute ("theatreShows") ShowTimingsModel theatreShows)throws Exception{
		List<String> showList = new ArrayList<String>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		ShowTimingsModel movies = theatreShows;
		List<ShowTimingsModel> loadShowList = iservice.loadTheatreShows(movies);
		for(int i=0; i< loadShowList.size(); i++) {
			showList.add(loadShowList.get(i).getShow_timings());
		}
		map.put(loadShowList.get(0).getTheatre_id(), showList);
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/loadTheatreNames")
	public @ResponseBody void loadTheatreNames(HttpServletResponse response, @ModelAttribute ("theatreNames") TheatreMovieModel theatreNames)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		TheatreMovieModel theatres = theatreNames;
		List<TheatreMovieModel> loadTheatreList = iservice.loadTheatreNames(theatres);
		for(int i=0; i<loadTheatreList.size(); i++) {
			map.put(loadTheatreList.get(i).getTheatre_id(), loadTheatreList.get(i).getTheatre_name());
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/loadMovieNames")
	public @ResponseBody void loadMovieNames(HttpServletResponse response, @ModelAttribute ("movieNames") TheatreMovieModel movieNames)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		TheatreMovieModel movies = movieNames;
		List<TheatreMovieModel> loadMovieList = iservice.loadMovieNames(movies);
		for(int i=0; i<loadMovieList.size(); i++) {
			map.put(loadMovieList.get(i).getMovie_id(), loadMovieList.get(i).getMovie_name());
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/loadMovie")
	public @ResponseBody void loadMovie(HttpServletResponse response)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		MovieModel movie = new MovieModel();
		List<MovieModel> loadMovieList = iservice.loadMovie(movie);
		for(int i=0; i<loadMovieList.size(); i++) {
			map.put(loadMovieList.get(i).getMovie_id(), loadMovieList.get(i).getMovie_name());
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/loadMovieDates")
	public @ResponseBody void loadMovieDates(HttpServletResponse response, @ModelAttribute ("movieDates") MovieModel movieDates)throws Exception{
		Map<Object, String> map = new HashMap<Object, String>();
		MovieModel movies = movieDates;
		List<MovieModel> loadDatesList = iservice.loadMovieDates(movies);
		String inputDate = loadDatesList.get(0).getMovie_start_date();
		LocalDate today = LocalDate.now();
		LocalDate parseDate = LocalDate.parse(inputDate);
		long difference = ChronoUnit.DAYS.between(parseDate, today);
		if(difference <= 0) {
			map.put(loadDatesList.get(0).getMovie_start_date(), loadDatesList.get(0).getMovie_end_date());
		} else{
			map.put(today, loadDatesList.get(0).getMovie_end_date());
		}
		
		/*for(int i=0; i<loadDatesList.size(); i++) {
			map.put(loadDatesList.get(i).getMovie_start_date(), loadDatesList.get(i).getMovie_end_date());
		}*/
		Gson gson = new Gson();
		String json = gson.toJson(map);
		json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	
	@RequestMapping("/loadTicketPrice")
	public @ResponseBody void loadTicketPrice(HttpServletResponse response, @ModelAttribute ("bookTicket") TheatreMovieModel ticketPrice)throws Exception{
		TheatreMovieModel tickets = ticketPrice;
		List<TheatreMovieModel> ticketList = iservice.loadTicketPrice(tickets);
		int totalPrice = tickets.getNo_of_seatsbooked() * ticketList.get(0).getTicket_price();
		response.getWriter().print(totalPrice);
	}
	
	@RequestMapping("/loadDescription")
	public @ResponseBody void loadDescription(HttpServletResponse response, @ModelAttribute ("movieDescription") MovieDescriptionModel movieDescription)throws Exception{
		List<String> showList = new ArrayList<String>();
		Map<String, List<String>> map = new HashMap<String, List<String>>();
		MovieDescriptionModel moviesDesc = movieDescription;
		List<MovieDescriptionModel> loadDescList = iservice.loadDescription(moviesDesc);
		for(int i=0; i<loadDescList.size(); i++) {
			showList.add(loadDescList.get(i).getMoviename());
			showList.add(loadDescList.get(i).getMoviedirector());
			showList.add(loadDescList.get(i).getMovieproducer());
			showList.add(loadDescList.get(i).getMovieactor());
			showList.add(loadDescList.get(i).getMovieactress());
		}
		for(int i=0; i<loadDescList.size(); i++) {
			map.put(loadDescList.get(i).getMovieid(), showList);
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		//json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping("/bookTicket")
	public @ResponseBody Object bookTicket(@ModelAttribute ("ticketBooking") ReservationModel ticketBooking, HttpSession httpSession)throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		ReservationModel ticketBook = ticketBooking;
		httpSession.setAttribute("moviename", ticketBook.getMovie_name());
		httpSession.setAttribute("theatrename", ticketBook.getTheatre_name());
		httpSession.setAttribute("dateofreservation", ticketBook.getDate_of_reservation());
		httpSession.setAttribute("showtime", ticketBook.getShow_time());
		httpSession.setAttribute("nofofseatsbooked", ticketBook.getNo_of_seatsbooked());
		httpSession.setAttribute("totalprice", ticketBook.getTotal_price());
		LocalDateTime currentTime = LocalDateTime.now();
		String showtime = ticketBook.getShow_time();
		String showdate = ticketBook.getDate_of_reservation();
		LocalTime parseTime = LocalTime.parse(showtime);
		LocalDate parseDate = LocalDate.parse(showdate);
		LocalDateTime showdatetime = LocalDateTime.of(parseDate, parseTime);
		long diff = currentTime.until(showdatetime, ChronoUnit.HOURS);
		if(diff >= 1) {
			int ticketStatus = 0;
			int availableStatus = 0;
			List<ReservationModel> transactionList = iservice.checkTransaction(ticketBook);
			if(transactionList.size() > 0) {
				ticketStatus = iservice.bookTicket(ticketBook);
				int availseats = transactionList.get(0).getNo_of_availableseats();
				int remseats = availseats - ticketBook.getNo_of_seatsbooked();
				int bookedseats = transactionList.get(0).getNo_of_seatsbooked() + ticketBook.getNo_of_seatsbooked();
				ticketBook.setNo_of_availableseats(remseats);
				ticketBook.setNo_of_seatsbooked(bookedseats);
				availableStatus = iservice.updateTransaction(ticketBook);
				if(ticketStatus == 1 && availableStatus == 1) {
					map.put("MESSAGE", "Success");
				}else {
					map.put("MESSAGE", "Failure");
				}
			}else {
				ticketStatus = iservice.bookTicket(ticketBook);
				int maxSeats = (int) httpSession.getAttribute("MAXSEATS");
				int availableSeats = maxSeats - ticketBook.getNo_of_seatsbooked();
				ticketBook.setNo_of_availableseats(availableSeats);
				availableStatus = iservice.insertTransaction(ticketBook);
				if(ticketStatus == 1 && availableStatus == 1) {
					map.put("MESSAGE", "Success");
				}else {
					map.put("MESSAGE", "Failure");
				}
			}
		}else {
			map.put("MESSAGE", "Show Started");
		}
		return mapper.writeValueAsString(map);
	}
	
	/*@RequestMapping(value="/loadMovieInformation", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void loadMovieInformation(HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		MovieModel movieInformation = new MovieModel();
		List<MovieModel> movieInformationList = iservice.loadMovieInformation(movieInformation);
		if(movieInformationList.size() > 0) {
			for(int i=0; i<movieInformationList.size(); i++) {
				map.put(movieInformationList.get(i).getImgfile(), movieInformationList.get(i).getMovie_name());
			}
		}else {
			map.put("MESSAGE", "Failed");
		}
		Gson gson = new Gson();
		String json = gson.toJson(map);
		//json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}*/
	
	@SuppressWarnings("deprecation")
	@RequestMapping(value="/loadMovieInformation", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody void loadMovieInformation(HttpServletResponse response) throws Exception {
		@SuppressWarnings("deprecation")
		MultiMap<String, String> multiMap = new MultiValueMap<String, String>();
		MovieModel movieInformation = new MovieModel();
		List<MovieModel> movieInformationList = iservice.loadMovieInformation(movieInformation);
		if(movieInformationList.size() > 0) {
			for(int i=0; i<movieInformationList.size(); i++) {
				multiMap.put(movieInformationList.get(i).getImgfile(), movieInformationList.get(i).getMovie_name());
				multiMap.put(movieInformationList.get(i).getImgfile(), movieInformationList.get(i).getTheatre_name());
			}
		}else {
			multiMap.put("MESSAGE", "Failed");
		}
		Gson gson = new Gson();
		String json = gson.toJson(multiMap);
		System.out.println(json);
		//json = json.replaceAll("\\s", "");
		response.getWriter().print(json);
	}
	
	@RequestMapping(value="/loadTicketDetails", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object loadTicketDetails(HttpSession httpSession, @ModelAttribute ("ticketdetails") ReservationModel ticketdetails) throws Exception {
		String email = (String) httpSession.getAttribute("email");
		ReservationModel ticket = ticketdetails;
		ticket.setEmail_id(email);
		List<ReservationModel> ticketList = iservice.loadTicketDetails(ticket);
		return ticketList;
	}
	
	@RequestMapping(value="/insertTheatre", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object insertTheatre(@ModelAttribute ("inserttheatre") TheatreModel inserttheatre) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		TheatreModel theatre = inserttheatre;
		List<TheatreModel> theatreList = iservice.checkTheatreById(inserttheatre);
		if(theatreList.size() > 0) {
			map.put("MESSAGE", "EXIST");
		}else {
			int status = iservice.insertTheatre(theatre);
			if(status == 1) {
				map.put("MESSAGE", "Success");
			}else {
				map.put("MESSAGE", "Failed");
			}
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/insertMovie", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object insertMovie(@ModelAttribute ("insertmovie") MovieModel insertmovie) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		MovieModel movie = insertmovie;
		List<MovieModel> movieList = iservice.checkMovieById(insertmovie);
		if(movieList.size() > 0) {
			map.put("MESSAGE", "EXIST");
		}else {
			movie.setImgfile("/images/"+movie.getImgfile());
			int status = iservice.insertMovie(movie);
			if(status == 1) {
				map.put("MESSAGE", "Success");
			}else {
				map.put("MESSAGE", "Failed");
			}
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/insertTimings", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object insertTimings(@ModelAttribute ("inserttimings") TheatreMovieModel inserttimings) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		TheatreMovieModel timings = inserttimings;
		List<TheatreMovieModel> showTimeList = iservice.checkShowTimings(inserttimings);
		if(showTimeList.size() > 0) {
			map.put("MESSAGE", "EXIST");
		}else {
			int status = iservice.insertTimings(timings);
			if(status == 1) {
				map.put("MESSAGE", "Success");
			}else {
				map.put("MESSAGE", "Failed");
			}
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/insertDescription", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object insertDescription(@ModelAttribute ("insertdescription") MovieDescriptionModel insertdescription) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		MovieDescriptionModel description = insertdescription;
		List<MovieDescriptionModel> showTimeList = iservice.checkDescription(insertdescription);
		if(showTimeList.size() > 0) {
			map.put("MESSAGE", "EXIST");
		}else {
			int status = iservice.insertDescription(description);
			if(status == 1) {
				map.put("MESSAGE", "Success");
			}else {
				map.put("MESSAGE", "Failed");
			}
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/deleteBooking", method= {RequestMethod.GET, RequestMethod.POST})
	public @ResponseBody Object deleteBooking(@ModelAttribute ("deletebooking") ReservationModel deletebooking) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		ObjectMapper mapper = new ObjectMapper();
		ReservationModel delete = deletebooking;
		List<ReservationModel> transactionList = iservice.checkBooking(delete);
		System.out.println("Size: "+ transactionList.size());
		LocalDateTime currentTime = LocalDateTime.now();
		String showtime = delete.getShow_time();
		String showdate = delete.getDate_of_reservation();
		LocalTime parseTime = LocalTime.parse(showtime);
		LocalDate parseDate = LocalDate.parse(showdate);
		LocalDateTime showdatetime = LocalDateTime.of(parseDate, parseTime);
		long diff = currentTime.until(showdatetime, ChronoUnit.MINUTES);
		if(diff > 30) {
			if(transactionList.size() > 0) {
				delete.setTicket_exist_flg(false);
				int status = iservice.deleteBooking(delete);
				int availseats = transactionList.get(0).getNo_of_availableseats();
				int remseats = availseats + delete.getNo_of_seatsbooked();
				int bookedseats = transactionList.get(0).getNo_of_seatsbooked() - delete.getNo_of_seatsbooked();
				delete.setNo_of_availableseats(remseats);
				delete.setNo_of_seatsbooked(bookedseats);
				int updateStatus = iservice.updateBooking(delete);
				if(status == 1 && updateStatus == 1) {
					map.put("MESSAGE", "Success");
				}else {
					map.put("MESSAGE", "Failed");
				}
			}else {
				map.put("MESSAGE", "NOTEXIST");
			}
		}else {
			map.put("MESSAGE", "ShowBegins");
		}
		
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping("/checkSession")
	public @ResponseBody Object checkSession (HttpServletRequest request) throws Exception  {
		Map<String, String> map = new HashMap<String, String>();
		System.out.println("Before:" + request.getSession(false));
		HttpSession	httpSession = request.getSession(false);
		if(httpSession == null) {
			System.out.println("After:" + request.getSession(false));
			map.put("MESSAGE", "Expired");
		}else {
			map.put("MESSAGE", "ALIVE"); 
		}
		System.out.println(map);
		return map;
	}
	
	@RequestMapping("/logout")
	public Object logout(HttpServletRequest request, HttpSession httpSession) throws Exception{
		ModelAndView modelAndView = new ModelAndView();
		String email = (String) httpSession.getAttribute("email");
		System.out.println("Before:"+ request.getSession(false));
		logger.log(Level.INFO, email+" Logged Out");
		httpSession.invalidate();
		System.out.println("After:"+ request.getSession(false));
		modelAndView.setViewName("Login");
		return modelAndView;
	}
	
	@RequestMapping("/sendMail")
	public Object sendMail(String email, String path, HttpSession httpSession, HttpServletResponse response) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		InvoiceReport invoiceReport = new InvoiceReport();
		ObjectMapper mapper = new ObjectMapper();
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		String currentDate = formatter.format(date);
		email = (String) httpSession.getAttribute("email");
		String name = (String) httpSession.getAttribute("username");
		String contactno = (String) httpSession.getAttribute("contactno");
		String movie_name = (String) httpSession.getAttribute("moviename");
		String theatre_name = (String) httpSession.getAttribute("theatrename");
		String date_of_reservation = (String) httpSession.getAttribute("dateofreservation");
		String show_time = (String) httpSession.getAttribute("showtime");
		int no_of_seatsbooked = (int) httpSession.getAttribute("nofofseatsbooked");
		int total_price = (int) httpSession.getAttribute("totalprice");
		path = invoiceReport.invoice(name, email, contactno, currentDate,  movie_name, theatre_name, 
				date_of_reservation, show_time, no_of_seatsbooked, total_price);
		String status = iservice.sendMail(email, path);
		logger.log(Level.INFO, status);
		if(status == "Mail Sent Successfully....") {
			map.put("MESSAGE", "Success");
		}else {
			map.put("MESSAGE", "Failed");
		}
		return mapper.writeValueAsString(map);
	}
	
	@RequestMapping(value="/pdfReport", produces = MediaType.APPLICATION_PDF_VALUE)
	public ResponseEntity<InputStreamResource> bookingReport(){
		var reservations = (List<ReservationModel>) iservice.getAllReservations();
		ByteArrayInputStream bis = GeneratedPdfReport.bookingReport(reservations);
		
		var headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=reservations.pdf");
        
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
	}
	
	@RequestMapping("/excelGenerate")
	public void excelGenerate(HttpServletResponse response) throws Exception {
		response.setContentType("application/octet-stream");
		SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());
		String headerKey = "Content-Disposition";
		String headerValue = "attachement; filename=reservations_"+ currentDateTime + ".xlsx";
		response.setHeader(headerKey, headerValue);
		List<ReservationModel> reservations = iservice.getAllReservations();
		ExportExcel excelExport = new ExportExcel(reservations);
		excelExport.export(response);
	}
	
}
