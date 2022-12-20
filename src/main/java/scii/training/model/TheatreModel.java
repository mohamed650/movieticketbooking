package scii.training.model;

import lombok.Data;

@Data
public class TheatreModel {
	private String theatre_id;
	private String theatre_name;
	private String theatre_address;
	private int maximum_seats;
	private String theatre_screens;
	private String show_timings;
	
}
