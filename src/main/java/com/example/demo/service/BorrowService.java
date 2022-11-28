package com.example.demo.service;

import com.example.demo.entity.BookItem;
import com.example.demo.entity.BookStatus;
import com.example.demo.entity.Borrow;
import com.example.demo.entity.Member;
import com.example.demo.repository.BookItemRepository;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class BorrowService {

    private final BorrowRepository borrowRepository;
    private final MemberRepository memberRepository;
    private final BookItemRepository bookItemRepository;

    @Autowired
    public BorrowService(BorrowRepository borrowRepository,
                         MemberRepository memberRepository,
                         BookItemRepository bookItemRepository) {
        this.borrowRepository = borrowRepository;
        this.memberRepository = memberRepository;
        this.bookItemRepository = bookItemRepository;
    }

    public Borrow borrowBook(String memberId, String barcode) {
        Borrow borrow = new Borrow();

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new IllegalStateException("Member with id " + memberId + " does not exist!"));
        if (member.isProhibited()) {
            throw new IllegalStateException("Member with id " + memberId + " is prohibited, he/she cannot borrow any book!");
        }
        borrow.setMember(member);

        BookItem bookItem = bookItemRepository.findById(barcode)
                .orElseThrow(() -> new IllegalStateException("Book item with barcode " + barcode + " does not exist!"));
        bookItem.setStatus(BookStatus.BORROWED);
        bookItemRepository.save(bookItem);
        borrow.setBookItem(bookItem);

        borrow.setDateIssued(LocalDate.now());
        borrow.setDateDueToReturn(LocalDate.now().plusDays(14));

        borrowRepository.save(borrow);
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
