package scii.training.model;

import lombok.Data;

@Data
public class TheatreMovieModel {
	private String theatre_id;
	private String movie_id;
	private String theatre_name;
	private String movie_name;
	private String movie_start_date;
	private String movie_end_date;
	private int ticket_price;
	public int no_of_seatsbooked;
	public String show_timings;
}
