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
@Entity(name = "Reservation")
@Table(name = "Reservation")
public class Reservation {

    @Id
    @Column(updatable = false)
    private UUID reserveId;

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

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private ReservationStatus status;

}
