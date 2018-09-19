package com.proyecto.cooperativa.repositories;

import com.proyecto.cooperativa.models.Farmer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class FarmerRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String SELECT = "SELECT ";
    private static final String FROM = " FROM ";
    private static final String WHERE = " WHERE ";
    private static final String LIKE = " LIKE ?";
    private static final String OR = " OR ";
    private static final String PERCENT = "%";
    private static final String COMMA_SEPARATOR = ", ";
    private List<String> farmerFieldsToGet = Arrays.asList("a.n_socio", "p.cif_nif",
            "p.nombre_razon_social",
            "p.apellidos",
            "p.direccion",
            "p.telefono",
            "p.email");

    public List<Map<String, Object>> getFarmersList(String textToSearch) {
        String query = buildSelectClause();
        String whereClause = buildWhereClause(textToSearch);
        if (!StringUtils.isEmpty(whereClause)) {
            query += whereClause;
            return jdbcTemplate.queryForList(query,
                    textToSearch,
                    textToSearch,
                    textToSearch,
                    textToSearch,
                    textToSearch,
                    textToSearch,
                    textToSearch);
        } else {
            return jdbcTemplate.queryForList(query);
        }
    }

    private String buildSelectClause() {
        return SELECT
                + farmerFieldsToGet.stream().collect(Collectors.joining(COMMA_SEPARATOR))
                + FROM + "PERSONAS p "
                + " JOIN agricultores a ON (p.id_persona = a.id_persona) ";
    }

    private String buildWhereClause(String textToSearch) {
        String whereClause = "";
        if (!StringUtils.isEmpty(textToSearch)) {
            whereClause = WHERE
                    + farmerFieldsToGet.stream()
                    .collect(Collectors.joining(LIKE + OR))
                    + LIKE;

        }
        return whereClause;
    }

//    private Object buildWhereClauseParameters(String textToSearch) {
//        textToSearch = PERCENT + textToSearch + PERCENT;
//        return new Object[]{textToSearch,
//                textToSearch,
//                textToSearch,
//                textToSearch,
//                textToSearch,
//                textToSearch,
//                textToSearch};
//    }


    class FarmerRowMapper implements RowMapper<Farmer> {
        public Farmer mapRow(ResultSet rs, int rowNumber) throws SQLException {
            Farmer farmer = new Farmer();

            return new Farmer();
        }

    }


}
