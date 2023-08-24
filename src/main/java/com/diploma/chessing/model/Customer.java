package com.diploma.chessing.model;

import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;
import java.io.Serializable;

@SuperBuilder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "Customer")
@Table(name = "customer")
public class Customer extends BaseEntity implements Serializable {
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String pass;
    @Column(nullable = false)
    private Integer rating;
    @Column()
    private boolean isRegularCustomer;
    @ManyToOne(cascade = CascadeType.ALL)
    private Owner owner;

   /* public String getName() {
        return getName();
    }

    public void setName(String name) {
        setName(name);
    }

    public String getEmail() {
        return getEmail();
    }

    public void setEmail(String email) {
        setEmail(email);
    }

    public String getPass() {
        return getPass();
    }

    public void setPass(String pass) {
        setPass(pass);
    }*/
}