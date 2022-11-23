package com.example.demo.controller;

import com.example.demo.entity.Reservation;
import com.example.demo.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/reserve")
    public Reservation reserveBook(@RequestParam String memberId, @RequestParam String isbn) {
        return reservationService.reserveBook(memberId, isbn);
    }

    @PutMapping("/reserve/{reserveId}")
    public Reservation updateReservationStatus(@PathVariable UUID reserveId, @RequestParam String status) {
        return reservationService.updateReservationStatus(reserveId, status);
    }

}
