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

    @PutMapping("/borrow/{barcode}")
    public Borrow returnBook(@PathVariable String barcode) {
        return borrowService.returnBook(barcode);
    }

}
