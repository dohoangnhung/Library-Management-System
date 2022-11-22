package com.example.demo.controller;

import com.example.demo.entity.Borrow;
import com.example.demo.service.BorrowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @PostMapping("/borrow")
    public Borrow borrowBook(@RequestParam String memberId, @RequestParam String barcode) {
        return borrowService.borrowBook(memberId, barcode);
    }

    @PutMapping("/borrow/{borrowId}")
    public Borrow returnBook(@PathVariable UUID borrowId) {
        return borrowService.returnBook(borrowId);
    }

}
