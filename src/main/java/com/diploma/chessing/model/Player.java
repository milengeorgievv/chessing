package com.diploma.chessing.model;

import com.diploma.chessing.enumeration.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table
public class Player {
    @Id
    @SequenceGenerator(
            name = "player_sequence",
            sequenceName = "player_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "player_sequence"
    )
    private Long id;
    private String name;
    private String email;
    private String password;
    private Date dateOfBirth;
    private Integer rating;
    private LocalDate dateOfRegistration;
    private Status status;

    public Player(String name, String email, String password, Date dateOfBirth) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.dateOfBirth = dateOfBirth;
        this.rating = 800;
        this.status = Status.ONLINE;
        this.dateOfRegistration = LocalDate.now();
    }

}
