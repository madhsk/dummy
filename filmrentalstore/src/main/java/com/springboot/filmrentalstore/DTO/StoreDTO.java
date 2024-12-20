package com.springboot.filmrentalstore.DTO;

import java.time.LocalDateTime;
import java.util.List;
import com.springboot.filmrentalstore.model.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;

public class StoreDTO {

    @NotNull(message = "Store ID Is Required")
    private Long storeId;

	@NotNull(message = "Address Is Required")
	@Valid
    private Address address;
	
	@NotNull(message = "Last Update Time Is Required")
	@PastOrPresent(message = "Last update must be in the past or present")
    private LocalDateTime lastUpdate;
	
	@Valid
    private List<Customer> customers;
	
	@Valid
    private List<Staff> staff_list;

    
}