package com.example.demo.entity;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
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
    private UUID fineId;

    @OneToOne
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
