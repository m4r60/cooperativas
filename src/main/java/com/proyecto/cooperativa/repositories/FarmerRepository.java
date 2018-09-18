package com.proyecto.cooperativa.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class FarmerRepository {

    @Autowired
    JdbcTemplate jdbcTemplate;

    private static final  String WHERE = " WHERE ";
    private static final String LIKE = " LIKE ?";
    private static final String OR = " OR ";
    private List<String> farmerFieldsToGet = Arrays.asList("n_socio", "cif_nif",
            "nombre_razon_social",
            "apellido",
            "direccion",
            "telefono",
            "email");

    public Map<String, Object> getFarmersList(String textToSearch) {
        StringBuilder query = new StringBuilder("SELECT  "
                + farmerFieldsToGet.stream().collect(Collectors.joining(","))
                + " FROM PERSONAS"
                + " JOIN (p.id_persona = a.id_persona) ON "
                + "agricultores a");
        if (!StringUtils.isEmpty(textToSearch)) {
            StringBuilder whereClause = new StringBuilder();
                     whereClause.append(WHERE).append(farmerFieldsToGet.stream()
                             .collect(Collectors.joining(LIKE  + OR))).append(LIKE);
            query.append(whereClause.toString());
            String[] fieldsToSearch = new String[farmerFieldsToGet.size()];
            Arrays.fill(fieldsToSearch, "%" + textToSearch + "%");
            return jdbcTemplate.queryForMap(query.toString(), fieldsToSearch);
        } else{
            return jdbcTemplate.queryForMap(query.toString());
        }
    }

}
