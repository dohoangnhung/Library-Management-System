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
@Entity(name = "Notification")
@Table(name = "Notification")
public class Notification {

    @Id
    @Column(updatable = false)
    private UUID notificationId;

    @Column(nullable = false)
    private UUID issueId;

    @Column(nullable = false)
    private LocalDate creationDate;

    @Column(nullable = false)
    private String content;

}
