package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.BookItemRepository;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final BookItemRepository bookItemRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository,
                              MemberRepository memberRepository,
                              BookRepository bookRepository,
                              BookItemRepository bookItemRepository) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.bookItemRepository = bookItemRepository;
    }

    public Reservation reserveBook(String memberId, String isbn) {
        Reservation reservation = new Reservation();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member with id " + memberId + " does not exist!"));
        if (member.isProhibited()) {
            throw new IllegalStateException("Member with id " + memberId + " is prohibited, he/she cannot borrow any book!");
        }
        reservation.setMember(member);

        Book book = bookRepository.findById(isbn)
                .orElseThrow(() -> new IllegalStateException("Book with isbn " + isbn + " does not exist!"));
        List<BookItem> bookItems = bookItemRepository.selectAvailableBookItems(isbn);
        if (!bookItems.isEmpty()) {
            throw new IllegalStateException("There are available book copies!");
        }
        reservation.setBook(book);

        reservation.setCreationDate(LocalDate.now());
        reservation.setStatus(ReservationStatus.WAITING);

        reservationRepository.save(reservation);
        return reservation;
    }

    public Reservation updateReservationStatus(UUID reserveId, String status) {
        Reservation reservation = reservationRepository.findById(reserveId)
                .orElseThrow(() -> new IllegalStateException("Reservation id " + reserveId + " does not exist!"));

        if (reservation.getStatus() == ReservationStatus.WAITING) {
            if (Objects.equals(status, "cancel")) {
                reservation.setStatus(ReservationStatus.CANCELED);
            } else if (Objects.equals(status, "completed")) {
                reservation.setStatus(ReservationStatus.COMPLETED);
            }
        } else {
            System.out.println("The reservation has been processed!");
        }

        reservationRepository.save(reservation);
        return reservation;
    }

}
