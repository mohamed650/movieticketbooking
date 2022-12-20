package scii.training.model;

import lombok.Data;

@Data
public class MovieModel {
	private String theatre_id;
	private String movie_id;
	private String movie_name;
	private String theatre_name;
	private String movie_language;
	private String movie_standard;
	private String movie_genre;
	private String movie_start_date;
	private String movie_end_date;
	private int ticket_price;
	private String run_time;
	private String imgfile;
}
