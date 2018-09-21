package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputDeliveryNote implements Serializable {
    private int id;
    private int partnerId;
    private String date;
    private int billId;

}
