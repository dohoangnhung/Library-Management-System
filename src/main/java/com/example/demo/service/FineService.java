package com.example.demo.service;

import com.example.demo.entity.Borrow;
import com.example.demo.entity.Fine;
import com.example.demo.repository.BorrowRepository;
import com.example.demo.repository.FineRepository;
import com.example.demo.utils.EmailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

@Service
public class FineService {

    private final FineRepository fineRepository;
    private final BorrowRepository borrowRepository;
    private final EmailSenderService emailSenderService;

    private static final double FINE_PER_DAY_PER_ITEM = 0.75;

    @Autowired
    public FineService(FineRepository fineRepository,
                       BorrowRepository borrowRepository,
                       EmailSenderService emailSenderService) {
        this.fineRepository = fineRepository;
        this.borrowRepository = borrowRepository;
        this.emailSenderService = emailSenderService;
    }

    @Scheduled(cron = "@daily")
    public void calculateFines() {
        List<Borrow> borrows = borrowRepository.selectUnpaidAndOverdueBorrow(LocalDate.now());
        for (Borrow borrow:borrows) {
            if (fineRepository.findByBorrowId(borrow.getBorrowId()).isEmpty()) {
                Fine fine = new Fine();
                fine.setBorrow(borrow);

                long timeDelay = Duration.between(borrow.getDateDueToReturn(), LocalDate.now()).toDays();
                fine.setAmount(timeDelay * FINE_PER_DAY_PER_ITEM);
                fine.setCreationDate(LocalDate.now());

                fineRepository.save(fine);

                String memberEmail = borrow.getMember().getEmail();
                emailSenderService.sendMail(
                        memberEmail,
                        "About your fine on overdue book",
                        "Borrow id: " + borrow.getBorrowId() +
                                "\nBook title: " + borrow.getBookItem().getBook().getTitle() +
                                "\nDate issued: " + borrow.getDateIssued() +
                                "\nDue date: " + borrow.getDateDueToReturn() +
                                "\nCurrent fine: " + fine.getAmount() +
                                "\nFine will increase 0.75$ per day per item." +
                                "\nYou cannot borrow anything until the book is returned and the fine is paid."
                );
            } else {
                Fine fine = fineRepository.findByBorrowId(borrow.getBorrowId()).get();
                long timeDelay = Duration.between(borrow.getDateDueToReturn(), LocalDate.now()).toDays();
                fine.setAmount(timeDelay * FINE_PER_DAY_PER_ITEM);

                fineRepository.save(fine);
            }
        }
    }

    public List<Fine> getAllFinesByMember(String memberId) {
        return fineRepository.findAllByMemberId(memberId);
    }

}
