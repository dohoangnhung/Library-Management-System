package com.example.demo.utils;

import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@Service
public class PhoneNumberValidator implements Predicate<String> {

    @Override
    public boolean test(String phoneNumber) {
        return phoneNumber.startsWith("+84") && phoneNumber.length() == 12;
    }

}
