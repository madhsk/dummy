package com.springboot.filmrentalstore.service;

import java.time.LocalDate;
import java.util.Map;

import com.springboot.filmrentalstore.DTO.PaymentDTO;

public interface IPaymentService {
	 PaymentDTO addPayment(PaymentDTO paymentDTO);
	   Map<LocalDate, Double> getCumulativeRevenueDatewise() ;
	     Map<LocalDate, Double> getCumulativeRevenueByStoreDatewise(Long storeId);
	     Map<String, Double> getCumulativeRevenueFilmwise() ;
		Map<String, Double> getCumulativeRevenueByFilmStorewise(Long filmId);
		Map<String, Double> getCumulativeRevenueFilmsByStore(Long storeId);
	 

}
