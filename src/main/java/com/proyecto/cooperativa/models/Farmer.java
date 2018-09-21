package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Farmer extends Person implements Serializable {
    private int farmerId;
    private boolean dropOut;
}
