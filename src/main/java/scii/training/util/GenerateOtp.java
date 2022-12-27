package scii.training.util;

import java.util.*;
public class GenerateOtp {
	
	public char[] generateOtp(int otpLength) {
		
		String numbers = "0123456789";
		
		Random randomOtp = new Random();
		
		char[] otpArray = new char[otpLength];
			
		for(int i=0 ;i<otpLength; i++) {
			otpArray[i] = numbers.charAt(randomOtp.nextInt(numbers.length()));
		}
		return otpArray;
	}
	
}
