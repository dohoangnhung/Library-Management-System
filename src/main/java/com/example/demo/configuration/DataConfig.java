package com.example.demo.configuration;

import com.example.demo.entity.Book;
import com.example.demo.entity.Member;
import com.example.demo.repository.BookRepository;
import com.example.demo.repository.MemberRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    CommandLineRunner commandLineRunner(MemberRepository memberRepository, BookRepository bookRepository) {
        return args -> {
            Member nhung = Member.builder()
                    .name("Nhung")
                    .dob(LocalDate.of(2001,3,5))
                    .phone("0123456789")
                    .email("nhung@gmail.com")
                    .address("284 3/2 street")
                    .build();

            Member sam = Member.builder()
                    .name("Sam")
                    .dob(LocalDate.of(1992,10,12))
                    .phone("0123456788")
                    .email("sam@gmail.com")
                    .address("284 3/2 street")
                    .build();

            memberRepository.saveAll(List.of(nhung, sam));

            Book book1 = Book.builder()
                    .isbn("9780552565974")
                    .title("Wonder")
                    .numOfCopies(5)
                    .author("R.J. Palacio")
                    .price(8.63)
                    .publisher("Penguin Random House UK")
                    .genre("Children's novel")
                    .availCopyNum(5)
                    .build();

            Book book2 = Book.builder()
                    .isbn("9780385754729")
                    .title("The Book Thief")
                    .numOfCopies(6)
                    .author("Markus Zusak")
                    .price(10.15)
                    .publisher("Penguin Random House US")
                    .genre("Historical fiction")
                    .availCopyNum(6)
                    .build();

            bookRepository.saveAll(List.of(book1, book2));
        };
    }

}
