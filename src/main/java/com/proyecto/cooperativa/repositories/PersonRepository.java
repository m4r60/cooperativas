package com.proyecto.cooperativa.repositories;

import com.proyecto.cooperativa.models.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.proyecto.cooperativa.models.Constants.*;
import static com.proyecto.cooperativa.utils.JdbcUtils.buildValuesWithQuestionMarks;

@Repository
public class PersonRepository {
    private static final Logger log = LoggerFactory.getLogger(PersonRepository.class);

    @Autowired
    JdbcTemplate jdbcTemplate;

    private final List<String>fieldsToUpdate = Arrays.asList(
             "cif_nif",
            "nombre_razon_social",
            "apellidos",
            "direccion",
            "telefono",
            "email");

    public boolean create(Person person){
        final String sql = INSERT_INTO
                + PERSONAS
                + INITIAL_PARENTHESIS
                + fieldsToUpdate.stream().collect(Collectors.joining(","))
                + VALUES
                + buildValuesWithQuestionMarks(fieldsToUpdate.size())
                + FINAL_PARENTHESIS;
        return inserting(person, sql);
    }


    private boolean inserting(Person person, String sql){
        boolean isInserted = false;
        try {
            isInserted = jdbcTemplate.update(sql,
                    person.getCifNif(),
                    person.getName(),
                    person.getLastName(),
                    person.getAdress(),
                    person.getPhoneNumber(),
                    person.getEmail()) >0;
        }catch (Exception e) {
            log.error("Inserting a person object, query: {}",  sql);
        }
        return isInserted;
    }



}
