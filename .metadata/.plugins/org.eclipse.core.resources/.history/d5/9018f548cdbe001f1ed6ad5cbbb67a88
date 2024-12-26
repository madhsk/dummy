package com.springboot.filmrentalstore;

import java.util.TimeZone;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class FilmrentalstoreApplication {
	private static final String ZONE_ID_INDIA = "Asia/Kolkata";
	public static void main(String[] args) {
		TimeZone.setDefault(TimeZone.getTimeZone(ZONE_ID_INDIA));
        System.out.println("Application time zone: " + TimeZone.getDefault().getID());
		SpringApplication.run(FilmrentalstoreApplication.class, args);
	}
	@Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
