package com.diploma.chessing.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OwnerDTO {
    private String name;
    private String email;
    private String pass;
    private String job;
    private String city;
}
