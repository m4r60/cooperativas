package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.stream.Stream;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputBill implements Serializable {
    private int billId;
    private String date;
    private int iva;
    private double netPrice;
    private boolean cancel;
    private String cifNif;
    private double totalPrice;

    private Stream<InputDeliveryNote> deliveryNotes;

}
