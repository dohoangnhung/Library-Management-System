package com.example.demo.entity;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity(name = "BookItem")
@Table(name = "BookItem")
public class BookItem {

    @Id
    @Column(updatable = false)
    private String barcode;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "isbn",
            referencedColumnName = "isbn"
    )
    private Book book;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BookStatus status;

}
