package com.example.demo.configuration;

import com.example.demo.entity.*;
import com.example.demo.repository.BookItemRepository;
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
    CommandLineRunner commandLineRunner(MemberRepository memberRepository,
                                        BookRepository bookRepository,
                                        BookItemRepository bookItemRepository) {
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
                    .numOfCopies(3)
                    .author("R.J. Palacio")
                    .price(8.63)
                    .publisher("Penguin Random House UK")
                    .category(BookCategory.LITERATURE_AND_FICTION)
                    .numOfPages(320)
                    .shelfNum("100.1 PAL")
                    .build();

            Book book2 = Book.builder()
                    .isbn("9780385754729")
                    .title("The Book Thief")
                    .numOfCopies(3)
                    .author("Markus Zusak")
                    .price(10.15)
                    .publisher("Penguin Random House US")
                    .category(BookCategory.LITERATURE_AND_FICTION)
                    .numOfPages(592)
                    .shelfNum("102.5 ZUS")
                    .build();

            bookRepository.saveAll(List.of(book1, book2));

            BookItem item1 = BookItem.builder()
                    .barcode("1111")
                    .book(book1)
                    .status(BookStatus.AVAILABLE)
                    .build();

            BookItem item2 = BookItem.builder()
                    .barcode("2222")
                    .book(book1)
                    .status(BookStatus.AVAILABLE)
                    .build();

            BookItem item3 = BookItem.builder()
                    .barcode("3333")
                    .book(book1)
                    .status(BookStatus.AVAILABLE)
                    .build();

            BookItem item4 = BookItem.builder()
                    .barcode("4444")
                    .book(book2)
                    .status(BookStatus.AVAILABLE)
                    .build();

            BookItem item5 = BookItem.builder()
                    .barcode("5555")
                    .book(book2)
                    .status(BookStatus.AVAILABLE)
                    .build();

            BookItem item6 = BookItem.builder()
                    .barcode("6666")
                    .book(book2)
                    .status(BookStatus.AVAILABLE)
                    .build();

            bookItemRepository.saveAll(List.of(item1, item2, item3, item4, item5, item6));
        };
    }

}
