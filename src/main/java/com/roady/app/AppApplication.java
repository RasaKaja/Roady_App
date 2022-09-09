package com.roady.app;

import com.roady.app.entities.DriverReview;
import com.roady.app.entities.PassengerReview;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class AppApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppApplication.class, args);
	}

//	@Bean
//	@Scope(value = "prototype")
//	Rewiew getPassengerReview() {
//		return new PassengerReview();
//	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("passengerReview")
	PassengerReview getPassengerReview(){
		return new PassengerReview();
	}

	@Bean
	@Scope(value = "prototype")
	@Qualifier("driverReview")
	DriverReview getDriverReview(){
		return new DriverReview();
	}

}