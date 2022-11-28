package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

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
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = @org.hibernate.annotations.Parameter(
                    name = "uuid_generator_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID reserveId;

    @ManyToOne
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "memberId"
    )
    private Member member;

    @ManyToOne
    @JoinColumn(
            name = "book_isbn",
            referencedColumnName = "isbn"
    )
    private Book book;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

}
