package com.diploma.chessing.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CustomerDTO {
    private String name;
    private String email;
    private String pass;
    private Integer rating;
    private boolean isRegularCustomer;
}
