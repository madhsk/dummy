package com.springboot.filmrentalstore.service;

import com.springboot.filmrentalstore.DTO.PaymentDTO;
import com.springboot.filmrentalstore.exception.InvalidInputException;
import com.springboot.filmrentalstore.model.Payment;
import com.springboot.filmrentalstore.repo.*;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
public class PaymentService implements IPaymentService {
	@Autowired
	private PaymentRepo paymentRepo;

	@Autowired
	ModelMapper modelMapper;

	@Override
	public PaymentDTO addPayment(PaymentDTO paymentDTO) throws InvalidInputException {
	    if (paymentDTO.getAmount() <= 0) {
	        throw new InvalidInputException("Payment amount must be greater than zero.");
	    }

	    // Map PaymentDTO to Payment entity
	    Payment payment = modelMapper.map(paymentDTO, Payment.class);

	    // Save payment using paymentDAO
	    payment = paymentRepo.save(payment);

	    // Map saved Payment back to PaymentDTO and return
	    return modelMapper.map(payment, PaymentDTO.class);
	}


	@Override
	public Map<LocalDate, Double> getCumulativeRevenueDatewise() {
		List<Payment> payments = paymentRepo.findAll();
		Map<LocalDate, Double> revenueMap = new HashMap<>();

		for (Payment payment : payments) {
			LocalDate paymentDate = payment.getPaymentDate().toLocalDate();
			revenueMap.put(paymentDate, revenueMap.getOrDefault(paymentDate, 0.0) + payment.getAmount());
		}

		return revenueMap;
	}

	@Override
	public Map<LocalDate, Double> getCumulativeRevenueByStoreDatewise(Long storeId) {
		List<Payment> payments = paymentRepo.findAll();
		Map<LocalDate, Double> revenueMap = new HashMap<>();

		for (Payment payment : payments) {
			if (payment.getRental().getInventory().getStore().getStoreId().equals(storeId)) {
				LocalDate paymentDate = payment.getPaymentDate().toLocalDate();
				revenueMap.put(paymentDate, revenueMap.getOrDefault(paymentDate, 0.0) + payment.getAmount());
			}
		}
		return revenueMap;
	}

	@Override
	public Map<String, Double> getCumulativeRevenueFilmwise() {
		List<Payment> payments = paymentRepo.findAll();
		Map<String, Double> filmRevenueMap = new HashMap<>();

		for (Payment payment : payments) {
			String filmTitle = payment.getRental().getInventory().getFilm().getTitle();
			filmRevenueMap.put(filmTitle, filmRevenueMap.getOrDefault(filmTitle, 0.0) + payment.getAmount());
		}

		return filmRevenueMap;
	}

	@Override
	public Map<String, Double> getCumulativeRevenueByFilmStorewise(Long filmId) {
		List<Payment> payments = paymentRepo.findAll();
		Map<String, Double> filmStoreRevenueMap = new HashMap<>();

		for (Payment payment : payments) {
			if (payment.getRental().getInventory().getFilm().getFilmId().equals(filmId)) {
				String filmName = payment.getRental().getInventory().getFilm().getTitle(); // Get film name
				String storeAddress = payment.getRental().getInventory().getStore().getAddress().getAddress(); // Get
																												// store
																												// address

				String key = filmName + " - " + storeAddress; // Create a composite key
				filmStoreRevenueMap.put(key, filmStoreRevenueMap.getOrDefault(key, 0.0) + payment.getAmount());
			}
		}

		return filmStoreRevenueMap;
	}

	@Override
	public Map<String, Double> getCumulativeRevenueFilmsByStore(Long storeId) {
		List<Payment> payments = paymentRepo.findAll();
		Map<String, Double> filmRevenueMap = new HashMap<>();

		for (Payment payment : payments) {
			if (payment.getRental().getInventory().getStore().getStoreId().equals(storeId)) {
				String filmTitle = payment.getRental().getInventory().getFilm().getTitle();
				filmRevenueMap.put(filmTitle, filmRevenueMap.getOrDefault(filmTitle, 0.0) + payment.getAmount());
			}
		}

		return filmRevenueMap;
	}

}
