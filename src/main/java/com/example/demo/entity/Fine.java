package com.example.demo.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Fine")
@Table(name = "Fine")
public class Fine {

    @Id
    @Column(updatable = false)
    private UUID fineId;

    @OneToOne(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
    )
    @JoinColumn(
            name = "borrow_id",
            referencedColumnName = "borrowId"
    )
    private Borrow borrow;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    @Value("${some.key:false}")
    private boolean isPaid;

}
