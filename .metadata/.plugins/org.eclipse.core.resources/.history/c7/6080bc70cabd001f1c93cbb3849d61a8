package com.springboot.filmrentalstore.controller;

import com.springboot.filmrentalstore.model.Staff;
import com.springboot.filmrentalstore.model.Address;
import com.springboot.filmrentalstore.service.StaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffController {

    @Autowired
    private StaffService staffService;

    // Add a new Staff
    @PostMapping("/post")
    public ResponseEntity<String> addStaff(@RequestBody Staff staff) {
        String message = staffService.addStaff(staff);
        return ResponseEntity.ok(message);
    }

    // Search Staff by Last Name
    @GetMapping("/lastname/{ln}")
    public ResponseEntity<List<Staff>> getStaffByLastName(@PathVariable String ln) {
        List<Staff> staffList = staffService.getStaffByLastName(ln);
        return ResponseEntity.ok(staffList);
    }

    // Search Staff by First Name
    @GetMapping("/firstname/{fn}")
    public ResponseEntity<List<Staff>> getStaffByFirstName(@PathVariable String fn) {
        List<Staff> staffList = staffService.getStaffByFirstName(fn);
        return ResponseEntity.ok(staffList);
    }

    // Search Staff by Email
    @GetMapping("/email/{email}")
    public ResponseEntity<Staff> getStaffByEmail(@PathVariable String email) {
        Staff staff = staffService.getStaffByEmail(email);
        return ResponseEntity.ok(staff);
    }

    // Assign Address to a Staff
    @PutMapping("/{id}/address")
    public ResponseEntity<Staff> assignAddressToStaff(@PathVariable int id, @RequestBody Address address) {
        Staff updatedStaff = staffService.assignAddressToStaff(id, address);
        return ResponseEntity.ok(updatedStaff);
    }

    // Search Staff by City
    @GetMapping("/city/{city}")
    public ResponseEntity<List<Staff>> getStaffByCity(@PathVariable String city) {
        List<Staff> staffList = staffService.getStaffByCity(city);
        return ResponseEntity.ok(staffList);
    }

    // Search Staff by Country
    @GetMapping("/country/{country}")
    public ResponseEntity<List<Staff>> getStaffByCountry(@PathVariable String country) {
        List<Staff> staffList = staffService.getStaffByCountry(country);
        return ResponseEntity.ok(staffList);
    }

    // Search Staff by Phone Number
    @GetMapping("/phone/{phone}")
    public ResponseEntity<Staff> getStaffByPhone(@PathVariable String phone) {
        Staff staff = staffService.getStaffByPhone(phone);
        return ResponseEntity.ok(staff);
    }

    // Update First Name of a Staff
    @PutMapping("/update/fn/{id}")
    public ResponseEntity<Staff> updateStaffFirstName(@PathVariable int id, @PathVariable String fn) {
        Staff updatedStaff = staffService.updateStaffFirstName(id, fn);
        return ResponseEntity.ok(updatedStaff);
    }

    // Update Last Name of a Staff
    @PutMapping("/update/ln/{id}")
    public ResponseEntity<Staff> updateStaffLastName(@PathVariable int id, @PathVariable String ln) {
        Staff updatedStaff = staffService.updateStaffLastName(id, ln);
        return ResponseEntity.ok(updatedStaff);
    }

    // Update Email of a Staff
    @PutMapping("/update/email/{id}")
    public ResponseEntity<Staff> updateStaffEmail(@PathVariable int id, @PathVariable String email) {
        Staff updatedStaff = staffService.updateStaffEmail(id, email);
        return ResponseEntity.ok(updatedStaff);
    }

    // Assign Store to a Staff
    @PutMapping("/update/store/{id}")
    public ResponseEntity<Staff> updateStaffStore(@PathVariable int id, @RequestBody int storeId) {
        Staff updatedStaff = staffService.updateStaffStore(id, storeId);
        return ResponseEntity.ok(updatedStaff);
    }

}
