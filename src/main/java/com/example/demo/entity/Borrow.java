package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
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
@Entity(name = "Borrow")
@Table(name = "Borrow")
public class Borrow {

    @Id
    @Column(updatable = false)
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(
            name = "uuid",
            strategy = "org.hibernate.id.UUIDGenerator",
            parameters = @Parameter(
                    name = "uuid_generator_strategy_class",
                    value = "org.hibernate.id.uuid.CustomVersionOneStrategy"
            )
    )
    @Type(type = "org.hibernate.type.UUIDCharType")
    private UUID borrowId;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "member_id",
            referencedColumnName = "memberId"
    )
    private Member member;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "book_barcode",
            referencedColumnName = "barcode"
    )
    private BookItem bookItem;

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
