package com.proyecto.cooperativa.models;

public class Constants {


    //Validation string
    public static final String EMAIL_VALIDATION = "^([\\w-\\.]+){1,64}@([\\w&&[^_]]+){2,255}.[a-z]{2,}$";
    public static final String PHONE_NUMBER_VALIDATION = "^(\\+34|0034|34)?[6789]\\d{8}$";
    public static final String CIF_NIF_VALIDATION = "(\\d{1,8})([TRWAGMYFPDXBNJZSQVHLCKEtrwagmyfpdxbnjzsqvhlcke])";

    //Query constants
    public static final String PERSONAS= " PERSONAS ";
    public static final String AGRICULTORES= " AGRICULTORES ";
    public static final String SELECT = "SELECT ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String LIKE = " LIKE ?";
    public static final String OR = " OR ";
    public static final String PERCENT = "%";
    public static final String COMMA_SEPARATOR = ", ";
    public static final String INSERT_INTO = "INSERT INTO ";
    public static final String VALUES = " ) VALUES (";
    public static final String QUESTION_MARK = "?";
    public static final String INITIAL_PARENTHESIS = " (";
    public static final String UPDATE = "UPDATE ";
    public static final String SET = " SET ";
    public static final String EQUALS_SIGN = " = ";
    public static final String FINAL_PARENTHESIS = ") ";
    public static final String ALL = "*";
    public static final String JOIN = " JOIN ";
    public static final String ON = " ON ";
    public static final String ID_PERSONA = "id_persona";
    public static final String AND = " AND ";
    public static final String FALSE = "false";
    
    //Messages
    public static final String SE_HA_DADO = "Se ha dado de ";
    public static final String NO_SE_HA_PODIDO = "No se ha podido dar de ";
    public static final String ALTA = "alta";
    public static final String BAJA = "baja";
    public static final String EL_AGRICULTOR = " el agricultor";

}
