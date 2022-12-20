package scii.training.model;

import lombok.Data;

@Data
public class ReservationModel {
	private int reservation_id;
	private String email_id;
	private String movie_id;
	private String movie_name;
	private String theatre_id;
	private String theatre_name;
	private String date_of_reservation;
	private String show_time;
	private String time_of_reservation;
	private int no_of_seatsbooked;
	private int total_price;
	private int no_of_availableseats;
	private String created_date;
	private String updated_date;
	private boolean ticket_exist_flg;
}
