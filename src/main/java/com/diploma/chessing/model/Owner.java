package com.diploma.chessing.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SuperBuilder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "Owner")
@Table(name = "owner")
public class Owner extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pass;
    @Column
    private String job;

    @Column(nullable = false)
    private String city;

    /*@OneToMany(targetEntity = User.class, mappedBy = "owner")
    private List<User> users = new ArrayList<>();*/
}
