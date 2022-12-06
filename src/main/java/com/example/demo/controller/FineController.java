package com.example.demo.controller;

import com.example.demo.entity.Fine;
import com.example.demo.service.FineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FineController {

    @Autowired
    private FineService fineService;

    @GetMapping("/fine/{memberId}")
    public List<Fine> getAllFinesOfMember(@PathVariable String memberId) {
        return fineService.getAllFinesByMember(memberId);
    }

}
