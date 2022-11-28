package com.example.demo.service;

import com.example.demo.entity.BookItem;
import com.example.demo.entity.BookStatus;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.Member;
import com.example.demo.repository.BookItemRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.MemberRepository;
import com.example.demo.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookItemRepository bookItemRepository;
    private final ReservationRepository reservationRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository,
                         MemberRepository memberRepository,
                         BookItemRepository bookItemRepository,
                         ReservationRepository reservationRepository) {
        this.borrowRepository = borrowRepository;
        this.memberRepository = memberRepository;
        this.bookItemRepository = bookItemRepository;
        this.reservationRepository = reservationRepository;
    }

    public Borrow borrowBook(String memberId, String barcode) {
        Borrow borrow = new Borrow();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member with id " + memberId + " does not exist!"));

        // check if member is prohibited
        if (member.isProhibited()) {
            throw new IllegalStateException("Member with id " + memberId + " is prohibited, he/she cannot borrow any book!");
        }

        // TODO: check if member has any unpaid fines

        // check the number of books issued to the member
        int issuedBooks = borrowRepository.numberOfIssuedBookByMemberId(memberId);
        if (issuedBooks > 5) {
            throw new IllegalStateException("The number of issued books to the member " + memberId + " exceeds 5 books!");
        }
        borrow.setMember(member);

        // check if the book has been reserved by any other members
        String bookIsbn = bookItemRepository.selectIsbnOfBookItem(barcode);
        int availableBooks = bookItemRepository.selectAvailableBookItems(bookIsbn).size();
        int reservedBooks = reservationRepository.selectReservedBooks(bookIsbn).size();
        if (availableBooks <= reservedBooks) {
            throw new IllegalStateException("All the book items have been reserved!");
        }

        BookItem bookItem = bookItemRepository.findById(barcode)
                .orElseThrow(() -> new IllegalStateException("Book item with barcode " + barcode + " does not exist!"));
        bookItem.setStatus(BookStatus.BORROWED);
        bookItemRepository.save(bookItem);
        borrow.setBookItem(bookItem);

        borrow.setDateIssued(LocalDate.now());
        borrow.setDateDueToReturn(LocalDate.now().plusDays(14));

        borrowRepository.save(borrow);

        Optional<UUID> waitingReservationId = reservationRepository.selectIdOfWaitingReservation(bookIsbn, memberId);
        waitingReservationId.ifPresent(reservationRepository::markCompletedReservation);

        return borrow;
    }

    public Borrow returnBook(String barcode) {
        Borrow borrow = borrowRepository.selectBorrowedBookItemByBarcode(barcode)
                .orElseThrow(() -> new IllegalStateException("The check-out with book item " + barcode + " does not exist!"));
        borrow.setDateReturned(LocalDate.now());
        borrowRepository.save(borrow);

        BookItem bookItem = borrow.getBookItem();
        bookItem.setStatus(BookStatus.AVAILABLE);
        bookItemRepository.save(bookItem);

        if (borrow.getDateReturned().isAfter(borrow.getDateDueToReturn())) {
            System.out.println("Overdue book!");
        }
        return borrow;
    }

}
