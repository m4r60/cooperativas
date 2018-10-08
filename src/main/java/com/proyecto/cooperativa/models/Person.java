package com.proyecto.cooperativa.models;

import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person implements Serializable {
    private int personId;
    private String cifNif;
    private String name;
    private String lastName;
    private String adress;
    private String phoneNumber;
    private String email;

}
