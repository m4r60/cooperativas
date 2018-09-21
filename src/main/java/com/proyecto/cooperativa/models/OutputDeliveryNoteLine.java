package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDeliveryNoteLine implements Serializable {
    private int deliveryNoteId;
    private int lineId;
    private String type;
    private double weight;
    private double kgPrice;
    private double totalPrice;



}
