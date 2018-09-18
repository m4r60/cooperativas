package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDeliveryLines {

    private int deliveryNoteId;
    private int idLine;
    private String type;
    private double weight;
    private double kgPrice;
    private double totalPrice;



}
