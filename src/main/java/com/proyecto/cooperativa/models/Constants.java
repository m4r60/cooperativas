package com.proyecto.cooperativa.models;

import org.springframework.stereotype.Component;

public class Constants {
    //Validation string
    public static final String EMAIL_VALIDATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    public static final String PHONE_NUMBER_VALIDATION = "^(\\+34|0034|34)?[6789]\\d{8}$";
    public static final String CIF_NIF_VALIDATION = "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";


}
