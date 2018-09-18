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
    private JdbcTemplate jdbcTemplate;

    private static final String WHERE = " WHERE ";
    private static final String LIKE = " LIKE ?";
    private static final String OR = " OR ";
    private static final String PERCENT = "%";
    private List<String> farmerFieldsToGet = Arrays.asList("n_socio", "cif_nif",
            "nombre_razon_social",
            "apellido",
            "direccion",
            "telefono",
            "email");

    public Map<String, Object> getFarmersList(String textToSearch) {
        StringBuilder query = buildSelectClause();
        String whereClause = buildWhereClause(textToSearch);
        if (StringUtils.isEmpty(whereClause)) {
            query.append(whereClause);
            return jdbcTemplate.queryForMap(query.toString(), buildParametersOfQuery(textToSearch));
        } else {
            return jdbcTemplate.queryForMap(query.toString());
        }
    }


    private StringBuilder buildSelectClause() {
        return new StringBuilder("SELECT  "
                + farmerFieldsToGet.stream().collect(Collectors.joining(","))
                + " FROM PERSONAS"
                + " JOIN (p.id_persona = a.id_persona) ON "
                + "agricultores a");
    }

    private String buildWhereClause(String textToSearch) {
        String whereClause = "";
        if (!StringUtils.isEmpty(textToSearch)) {
            whereClause = WHERE + farmerFieldsToGet
                    .stream().collect(Collectors.joining(LIKE + OR))
                    + LIKE;

        }
        return whereClause;
    }

    private String[] buildParametersOfQuery(String textToSearch) {
        String[] fieldsToSearch = new String[farmerFieldsToGet.size()];
        Arrays.fill(fieldsToSearch, PERCENT + textToSearch + PERCENT);
        return fieldsToSearch;
    }

}
