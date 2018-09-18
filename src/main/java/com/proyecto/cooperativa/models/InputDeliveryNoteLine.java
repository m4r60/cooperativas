package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InputDeliveryNoteLine {
    //Si pongo extends serializable me permite enviar objetos por la red
    //en bytes y luego porder deserializarlos.
    private int deliveryNoteId;
    private int lineId;
    private String type;
    private double weight;
    private double kgPrice;
    private double totalPrice;



}
