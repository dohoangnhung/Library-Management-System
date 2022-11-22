package com.example.demo.entity;

import com.example.demo.generator.UserIdGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "Member")
@Table(
        name = "Member",
        uniqueConstraints = @UniqueConstraint(name = "unique_email", columnNames = "email")
)
public class Member {

    @Id
    @GeneratedValue(generator = "memberid", strategy = GenerationType.SEQUENCE)
    @GenericGenerator(name = "memberid", strategy = "com.example.demo.generator.UserIdGenerator", parameters = {
            @Parameter(name = UserIdGenerator.INCREMENT_PARAM, value = "1"),
            @Parameter(name = UserIdGenerator.PREFIX_PARAMETER, value = "S"),
            @Parameter(name = UserIdGenerator.NUMBER_FORMAT_PARAMETER, value = "%03d")
    })
    @Column(updatable = false)
    private String memberId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate dob;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    @Email(message = "The valid email is a@b.c")
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    @Value("false")
    private boolean isProhibited;

}
