package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Varieties implements Serializable {

    private String type;
    private double kgPrice;
    private double boxWeight;
    private double boxPrice;

}
