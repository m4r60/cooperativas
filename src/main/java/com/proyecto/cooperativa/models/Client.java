package com.proyecto.cooperativa.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.Serializable;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client implements Serializable {

    private int clientId;
    private boolean dropOut;
}
