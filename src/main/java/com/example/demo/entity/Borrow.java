package com.example.demo.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "Borrow")
@Table(name = "Borrow")
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID borrowId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "memberId"
    )
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "book_isbn",
            referencedColumnName = "isbn"
    )
    private Book book;

    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDate dateIssued;

    @Column(
            updatable = false,
            nullable = false
    )
    private LocalDate dateDueToReturn;

    private LocalDate dateReturned;

}
