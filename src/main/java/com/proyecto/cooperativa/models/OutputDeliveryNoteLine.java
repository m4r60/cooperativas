package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OutputDeliveryNoteLine {
    private int deliveryNoteId;
    private int lineId;
    private String type;
    private double weight;
    private double kgPrice;
    private double totalPrice;



}
