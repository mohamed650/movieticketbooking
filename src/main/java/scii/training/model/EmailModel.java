package scii.training.model;

import lombok.Data;

@Data
public class EmailModel {
	
	private String recipient;
    private String msgBody;
    private String subject;
}
